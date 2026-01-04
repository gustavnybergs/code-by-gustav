import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './Header.css';
import logo from '../../assets/images/gn.jpg';

function Header() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const closeMenu = () => {
    setIsMenuOpen(false);
  };

  return (
      <header className="header">
        <div className="header__container">
          <Link to="/about" className="header__logo" onClick={closeMenu}>
            <img src={logo} alt="Code by Gustav" />
          </Link>

          <button
              className={`header__hamburger ${isMenuOpen ? 'header__hamburger--active' : ''}`}
              onClick={toggleMenu}
              aria-label="Toggle menu"
          >
            <span></span>
            <span></span>
            <span></span>
          </button>

          <nav className={`header__nav ${isMenuOpen ? 'header__nav--open' : ''}`}>
            <Link to="/about" className="header__nav-link" onClick={closeMenu}>About</Link>
            <Link to="/projects" className="header__nav-link" onClick={closeMenu}>Projects</Link>
            <Link to="/contact" className="header__nav-link" onClick={closeMenu}>Contact</Link>
          </nav>
        </div>
      </header>
  );
}

export default Header;