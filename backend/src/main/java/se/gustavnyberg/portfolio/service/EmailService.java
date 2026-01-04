package se.gustavnyberg.portfolio.service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.gustavnyberg.portfolio.model.ContactMessage;

import java.io.IOException;

@Service
public class EmailService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    
    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;
    
    @Value("${sendgrid.from.email}")
    private String fromEmail;
    
    @Value("${sendgrid.from.name}")
    private String fromName;
    
    // Skickar email via SendGrid API
    public void sendContactNotification(ContactMessage message) {
        logger.info("Förbereder email via SendGrid...");
        
        Email from = new Email(fromEmail, fromName);
        Email to = new Email("gustavnybergs@gmail.com");
        String subject = "Nytt meddelande från portfolio: " + message.getSubject();
        
        String emailBody = String.format(
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
        
        Content content = new Content("text/plain", emailBody);
        Mail mail = new Mail(from, subject, to, content);
        
        // Reply-to satt till avsändarens email
        mail.setReplyTo(new Email(message.getEmail()));
        
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            
            Response response = sg.api(request);
            
            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                logger.info("Email skickat via SendGrid! Status: {}", response.getStatusCode());
            } else {
                logger.error("SendGrid fel - Status: {}, Body: {}", 
                    response.getStatusCode(), response.getBody());
            }
            
        } catch (IOException e) {
            logger.error("CRITICAL: Fel vid SendGrid API-anrop!", e);
            logger.error("SendGrid error: {}", e.getMessage());
        }
    }
}
