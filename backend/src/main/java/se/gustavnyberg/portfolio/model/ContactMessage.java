package se.gustavnyberg.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Datamodell för kontaktmeddelanden.
 *
 * Representerar både Java-objektet som skickas runt i applikationen
 * och databastabellen "contact_messages" i PostgreSQL.
 *
 * Implements Serializable krävs för att kunna skicka objektet via RabbitMQ.
 * Lombok-annotationerna skapar getters, setters och konstruktorer automatiskt.
 *
 * @PrePersist sätter tidsstämpel automatiskt när meddelandet sparas.
 */

@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessage implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    private String subject;
    
    @Column(length = 2000, nullable = false)
    private String message;
    
    private LocalDateTime sentDate;

    // Automatisk tidsstämpel när meddelande skapas
    @PrePersist
    protected void onCreate() {
        sentDate = LocalDateTime.now();
    }
}
