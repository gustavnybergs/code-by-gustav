package se.gustavnyberg.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    // Backend, Frontend, Database, Tools, Other
    @Column(nullable = false)
    private String category;
    
    // Skill level from 1-100
    private Integer proficiencyLevel;
    
    // FontAwesome icon name or emoji for visual representation
    private String icon;
}
