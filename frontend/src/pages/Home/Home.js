import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';
import profileImage from '../../assets/images/Jag.jpg';

function Home() {
  return (
    <main className="home">
      <section className="hero">
        <div className="hero__container">
          <div className="hero__content">
            <div className="hero__text">
              <h1 className="hero__title">
                <span className="hero__subtitle">Fullstack Developer</span>
                Gustav Nyberg
              </h1>
              <p className="hero__description">
                I create modern web applications with a focus on clean code, 
                user experience, and scalability. Specializing in Java Spring Boot 
                and React.
              </p>
              <div className="hero__cta">
                <Link to="/projects" className="btn btn--primary">
                  View my projects
                </Link>
                <Link to="/contact" className="btn btn--secondary">
                  Get in touch
                </Link>
              </div>
            </div>
            
            <div className="hero__image">
              <img src={profileImage} alt="Gustav Nyberg" />
            </div>
          </div>
        </div>
      </section>

      <section className="home__intro">
        <div className="container">
          <h2>Welcome to my portfolio</h2>
          <p>
            Here you'll find a selection of my projects, skills, and a bit about who I am. 
            Feel free to reach out if you have any questions or are interested in collaborating!
          </p>
        </div>
      </section>
    </main>
  );
}

export default Home;
