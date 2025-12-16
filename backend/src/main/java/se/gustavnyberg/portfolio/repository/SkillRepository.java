package se.gustavnyberg.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.gustavnyberg.portfolio.model.Skill;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    // Find skills by category
    List<Skill> findByCategory(String category);
    
    // Find skills ordered by proficiency level
    List<Skill> findAllByOrderByProficiencyLevelDesc();
}
