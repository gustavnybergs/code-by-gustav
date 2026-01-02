package se.gustavnyberg.portfolio.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import se.gustavnyberg.portfolio.model.ContactMessage;
import se.gustavnyberg.portfolio.repository.ContactMessageRepository;

@Component
public class ContactMessageConsumer {
    
    private static final Logger logger = LoggerFactory.getLogger(ContactMessageConsumer.class);
    
    private final ContactMessageRepository contactMessageRepository;
    
    public ContactMessageConsumer(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }
    
    // Lyssna på queue och konsumera meddelanden
    @RabbitListener(queues = "contact.messages.queue")
    public void receiveMessage(ContactMessage message) {
        logger.info("Mottaget meddelande från RabbitMQ: {}", message.getName());
        
        try {
            // Spara meddelandet i MySQL
            ContactMessage savedMessage = contactMessageRepository.save(message);
            logger.info("Meddelande sparat i databas med ID: {}", savedMessage.getId());
        } catch (Exception e) {
            logger.error("Fel vid sparande av meddelande: {}", e.getMessage(), e);
            // Här kan vi lägga till Dead Letter Queue logik senare
        }
    }
}
