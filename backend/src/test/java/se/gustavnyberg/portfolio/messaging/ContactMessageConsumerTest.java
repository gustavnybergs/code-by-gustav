package se.gustavnyberg.portfolio.messaging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.gustavnyberg.portfolio.model.ContactMessage;
import se.gustavnyberg.portfolio.repository.ContactMessageRepository;
import se.gustavnyberg.portfolio.service.EmailService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Tester för ContactMessageConsumer.
 * 
 * Testar att meddelanden från kön hanteras korrekt - sparas i databas
 * och triggar email-notifikation.
 */
@ExtendWith(MockitoExtension.class)
class ContactMessageConsumerTest {

    @Mock
    private ContactMessageRepository contactMessageRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ContactMessageConsumer consumer;

    private ContactMessage testMessage;

    @BeforeEach
    void setUp() {
        testMessage = new ContactMessage();
        testMessage.setId(1L);
        testMessage.setName("Test Testsson");
        testMessage.setEmail("test@example.com");
        testMessage.setSubject("Testämne");
        testMessage.setMessage("Detta är ett testmeddelande.");
    }

    @Test
    @DisplayName("Meddelande sparas i databasen")
    void receiveMessage_SavesMessageToDatabase() {
        when(contactMessageRepository.save(any(ContactMessage.class))).thenReturn(testMessage);

        consumer.receiveMessage(testMessage);

        verify(contactMessageRepository, times(1)).save(testMessage);
    }

    @Test
    @DisplayName("Email-notifikation skickas efter sparning")
    void receiveMessage_SendsEmailNotification() {
        when(contactMessageRepository.save(any(ContactMessage.class))).thenReturn(testMessage);

        consumer.receiveMessage(testMessage);

        // Verifiera att email skickas med det sparade meddelandet
        verify(emailService, times(1)).sendContactNotification(testMessage);
    }

    @Test
    @DisplayName("Databasfel loggas men kastar inte exception")
    void receiveMessage_DatabaseError_LogsError() {
        when(contactMessageRepository.save(any(ContactMessage.class)))
                .thenThrow(new RuntimeException("Database connection failed"));

        // Ska inte kasta exception, bara logga felet
        consumer.receiveMessage(testMessage);

        // Email ska inte skickas om sparning misslyckas
        verify(emailService, never()).sendContactNotification(any());
    }

    @Test
    @DisplayName("Emailfel påverkar inte databassparning")
    void receiveMessage_EmailError_StillSavesToDatabase() {
        when(contactMessageRepository.save(any(ContactMessage.class))).thenReturn(testMessage);
        doThrow(new RuntimeException("Email service unavailable"))
                .when(emailService).sendContactNotification(any());

        // Ska inte kasta exception även om email misslyckas
        consumer.receiveMessage(testMessage);

        // Databasen ska fortfarande ha anropats
        verify(contactMessageRepository, times(1)).save(testMessage);
    }
}
