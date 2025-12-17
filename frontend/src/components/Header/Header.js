import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';
import logo from '../../assets/images/gn.jpg';

function Header() {
  return (
    <header className="header">
      <div className="header__container">
        <Link to="/" className="header__logo">
          <img src={logo} alt="Code by Gustav" />
        </Link>
        
        <nav className="header__nav">
          <Link to="/" className="header__nav-link">Home</Link>
          <Link to="/projects" className="header__nav-link">Projects</Link>
          <Link to="/skills" className="header__nav-link">Skills</Link>
          <Link to="/about" className="header__nav-link">About</Link>
          <Link to="/contact" className="header__nav-link">Contact</Link>
        </nav>
      </div>
    </header>
  );
}

export default Header;
