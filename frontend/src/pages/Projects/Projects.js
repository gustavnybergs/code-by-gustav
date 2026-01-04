import React from 'react';
import { GitHubCalendar } from 'react-github-calendar';
import ProjectCard from '../../components/ProjectCard/ProjectCard';
import SkillCard from '../../components/SkillCard/SkillCard';
import './Projects.css';

function Projects() {
  // Hårdkodad projektdata, statisk data
  const projects = [
    {
      id: 1,
      title: "Code by Gustav - Portfolio",
      description: "Fullstack portfolio website built with React, Spring Boot and PostgreSQL. Features project showcase, skills display, and contact form with RabbitMQ message queue.",
      githubUrl: "https://github.com/gustavnybergs/code-by-gustav",
      liveUrl: "https://code-by-gustav.vercel.app",
      imageUrl: null,
      featured: true,
      createdDate: "2024-12-01"
    },
    {
      id: 2,
      title: "Weather Service with Authentication",
      description: "Weather application with API integration and user authentication system. Built as a school project demonstrating Spring Boot backend skills and secure login implementation.",
      githubUrl: "https://github.com/gustavnybergs/weather-service-auth",
      liveUrl: null,
      imageUrl: null,
      featured: false,
      createdDate: "2024-10-15"
    },
    {
      id: 3,
      title: "Agile Project - Java & UX Collaboration",
      description: "Collaborative project between Java and UX classes, showcasing agile development methodology and cross-functional teamwork.",
      githubUrl: "https://github.com/Elie0825/Grupp-5-Agila-projektet-",
      liveUrl: "https://grupp-5-agila-projektet.vercel.app/",
      imageUrl: null,
      featured: false,
      createdDate: "2024-09-20"
    }
  ];

  // Hårdkodad skills-data
  const skills = [
    { id: 1, name: "Java", category: "Backend" },
    { id: 2, name: "Spring Boot", category: "Backend" },
    { id: 3, name: "JPA/Hibernate", category: "Backend" },
    { id: 4, name: "REST API", category: "Backend" },
    { id: 5, name: "Spring Security", category: "Backend" },
    { id: 6, name: "React", category: "Frontend" },
    { id: 7, name: "JavaScript", category: "Frontend" },
    { id: 8, name: "TypeScript", category: "Frontend" },
    { id: 9, name: "PostgreSQL", category: "Database" },
    { id: 10, name: "MySQL", category: "Database" },
    { id: 11, name: "Jest", category: "Testing" },
    { id: 12, name: "JUnit", category: "Testing" },
    { id: 13, name: "Docker", category: "DevOps" },
    { id: 14, name: "Git", category: "Tools" },
    { id: 15, name: "Vite", category: "Tools" },
    { id: 16, name: "Vercel", category: "Tools" },
    { id: 17, name: "Railway", category: "Tools" },
    { id: 18, name: "Render", category: "Tools" },
    { id: 19, name: "Azure", category: "Tools" }
  ];

  return (
      <main className="projects">
        <section className="projects__hero">
          <div className="container">
            <h1 className="projects__title">My Projects</h1>
            <p className="projects__subtitle">
              A selection of projects I've worked on. From fullstack applications
              to frontend experiments.
            </p>
          </div>
        </section>

        <section className="projects__github-activity">
          <div className="container">
            <h2 className="projects__section-title">GitHub Activity</h2>
            <p className="projects__section-subtitle">
              My coding journey over the past year
            </p>
            <div className="projects__calendar-wrapper">
              <GitHubCalendar
                  username="gustavnybergs"
                  blockSize={12}
                  blockMargin={4}
                  fontSize={14}
              />
            </div>
          </div>
        </section>

        <section className="projects__grid-section">
          <div className="container">
            <div className="projects__grid">
              {projects.map((project) => (
                  <ProjectCard key={project.id} project={project} />
              ))}
            </div>
          </div>
        </section>

        <section className="projects__skills-section">
          <div className="container">
            <h2 className="projects__section-title">Technologies & Tools</h2>
            <p className="projects__section-subtitle">
              Tech stack I work with across my projects
            </p>
            <div className="projects__skills-grid">
              {skills.map((skill) => (
                  <SkillCard key={skill.id} skill={skill} />
              ))}
            </div>
          </div>
        </section>
      </main>
  );
}

export default Projects;