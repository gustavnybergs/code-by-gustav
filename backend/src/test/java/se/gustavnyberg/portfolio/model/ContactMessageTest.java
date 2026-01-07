package se.gustavnyberg.portfolio.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester för ContactMessage-entiteten.
 * 
 * Fokuserar på Bean Validation-reglerna och att @PrePersist fungerar.
 */
class ContactMessageTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Hjälpmetod för att skapa ett giltigt meddelande
    private ContactMessage createValidMessage() {
        ContactMessage message = new ContactMessage();
        message.setName("Test Testsson");
        message.setEmail("test@example.com");
        message.setSubject("Testämne");
        message.setMessage("Detta är ett testmeddelande.");
        return message;
    }

    @Test
    @DisplayName("Giltigt meddelande passerar validering")
    void validMessage_PassesValidation() {
        ContactMessage message = createValidMessage();

        Set<ConstraintViolation<ContactMessage>> violations = validator.validate(message);

        assertTrue(violations.isEmpty(), "Giltigt meddelande ska inte ha valideringsfel");
    }

    @Test
    @DisplayName("Meddelande utan obligatoriska fält ger valideringsfel")
    void invalidMessage_FailsValidation() {
        ContactMessage message = new ContactMessage();
        // Lämnar alla fält tomma

        Set<ConstraintViolation<ContactMessage>> violations = validator.validate(message);

        // Ska ha fel för name, email och message
        assertFalse(violations.isEmpty(), "Tomt meddelande ska ha valideringsfel");
        assertTrue(violations.size() >= 3, "Ska ha minst 3 valideringsfel");
    }

    @Test
    @DisplayName("@PrePersist sätter sentDate automatiskt")
    void onCreate_SetsSentDate() {
        ContactMessage message = createValidMessage();
        assertNull(message.getSentDate(), "sentDate ska vara null innan persist");

        // Simulera @PrePersist
        message.onCreate();

        assertNotNull(message.getSentDate(), "sentDate ska sättas av onCreate");
    }
}
