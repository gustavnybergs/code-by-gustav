package se.gustavnyberg.portfolio.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import se.gustavnyberg.portfolio.model.ContactMessage;
import se.gustavnyberg.portfolio.repository.ContactMessageRepository;

@Component
public class ContactMessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactMessageConsumer.class);
    
    private final ContactMessageRepository contactMessageRepository;
    private final JavaMailSender mailSender;
    
    public ContactMessageConsumer(ContactMessageRepository contactMessageRepository, JavaMailSender mailSender) {
        this.contactMessageRepository = contactMessageRepository;
        this.mailSender = mailSender;
    }
    
    // Tar emot meddelanden från RabbitMQ queue
    @RabbitListener(queues = "contact.messages.queue")
    public void receiveMessage(ContactMessage message) {
        logger.info("Mottaget meddelande från RabbitMQ: {}", message.getName());
        
        try {
            // Sparar först i databasen
            ContactMessage savedMessage = contactMessageRepository.save(message);
            logger.info("Meddelande sparat i databas med ID: {}", savedMessage.getId());
            
            // Skickar mail till mig så jag får notis
            sendEmailNotification(savedMessage);
            
        } catch (Exception e) {
            logger.error("Fel vid sparande av meddelande: {}", e.getMessage(), e);
        }
    }
    
    // Bygger och skickar email med meddelandeinnehållet
    private void sendEmailNotification(ContactMessage message) {
        logger.info("Försöker skicka email-notifikation...");
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo("gustavnybergs@gmail.com");
            email.setSubject("Nytt meddelande från portfolio: " + message.getSubject());
            email.setText(
                "Du har fått ett nytt meddelande via din portfolio!\n\n" +
                "Från: " + message.getName() + "\n" +
                "Email: " + message.getEmail() + "\n" +
                "Ämne: " + message.getSubject() + "\n\n" +
                "Meddelande:\n" + message.getMessage() + "\n\n" +
                "---\n" +
                "Skickat: " + message.getSentDate()
            );
            email.setReplyTo(message.getEmail());
            
            logger.info("Skickar email via JavaMailSender...");
            mailSender.send(email);
            logger.info("Email-notifikation skickad till gustavnybergs@gmail.com");
            
        } catch (Exception e) {
            logger.error("CRITICAL: Fel vid skickning av email!", e);
            logger.error("Email error details: {}", e.getMessage());
            if (e.getCause() != null) {
                logger.error("Caused by: {}", e.getCause().getMessage());
            }
        }
    }
}
