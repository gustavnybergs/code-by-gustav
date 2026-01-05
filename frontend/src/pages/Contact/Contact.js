import React from 'react';
import { FiMail, FiLinkedin, FiGithub } from 'react-icons/fi';
import ContactForm from '../../components/ContactForm/ContactForm';
import './Contact.css';

function Contact() {
  return (
    <main className="contact">
      <section className="contact__hero">
        <div className="container">
          <h1 className="contact__title">Get in Touch</h1>
          <p className="contact__subtitle">
            Have a question, project proposal, or just want to say hi? 
            Fill out the form below and I'll get back to you as soon as possible!
          </p>
        </div>
      </section>

      <section className="contact__content">
        <div className="container">
          <div className="contact__grid">
            <div className="contact__form-section">
              <h2>Send a message</h2>
              <ContactForm />
            </div>

            <div className="contact__info-section">
              <h2>Other ways to reach me</h2>
              
              <a 
                href="mailto:gustavnybergs@outlook.com"
                className="contact__info-card"
              >
                <div className="contact__info-icon">
                  <FiMail />
                </div>
                <div className="contact__info-content">
                  <h3>Email</h3>
                  <p>gustavnybergs@outlook.com</p>
                </div>
              </a>

              <a 
                href="https://linkedin.com/in/gustavnyberg" 
                target="_blank" 
                rel="noopener noreferrer"
                className="contact__info-card"
              >
                <div className="contact__info-icon">
                  <FiLinkedin />
                </div>
                <div className="contact__info-content">
                  <h3>LinkedIn</h3>
                  <p>linkedin.com/in/gustavnyberg</p>
                </div>
              </a>

              <a 
                href="https://github.com/gustavnybergs" 
                target="_blank" 
                rel="noopener noreferrer"
                className="contact__info-card"
              >
                <div className="contact__info-icon">
                  <FiGithub />
                </div>
                <div className="contact__info-content">
                  <h3>GitHub</h3>
                  <p>github.com/gustavnybergs</p>
                </div>
              </a>

              <div className="contact__availability">
                <h3>Availability</h3>
                <p>
                  I'm open to full-time employment from summer 2026 after my graduation.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </main>
  );
}

export default Contact;
