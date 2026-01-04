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

    // Lyssna på queue och konsumera meddelanden
    @RabbitListener(queues = "contact.messages.queue")
    public void receiveMessage(ContactMessage message) {
        logger.info("Mottaget meddelande från RabbitMQ: {}", message.getName());

        try {
            // Spara meddelandet i databas
            ContactMessage savedMessage = contactMessageRepository.save(message);
            logger.info("Meddelande sparat i databas med ID: {}", savedMessage.getId());

            // Skicka email-notifikation till mig
            sendEmailNotification(savedMessage);

        } catch (Exception e) {
            logger.error("Fel vid sparande av meddelande: {}", e.getMessage(), e);
            // Här kan vi lägga till Dead Letter Queue logik senare
        }
    }

    // Skicka email till mig själv med meddelandet
    private void sendEmailNotification(ContactMessage message) {
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

            mailSender.send(email);
            logger.info("Email-notifikation skickad till gustavnybergs@gmail.com");

        } catch (Exception e) {
            logger.error("Fel vid skickning av email: {}", e.getMessage(), e);
            // Logga felet men låt inte email-felet stoppa hela processen
        }
    }
}