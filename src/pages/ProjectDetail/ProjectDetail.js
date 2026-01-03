import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { projectService } from '../../services/api';
import { FiGithub, FiExternalLink, FiArrowLeft, FiChevronLeft, FiChevronRight } from 'react-icons/fi';
import './ProjectDetail.css';

function ProjectDetail() {
  const { id } = useParams();
  const [project, setProject] = useState(null);
  const [allProjects, setAllProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const [projectResponse, allProjectsResponse] = await Promise.all([
          projectService.getProjectById(id),
          projectService.getAllProjects()
        ]);
        
        setProject(projectResponse.data);
        setAllProjects(allProjectsResponse.data);
        setLoading(false);
        window.scrollTo(0, 0);
      } catch (err) {
        setError('Failed to load project');
        setLoading(false);
        console.error('Error:', err);
      }
    };

    fetchData();
  }, [id]);

  const getCurrentIndex = () => {
    return allProjects.findIndex(p => p.id === parseInt(id));
  };

  const getPreviousProject = () => {
    const currentIndex = getCurrentIndex();
    return currentIndex > 0 ? allProjects[currentIndex - 1] : null;
  };

  const getNextProject = () => {
    const currentIndex = getCurrentIndex();
    return currentIndex < allProjects.length - 1 ? allProjects[currentIndex + 1] : null;
  };

  if (loading) {
    return (
      <main className="project-detail">
        <div className="container">
          <p>Loading project...</p>
        </div>
      </main>
    );
  }

  if (error || !project) {
    return (
      <main className="project-detail">
        <div className="container">
          <p style={{color: 'red'}}>{error || 'Project not found'}</p>
          <Link to="/projects" className="btn btn--secondary">Back to projects</Link>
        </div>
      </main>
    );
  }

  const previousProject = getPreviousProject();
  const nextProject = getNextProject();

  return (
    <main className="project-detail">
      {previousProject && (
        <div className="project-detail__side-nav project-detail__side-nav--left">
          <Link 
            to={`/projects/${previousProject.id}`}
            className="project-detail__arrow-btn"
            title={`Previous: ${previousProject.title}`}
          >
            <FiChevronLeft />
          </Link>
        </div>
      )}

      {nextProject && (
        <div className="project-detail__side-nav project-detail__side-nav--right">
          <Link 
            to={`/projects/${nextProject.id}`}
            className="project-detail__arrow-btn"
            title={`Next: ${nextProject.title}`}
          >
            <FiChevronRight />
          </Link>
        </div>
      )}

      <div className="project-detail__hero">
        <div className="container">
          <Link to="/projects" className="project-detail__back">
            <FiArrowLeft /> Back to projects
          </Link>
          
          <h1 className="project-detail__title">{project.title}</h1>
          <p className="project-detail__description">{project.description}</p>
          
          <div className="project-detail__links">
            {project.githubUrl && (
              <a 
                href={project.githubUrl} 
                target="_blank" 
                rel="noopener noreferrer"
                className="btn btn--primary"
              >
                <FiGithub /> View on GitHub
              </a>
            )}
            {project.liveUrl && (
              <a 
                href={project.liveUrl} 
                target="_blank" 
                rel="noopener noreferrer"
                className="btn btn--secondary"
              >
                <FiExternalLink /> Live Demo
              </a>
            )}
          </div>
        </div>
      </div>

      <div className="project-detail__content">
        <div className="container">
          <div className="project-detail__image">
            <img src={project.imageUrl} alt={project.title} />
          </div>

          <div className="project-detail__info">
            <section className="project-detail__section">
              <h2>About the project</h2>
              <p>{project.longDescription}</p>
            </section>

            <section className="project-detail__section">
              <h2>Technologies</h2>
              <div className="project-detail__tech">
                {project.technologies.map((tech, index) => (
                  <span key={index} className="project-detail__tech-badge">
                    {tech}
                  </span>
                ))}
              </div>
            </section>

            {project.createdDate && (
              <section className="project-detail__section">
                <h2>Created</h2>
                <p>{new Date(project.createdDate).toLocaleDateString('en-US', {
                  year: 'numeric',
                  month: 'long'
                })}</p>
              </section>
            )}
          </div>
        </div>
      </div>
    </main>
  );
}

export default ProjectDetail;
