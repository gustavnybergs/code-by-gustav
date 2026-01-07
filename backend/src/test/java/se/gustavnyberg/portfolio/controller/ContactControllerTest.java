package se.gustavnyberg.portfolio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.AmqpException;
import se.gustavnyberg.portfolio.config.RabbitMQConfig;
import se.gustavnyberg.portfolio.model.ContactMessage;
import se.gustavnyberg.portfolio.repository.ContactMessageRepository;
import se.gustavnyberg.portfolio.service.RateLimiterService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Tester för ContactController.
 * Testar REST-endpoint, validering, RabbitMQ-integration och rate limiting.
 */
@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @MockBean
    private ContactMessageRepository contactMessageRepository;

    @MockBean
    private RateLimiterService rateLimiterService;

    @BeforeEach
    void setUp() {
        // Tillåt requests som standard
        when(rateLimiterService.isAllowed(anyString())).thenReturn(true);
    }

    private ContactMessage createValidMessage() {
        ContactMessage message = new ContactMessage();
        message.setName("Test Testsson");
        message.setEmail("test@example.com");
        message.setSubject("Testämne");
        message.setMessage("Detta är ett testmeddelande.");
        return message;
    }

    @Test
    @DisplayName("Giltig request returnerar 202 Accepted")
    void submitContactForm_ValidData_Returns202() throws Exception {
        ContactMessage message = createValidMessage();

        mockMvc.perform(post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Message received and queued for processing"));
    }

    @Test
    @DisplayName("Giltig request skickar meddelande till RabbitMQ")
    void submitContactForm_ValidData_SendsToRabbitMQ() throws Exception {
        ContactMessage message = createValidMessage();

        mockMvc.perform(post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)));

        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(RabbitMQConfig.EXCHANGE_NAME),
                eq(RabbitMQConfig.ROUTING_KEY),
                any(ContactMessage.class)
        );
    }

    @Test
    @DisplayName("Request utan namn returnerar 400 Bad Request")
    void submitContactForm_MissingName_Returns400() throws Exception {
        ContactMessage message = createValidMessage();
        message.setName("");

        mockMvc.perform(post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Request med ogiltig email returnerar 400 Bad Request")
    void submitContactForm_InvalidEmail_Returns400() throws Exception {
        ContactMessage message = createValidMessage();
        message.setEmail("ingen-giltig-email");

        mockMvc.perform(post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("RabbitMQ-fel returnerar 500 Internal Server Error")
    void submitContactForm_RabbitMQFailure_Returns500() throws Exception {
        ContactMessage message = createValidMessage();

        doThrow(new AmqpException("Connection failed"))
                .when(rabbitTemplate).convertAndSend(anyString(), anyString(), any(ContactMessage.class));

        mockMvc.perform(post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to queue message"));
    }

    @Test
    @DisplayName("Rate limit överskriden returnerar 429 Too Many Requests")
    void submitContactForm_RateLimitExceeded_Returns429() throws Exception {
        ContactMessage message = createValidMessage();
        
        // Simulera att rate limit är nådd
        when(rateLimiterService.isAllowed(anyString())).thenReturn(false);

        mockMvc.perform(post("/api/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message)))
                .andExpect(status().isTooManyRequests())
                .andExpect(content().string("Too many requests. Please wait a moment before trying again."));

        // Verifiera att RabbitMQ INTE anropades
        verify(rabbitTemplate, never()).convertAndSend(anyString(), anyString(), any(ContactMessage.class));
    }
}
