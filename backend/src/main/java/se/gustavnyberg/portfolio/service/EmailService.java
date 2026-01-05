package se.gustavnyberg.portfolio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.gustavnyberg.portfolio.model.ContactMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    @Value("${mailersend.api.key}")
    private String apiKey;
    
    @Value("${mailersend.from.email}")
    private String fromEmail;
    
    @Value("${mailersend.from.name}")
    private String fromName;
    
    @Value("${mailersend.to.email}")
    private String toEmail;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    // Skickar email via MailerSend API
    public void sendContactNotification(ContactMessage message) {
        logger.info("Förbereder email via MailerSend...");
        
        String url = "https://api.mailersend.com/v1/email";
        
        // Bygg request body
        Map<String, Object> requestBody = new HashMap<>();
        
        // Avsändare
        Map<String, String> from = new HashMap<>();
        from.put("email", fromEmail);
        from.put("name", fromName);
        requestBody.put("from", from);
        
        // Mottagare
        Map<String, String> to = new HashMap<>();
        to.put("email", toEmail);
        to.put("name", "Gustav Nyberg");
        requestBody.put("to", List.of(to));
        
        // Reply-to (kundens email)
        Map<String, String> replyTo = new HashMap<>();
        replyTo.put("email", message.getEmail());
        replyTo.put("name", message.getName());
        requestBody.put("reply_to", replyTo);
        
        // Ämne och innehåll
        requestBody.put("subject", "Nytt meddelande från portfolio: " + message.getSubject());
        
        String textContent = String.format(
            "Nytt meddelande via portfolio!\n\n" +
            "Från: %s\n" +
            "Email: %s\n" +
            "Ämne: %s\n\n" +
            "Meddelande:\n%s\n\n" +
            "---\n" +
            "Skickat: %s",
            message.getName(),
            message.getEmail(),
            message.getSubject(),
            message.getMessage(),
            message.getSentDate()
        );
        requestBody.put("text", textContent);
        
        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("Email skickat via MailerSend! Status: {}", response.getStatusCode());
            } else {
                logger.error("MailerSend fel - Status: {}, Body: {}", 
                    response.getStatusCode(), response.getBody());
            }
        } catch (Exception e) {
            logger.error("CRITICAL: Fel vid MailerSend API-anrop!", e);
            logger.error("MailerSend error: {}", e.getMessage());
        }
    }
}
