import React, { useState, useEffect } from 'react';
import { GitHubCalendar } from 'react-github-calendar';
import { projectService } from '../../services/api';
import ProjectCard from '../../components/ProjectCard/ProjectCard';
import './Projects.css';

function Projects() {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const response = await projectService.getAllProjects();
        setProjects(response.data);
        setLoading(false);
      } catch (err) {
        setError('Failed to load projects');
        setLoading(false);
        console.error('Error:', err);
      }
    };

    fetchProjects();
  }, []);

  if (loading) {
    return (
      <main className="projects">
        <div className="container">
          <p>Loading projects...</p>
        </div>
      </main>
    );
  }

  if (error) {
    return (
      <main className="projects">
        <div className="container">
          <p style={{color: 'red'}}>{error}</p>
        </div>
      </main>
    );
  }

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
    </main>
  );
}

export default Projects;
