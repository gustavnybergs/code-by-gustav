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
import se.gustavnyberg.portfolio.repository.ContactMessageRepository;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    // Submit contact form - skicka till RabbitMQ istället för direkt till DB
    @PostMapping
    public ResponseEntity<String> submitContactForm(@RequestBody ContactMessage contactMessage) {
        try {
            // Skicka meddelandet till RabbitMQ queue
            rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                contactMessage
            );
            
            logger.info("Meddelande skickat till RabbitMQ från: {}", contactMessage.getEmail());
            
            // Returnera 202 Accepted - meddelandet är mottaget men inte sparat än
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body("Message received and queued for processing");
                    
        } catch (Exception e) {
            logger.error("Fel vid skickning till RabbitMQ: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to queue message");
        }
    }
}
