package se.gustavnyberg.portfolio.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import se.gustavnyberg.portfolio.model.ContactMessage;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Tester för EmailService.
 *
 * Använder mockad RestTemplate för att testa API-anrop utan att
 * faktiskt kontakta MailerSend.
 */
@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private EmailService emailService;

    private ContactMessage testMessage;

    @BeforeEach
    void setUp() {
        emailService = new EmailService(restTemplate);

        // Sätt konfigurationsvärden via reflection
        ReflectionTestUtils.setField(emailService, "apiKey", "test-api-key");
        ReflectionTestUtils.setField(emailService, "fromEmail", "noreply@test.se");
        ReflectionTestUtils.setField(emailService, "fromName", "Test Portfolio");
        ReflectionTestUtils.setField(emailService, "toEmail", "mottagare@test.se");

        testMessage = new ContactMessage();
        testMessage.setName("Test Testsson");
        testMessage.setEmail("avsandare@example.com");
        testMessage.setSubject("Testämne");
        testMessage.setMessage("Detta är ett testmeddelande.");
        testMessage.setSentDate(LocalDateTime.now());
    }

    @Test
    @DisplayName("Lyckat API-anrop loggas som success")
    void sendContactNotification_SuccessfulApiCall() {
        ResponseEntity<String> successResponse = new ResponseEntity<>("OK", HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(successResponse);

        // Ska köras utan exception
        assertDoesNotThrow(() -> emailService.sendContactNotification(testMessage));

        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class));
    }

    @Test
    @DisplayName("Request body innehåller rätt struktur")
    @SuppressWarnings("unchecked")
    void sendContactNotification_CorrectRequestBody() {
        ResponseEntity<String> successResponse = new ResponseEntity<>("OK", HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(successResponse);

        emailService.sendContactNotification(testMessage);

        // Fånga request body
        ArgumentCaptor<HttpEntity<Map<String, Object>>> captor = ArgumentCaptor.forClass(HttpEntity.class);
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.POST), captor.capture(), eq(String.class));

        HttpEntity<Map<String, Object>> captured = captor.getValue();
        Map<String, Object> body = captured.getBody();

        // Verifiera att viktiga fält finns
        assertNotNull(body);
        assertNotNull(body.get("from"));
        assertNotNull(body.get("to"));
        assertNotNull(body.get("reply_to"));
        assertNotNull(body.get("subject"));
        assertTrue(body.get("subject").toString().contains("Testämne"));
    }

    @Test
    @DisplayName("API-fel hanteras utan exception")
    void sendContactNotification_ApiError_LogsError() {
        ResponseEntity<String> errorResponse = new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenReturn(errorResponse);

        // Ska inte kasta exception
        assertDoesNotThrow(() -> emailService.sendContactNotification(testMessage));
    }

    @Test
    @DisplayName("Nätverksfel hanteras utan exception")
    void sendContactNotification_NetworkError_LogsError() {
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new RestClientException("Connection timeout"));

        // Ska inte kasta exception, bara logga
        assertDoesNotThrow(() -> emailService.sendContactNotification(testMessage));
    }
}
