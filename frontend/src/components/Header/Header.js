import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { FiSun, FiMoon } from 'react-icons/fi';
import './Header.css';
import blackLogo from '../../assets/images/cbg-black.png';
import whiteLogo from '../../assets/images/cbg-white.png';

function Header() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isDarkMode, setIsDarkMode] = useState(() => {
    const saved = localStorage.getItem('theme');
    return saved === 'dark';
  });

  useEffect(() => {
    document.documentElement.setAttribute('data-theme', isDarkMode ? 'dark' : 'light');
    localStorage.setItem('theme', isDarkMode ? 'dark' : 'light');
  }, [isDarkMode]);

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const closeMenu = () => {
    setIsMenuOpen(false);
  };

  const toggleTheme = () => {
    setIsDarkMode(!isDarkMode);
  };

  return (
      <header className="header">
        <div className="header__container">
          <Link to="/about" className="header__logo" onClick={closeMenu}>
            <img src={isDarkMode ? whiteLogo : blackLogo} alt="Code by Gustav" />
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
            <button
                className="header__theme-toggle"
                onClick={toggleTheme}
                aria-label="Toggle dark mode"
            >
              {isDarkMode ? <FiSun /> : <FiMoon />}
            </button>
          </nav>
        </div>
      </header>
  );
}

export default Header;