package se.gustavnyberg.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.gustavnyberg.portfolio.model.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    // Find all featured projects
    List<Project> findByFeaturedTrue();
    
    // Find projects by technology (searches in the technologies list)
    List<Project> findByTechnologiesContaining(String technology);
}
