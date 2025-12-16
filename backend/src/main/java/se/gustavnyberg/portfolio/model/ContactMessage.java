package se.gustavnyberg.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "contact_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessage {
    
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
    
    // For admin to mark messages as read
    @Column(nullable = false)
    private Boolean isRead = false;
    
    // Automatically set timestamp when message is created
    @PrePersist
    protected void onCreate() {
        sentDate = LocalDateTime.now();
    }
}
