import React from 'react';
import SkillCard from '../../components/SkillCard/SkillCard';
import './Skills.css';

function Skills() {
  // Hårdkodad skills-data organiserad efter kategori
  const skills = [
    // Backend
    { id: 1, name: "Java", category: "Backend" },
    { id: 2, name: "Spring Boot", category: "Backend" },
    { id: 3, name: "JPA/Hibernate", category: "Backend" },
    { id: 4, name: "REST API", category: "Backend" },
    { id: 5, name: "Spring Security", category: "Backend" },
    
    // Frontend
    { id: 6, name: "React", category: "Frontend" },
    { id: 7, name: "JavaScript", category: "Frontend" },
    { id: 8, name: "TypeScript", category: "Frontend" },
    
    // Database
    { id: 9, name: "PostgreSQL", category: "Database" },
    { id: 10, name: "MySQL", category: "Database" },
    
    // Testing
    { id: 11, name: "Jest", category: "Testing" },
    { id: 12, name: "JUnit", category: "Testing" },
    
    // DevOps & Tools
    { id: 13, name: "Docker", category: "DevOps" },
    { id: 14, name: "Git", category: "Tools" },
    { id: 15, name: "Vite", category: "Tools" },
    { id: 16, name: "Vercel", category: "Tools" },
    { id: 17, name: "Railway", category: "Tools" },
    { id: 18, name: "Azure", category: "Tools" }
  ];

  return (
    <main className="skills">
      <section className="skills__hero">
        <div className="container">
          <h1 className="skills__title">My Skills</h1>
          <p className="skills__subtitle">
            Technologies and tools I work with. From backend development 
            to modern frontend frameworks.
          </p>
        </div>
      </section>

      <section className="skills__content">
        <div className="container">
          <div className="skills__grid">
            {skills.map(skill => (
              <SkillCard key={skill.id} skill={skill} />
            ))}
          </div>
        </div>
      </section>
    </main>
  );
}

export default Skills;
