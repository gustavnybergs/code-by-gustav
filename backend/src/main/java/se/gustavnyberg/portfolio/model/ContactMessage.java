package se.gustavnyberg.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
 * Bean Validation säkerställer att datan är giltig innan den når kön.
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

    @NotBlank(message = "Namn får inte vara tomt")
    @Size(max = 100, message = "Namn får max vara 100 tecken")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email får inte vara tom")
    @Email(message = "Ogiltig emailadress")
    @Column(nullable = false)
    private String email;

    @Size(max = 200, message = "Ämne får max vara 200 tecken")
    private String subject;

    @NotBlank(message = "Meddelande får inte vara tomt")
    @Size(max = 2000, message = "Meddelande får max vara 2000 tecken")
    @Column(length = 2000, nullable = false)
    private String message;

    private LocalDateTime sentDate;

    // Automatisk tidsstämpel när meddelande skapas
    @PrePersist
    protected void onCreate() {
        sentDate = LocalDateTime.now();
    }
}