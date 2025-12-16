package se.gustavnyberg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gustavnyberg.portfolio.model.Skill;
import se.gustavnyberg.portfolio.repository.SkillRepository;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {
    
    @Autowired
    private SkillRepository skillRepository;
    
    // Get all skills
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }
    
    // Get skills by category
    @GetMapping("/category/{category}")
    public List<Skill> getSkillsByCategory(@PathVariable String category) {
        return skillRepository.findByCategory(category);
    }
    
    // Get skills ordered by proficiency
    @GetMapping("/top")
    public List<Skill> getTopSkills() {
        return skillRepository.findAllByOrderByProficiencyLevelDesc();
    }
    
    // Create new skill
    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }
    
    // Delete skill
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        return skillRepository.findById(id)
                .map(skill -> {
                    skillRepository.delete(skill);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
