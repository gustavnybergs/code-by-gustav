import React from 'react';
import { Link } from 'react-router-dom';
import { FiGithub, FiLinkedin, FiMail } from 'react-icons/fi';
import './Footer.css';

function Footer() {
  const currentYear = new Date().getFullYear();

  return (
    <footer className="footer">
      <div className="container">
        <div className="footer__content">
          <div className="footer__section">
            <h3 className="footer__title">Gustav Nyberg</h3>
            <p className="footer__description">
              Java developer with a passion for clean code and user-friendly solutions.
            </p>
          </div>

          <div className="footer__section">
            <h4 className="footer__subtitle">Quick Links</h4>
            <nav className="footer__nav">
              <Link to="/" className="footer__link">Home</Link>
              <Link to="/projects" className="footer__link">Projects</Link>
              <Link to="/skills" className="footer__link">Skills</Link>
              <Link to="/about" className="footer__link">About</Link>
              <Link to="/contact" className="footer__link">Contact</Link>
            </nav>
          </div>

          <div className="footer__section">
            <h4 className="footer__subtitle">Follow Me</h4>
            <div className="footer__social">
              <a 
                href="https://github.com/gustavnybergs" 
                target="_blank" 
                rel="noopener noreferrer"
                className="footer__social-link"
                aria-label="GitHub"
              >
                <FiGithub />
              </a>
              <a 
                href="https://linkedin.com/in/gustavnyberg" 
                target="_blank" 
                rel="noopener noreferrer"
                className="footer__social-link"
                aria-label="LinkedIn"
              >
                <FiLinkedin />
              </a>
              <a 
                href="mailto:gustavnybergs@gmail.com"
                className="footer__social-link"
                aria-label="Email"
              >
                <FiMail />
              </a>
            </div>
          </div>
        </div>

        <div className="footer__bottom">
          <p className="footer__copyright">
            © {currentYear} Gustav Nyberg. All rights reserved.
          </p>
          <p className="footer__built">
            Built with React & Spring Boot
          </p>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
