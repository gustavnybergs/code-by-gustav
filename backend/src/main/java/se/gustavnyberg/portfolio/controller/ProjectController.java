package se.gustavnyberg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.gustavnyberg.portfolio.model.Project;
import se.gustavnyberg.portfolio.repository.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    // Get all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    
    // Get project by id
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Get featured projects
    @GetMapping("/featured")
    public List<Project> getFeaturedProjects() {
        return projectRepository.findByFeaturedTrue();
    }
    
    // Create new project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }
    
    // Update project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setTitle(projectDetails.getTitle());
                    project.setDescription(projectDetails.getDescription());
                    project.setLongDescription(projectDetails.getLongDescription());
                    project.setTechnologies(projectDetails.getTechnologies());
                    project.setImageUrl(projectDetails.getImageUrl());
                    project.setGithubUrl(projectDetails.getGithubUrl());
                    project.setLiveUrl(projectDetails.getLiveUrl());
                    project.setCreatedDate(projectDetails.getCreatedDate());
                    project.setFeatured(projectDetails.getFeatured());
                    return ResponseEntity.ok(projectRepository.save(project));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Delete project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(project -> {
                    projectRepository.delete(project);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
