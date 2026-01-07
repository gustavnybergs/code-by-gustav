package se.gustavnyberg.portfolio.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ-konfiguration för meddelandekön.
 *
 * Skapar en kö där kontaktmeddelanden läggs innan de processas.
 * Exchange fungerar som en brevsorterare som skickar meddelanden
 * till rätt kö baserat på routing key.
 *
 * För denna portfolio används bara en kö, men strukturen med
 * exchange och routing key gör det skalbart om fler köer behövs senare.
 * tex mail eller sms kö
 *
 * JSON-convertern omvandlar Java-objekt till JSON när de skickas till kön.
 */

@Configuration
public class RabbitMQConfig {
    
    // Namn på queue, exchange och routing key
    public static final String QUEUE_NAME = "contact.messages.queue";
    public static final String EXCHANGE_NAME = "contact.exchange";
    public static final String ROUTING_KEY = "contact.messages";
    
    // Skapa queue för kontaktmeddelanden
    @Bean
    public Queue contactMessagesQueue() {
        return new Queue(QUEUE_NAME, true); // durable = true = kön överlever omstart
    }
    
    // Skapa topic exchange
    @Bean
    public TopicExchange contactExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }
    
    // Bind queue till exchange med routing key
    @Bean
    public Binding binding(Queue contactMessagesQueue, TopicExchange contactExchange) {
        return BindingBuilder
                .bind(contactMessagesQueue)
                .to(contactExchange)
                .with(ROUTING_KEY);
    }
    
    // JSON message converter för att skicka objekt som JSON till kön
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    // RabbitTemplate med JSON converter
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
