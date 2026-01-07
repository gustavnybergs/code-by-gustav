package se.gustavnyberg.portfolio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gustavnyberg.portfolio.config.RabbitMQConfig;
import se.gustavnyberg.portfolio.model.ContactMessage;
import se.gustavnyberg.portfolio.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private RateLimiterService rateLimiterService;
    
    // Submit contact form - skicka till RabbitMQ
    @PostMapping
    public ResponseEntity<String> submitContactForm(
            @Valid @RequestBody ContactMessage contactMessage,
            HttpServletRequest request) {
        
        // Hämta klientens IP-adress
        String clientIp = getClientIp(request);
        
        // Kolla rate limit innan vi processar
        if (!rateLimiterService.isAllowed(clientIp)) {
            logger.warn("Rate limit nådd för IP: {}", clientIp);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Too many requests. Please wait a moment before trying again.");
        }
        
        try {
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                contactMessage
            );
            
            logger.info("Meddelande skickat till RabbitMQ från: {}", contactMessage.getEmail());
            
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Message received and queued for processing");
                    
        } catch (Exception e) {
            logger.error("Fel vid skickning till RabbitMQ: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to queue message");
        }
    }
    
    /**
     * Hämtar klientens riktiga IP-adress.
     * Kollar X-Forwarded-For header för requests via proxy/load balancer.
     */
    private String getClientIp(HttpServletRequest request) {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isEmpty()) {
            // Första IP:n i listan är klientens ursprungliga IP
            return forwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
