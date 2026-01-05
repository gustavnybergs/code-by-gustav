import React, { useState } from 'react';
import { contactService } from '../../services/api';
import { FiSend, FiCheck, FiAlertCircle } from 'react-icons/fi';
import './ContactForm.css';

function ContactForm() {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    subject: '',
    message: ''
  });
  const [status, setStatus] = useState({ type: '', message: '' });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const validateForm = () => {
    if (!formData.name.trim()) {
      setStatus({ type: 'error', message: 'Please enter your name' });
      return false;
    }
    if (!formData.email.trim() || !formData.email.includes('@')) {
      setStatus({ type: 'error', message: 'Please enter a valid email address' });
      return false;
    }
    if (!formData.message.trim()) {
      setStatus({ type: 'error', message: 'Please write a message' });
      return false;
    }
    return true;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!validateForm()) {
      return;
    }

    // Optimistiskt UI - visa success direkt
    setStatus({ 
      type: 'success', 
      message: 'Thank you for your message! I\'ll get back to you as soon as possible.' 
    });
    
    // Spara formdata och rensa formuläret direkt
    const messageData = { ...formData };
    setFormData({ name: '', email: '', subject: '', message: '' });

    // Skicka till backend i bakgrunden
    contactService.sendMessage(messageData)
      .catch(error => {
        console.error('Error sending message:', error);
        setStatus({ 
          type: 'error', 
          message: 'Something went wrong. Please try again or contact me directly via email.' 
        });
      });
  };

  return (
    <form className="contact-form" onSubmit={handleSubmit}>
      {status.message && (
        <div className={`contact-form__status contact-form__status--${status.type}`}>
          {status.type === 'success' ? <FiCheck /> : <FiAlertCircle />}
          <span>{status.message}</span>
        </div>
      )}

      <div className="contact-form__group">
        <label htmlFor="name" className="contact-form__label">
          Name *
        </label>
        <input
          type="text"
          id="name"
          name="name"
          className="contact-form__input"
          value={formData.name}
          onChange={handleChange}
          required
        />
      </div>

      <div className="contact-form__group">
        <label htmlFor="email" className="contact-form__label">
          Email *
        </label>
        <input
          type="email"
          id="email"
          name="email"
          className="contact-form__input"
          value={formData.email}
          onChange={handleChange}
          required
        />
      </div>

      <div className="contact-form__group">
        <label htmlFor="subject" className="contact-form__label">
          Subject
        </label>
        <input
          type="text"
          id="subject"
          name="subject"
          className="contact-form__input"
          value={formData.subject}
          onChange={handleChange}
        />
      </div>

      <div className="contact-form__group">
        <label htmlFor="message" className="contact-form__label">
          Message *
        </label>
        <textarea
          id="message"
          name="message"
          className="contact-form__textarea"
          rows="6"
          value={formData.message}
          onChange={handleChange}
          required
        />
      </div>

      <button 
        type="submit" 
        className="contact-form__submit btn btn--primary"
      >
        <FiSend /> Send message
      </button>
    </form>
  );
}

export default ContactForm;
