package se.gustavnyberg.portfolio.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import se.gustavnyberg.portfolio.model.ContactMessage;
import se.gustavnyberg.portfolio.repository.ContactMessageRepository;
import se.gustavnyberg.portfolio.service.EmailService;

/**
 * Lyssnar på RabbitMQ-kön och processar inkommande kontaktmeddelanden.
 *
 * När ett meddelande kommer från kön:
 * 1. Sparas det i databasen (PostgreSQL)
 * 2. Skickas email-notifikation till Outlook via MailerSend
 *
 * Try-catch säkerställer att fel loggas utan att krascha applikationen.
 */

@Component
public class ContactMessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactMessageConsumer.class);
    
    private final ContactMessageRepository contactMessageRepository;
    private final EmailService emailService;
    
    public ContactMessageConsumer(ContactMessageRepository contactMessageRepository, EmailService emailService) {
        this.contactMessageRepository = contactMessageRepository;
        this.emailService = emailService;
    }
    
    // Tar emot meddelanden från RabbitMQ queue
    @RabbitListener(queues = "contact.messages.queue")
    public void receiveMessage(ContactMessage message) {
        logger.info("Mottaget meddelande från RabbitMQ: {}", message.getName());
        
        try {
            // Sparar först i databasen
            ContactMessage savedMessage = contactMessageRepository.save(message);
            logger.info("Meddelande sparat i databas med ID: {}", savedMessage.getId());
            
            // Skickar email till outlook via MailerSend
            emailService.sendContactNotification(savedMessage);
            
        } catch (Exception e) {
            logger.error("Fel vid sparande av meddelande: {}", e.getMessage(), e);
        }
    }
}
