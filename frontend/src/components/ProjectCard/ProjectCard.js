import React from 'react';
import { Link } from 'react-router-dom';
import './ProjectCard.css';

function ProjectCard({ project }) {
  return (
    <article className="project-card">
      <Link to={`/projects/${project.id}`} className="project-card__link">
        <div className="project-card__image">
          <img src={project.imageUrl} alt={project.title} />
          {project.featured && (
            <span className="project-card__badge">Featured</span>
          )}
        </div>
        
        <div className="project-card__content">
          <h3 className="project-card__title">{project.title}</h3>
          <p className="project-card__description">{project.description}</p>
          
          <div className="project-card__tech">
            {project.technologies.slice(0, 3).map((tech, index) => (
              <span key={index} className="project-card__tech-badge">
                {tech}
              </span>
            ))}
            {project.technologies.length > 3 && (
              <span className="project-card__tech-badge">
                +{project.technologies.length - 3}
              </span>
            )}
          </div>
        </div>
      </Link>
    </article>
  );
}

export default ProjectCard;
