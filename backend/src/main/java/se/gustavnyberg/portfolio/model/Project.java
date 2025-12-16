package se.gustavnyberg.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    // Short description for list views
    @Column(length = 500)
    private String description;
    
    // Detailed description for project detail page
    @Column(length = 2000)
    private String longDescription;
    
    // Store technologies as comma-separated string in database
    @ElementCollection
    @CollectionTable(name = "project_technologies", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "technology")
    private List<String> technologies;
    
    private String imageUrl;
    
    private String githubUrl;
    
    private String liveUrl;
    
    private LocalDate createdDate;
    
    // Flag to highlight important projects
    @Column(nullable = false)
    private Boolean featured = false;
}
