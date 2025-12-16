package se.gustavnyberg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gustavnyberg.portfolio.model.ContactMessage;
import se.gustavnyberg.portfolio.repository.ContactMessageRepository;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {
    
    @Autowired
    private ContactMessageRepository contactMessageRepository;
    
    // Submit contact form
    @PostMapping
    public ResponseEntity<ContactMessage> submitContactForm(@RequestBody ContactMessage contactMessage) {
        ContactMessage savedMessage = contactMessageRepository.save(contactMessage);
        return ResponseEntity.ok(savedMessage);
    }
    
    // Get all messages (for admin)
    @GetMapping
    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAll();
    }
    
    // Get unread messages (for admin)
    @GetMapping("/unread")
    public List<ContactMessage> getUnreadMessages() {
        return contactMessageRepository.findByIsReadFalse();
    }
    
    // Mark message as read (for admin)
    @PutMapping("/{id}/read")
    public ResponseEntity<ContactMessage> markAsRead(@PathVariable Long id) {
        return contactMessageRepository.findById(id)
                .map(message -> {
                    message.setIsRead(true);
                    return ResponseEntity.ok(contactMessageRepository.save(message));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
