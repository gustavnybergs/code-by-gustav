import React from 'react';
import { Link } from 'react-router-dom';
import { FiGithub, FiExternalLink, FiLock } from 'react-icons/fi';
import './ProjectCard.css';

function ProjectCard({ project }) {
  const isPrivateRepo = !project.githubUrl || project.githubUrl === 'private';

  return (
    <article className="project-card">
      <div className="project-card__image">
        <img src={project.imageUrl} alt={project.title} />
        {project.featured && (
          <span className="project-card__badge">Featured</span>
        )}
      </div>
      
      <div className="project-card__content">
        <Link to={`/projects/${project.id}`} className="project-card__title-link">
          <h3 className="project-card__title">{project.title}</h3>
        </Link>
        <p className="project-card__description">{project.description}</p>

        <div className="project-card__actions">
          {project.liveUrl && (
            <a 
              href={project.liveUrl} 
              target="_blank" 
              rel="noopener noreferrer"
              className="project-card__button project-card__button--primary"
            >
              <FiExternalLink size={16} />
              Live Demo
            </a>
          )}
          
          {isPrivateRepo ? (
            <span className="project-card__button project-card__button--disabled">
              <FiLock size={16} />
              Private Repo
            </span>
          ) : (
            <a 
              href={project.githubUrl} 
              target="_blank" 
              rel="noopener noreferrer"
              className="project-card__button project-card__button--secondary"
            >
              <FiGithub size={16} />
              View Code
            </a>
          )}
        </div>
      </div>
    </article>
  );
}

export default ProjectCard;
