import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { FiChevronDown, FiDownload, FiTarget, FiUsers, FiTool, FiAward } from 'react-icons/fi';
import './About.css';
import profileImage from '../../assets/images/jag_avatar.png';
import pekarHalv from '../../assets/images/pekar-halvrak-arm.png';
import pekarRak from '../../assets/images/pekar-rak-arm.png';


function About() {
  const [currentFrame, setCurrentFrame] = useState(0);

  const avatarFrames = [
    pekarHalv,
    pekarRak,
    pekarHalv,
    pekarRak,
    pekarHalv,
    pekarRak,
    pekarHalv,
    pekarRak
  ];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentFrame((prev) => (prev + 1) % avatarFrames.length);
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  const scrollToAbout = () => {
    document.getElementById('about-section').scrollIntoView({
      behavior: 'smooth'
    });
  };

  const strengths = [
    {
      icon: <FiUsers />,
      title: "Social & Communicative",
      description: "From customer service and sales, I've learned to adapt my communication to any audience. I can explain technical solutions in a way everyone understands."
    },
    {
      icon: <FiTarget />,
      title: "Results-Oriented",
      description: "Consistently ranked among the top sellers at Telenor. I complete what I start and always deliver with high quality."
    },
    {
      icon: <FiTool />,
      title: "Problem Solver",
      description: "Just like in renovations or on the football field. I see challenges as opportunities to learn something new and improve."
    },
    {
      icon: <FiAward />,
      title: "Detail-Focused",
      description: "The small finishing touches that make the difference. I invest time in details that create a professional end product I can be proud of."
    }
  ];

  const timeline = [
    { year: "2024 - Present", title: "Java Developer Student", org: "Stockholm Technical Institute", description: "Studying fullstack development with Java, Spring Boot, React and cloud services. Top grades in all completed courses." },
    { year: "2024 - Present", title: "B2B Sales Representative", org: "Easypartner Stockholm", description: "Corporate sales in telecom parallel to studies." },
    { year: "2021 - 2023", title: "Sales Customer Service", org: "Telenor", description: "Consistently ranked among the top sellers nationally. Almost never missed budget despite working 75-85%." },
    { year: "2020 - 2021", title: "Social Studies & PE Teacher", org: "Fårboskolan, Oskarshamn", description: "Developed pedagogical skills and patience working with students grade 4-6." },
    { year: "2019 - 2020", title: "PE Teacher & Student Assistant", org: "Kristinebergsskolan", description: "Worked with students needing extra support, grades K-9." },
  ];

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

            <div className="hero__scroll-wrapper">
              <img
                  src={avatarFrames[currentFrame]}
                  alt="Scroll guide"
                  className="hero__scroll-avatar-pointer"
              />
              <div className="hero__scroll-content">
                <button
                    className="hero__scroll-indicator"
                    onClick={scrollToAbout}
                    aria-label="Scroll to about section"
                >
                  <FiChevronDown />
                </button>
                <span className="hero__scroll-text">Learn more about me</span>
              </div>
            </div>
          </div>
        </section>

        <section id="about-section" className="about">
          <div className="container">
            <div className="about__hero-content">
              <h2 className="about__title">From sales to fullstack development</h2>
              <p className="about__lead">
                I'm not your typical IT guy. My background in sales, teaching,
                and team sports gives me perspectives that are rare in tech.
              </p>
            </div>
          </div>

          <div className="container">
            <h3>My journey to code</h3>
            <div className="about__story-content">
              <p>
                During my years in sales and customer service at Telenor, I consistently
                ranked among the country's top sellers. This despite working 75-85%.
                But I realized sales wasn't something I wanted to do for the rest of my life.
                So I started looking around for what could be next.
              </p>
              <p>
                I've always loved renovating and building in my spare time. Planning a project,
                encountering problems, finding solutions, and finally seeing the perfect solution.
                That's where I thrive. One day I realized that coding is exactly the same thing,
                just digital.
              </p>
              <p>
                With 20+ years of football at a semi-professional level, I've learned the
                importance of teamwork, discipline, and never giving up when things get tough.
                The combination of my experience from sales, teaching, and team sports makes
                me unique in the IT industry.
              </p>
              <p>
                Today I'm studying Java development at Stockholm Technical Institute with
                top grades in all completed courses. I'll graduate in spring 2026. I'm ready
                to bring everything I've learned from my previous career into the tech world.
              </p>
            </div>
          </div>

          <div className="container">
            <h3>What makes me unique</h3>
            <div className="about__strengths-grid">
              {strengths.map((strength, index) => (
                  <article key={index} className="about__strength-card">
                    <div className="about__strength-icon">{strength.icon}</div>
                    <h4>{strength.title}</h4>
                    <p>{strength.description}</p>
                  </article>
              ))}
            </div>
          </div>

          <div className="container">
            <h3>My career path</h3>
            <div className="about__timeline-list">
              {timeline.map((item, index) => (
                  <article key={index} className="about__timeline-item">
                    <div className="about__timeline-year">{item.year}</div>
                    <div className="about__timeline-content">
                      <h4>{item.title}</h4>
                      <h5>{item.org}</h5>
                      <p>{item.description}</p>
                    </div>
                  </article>
              ))}
            </div>
          </div>

          <div className="container">
            <h3>Beyond the code</h3>
            <div className="about__outside-content">
              <div className="about__outside-item">
                <h4>Football</h4>
                <p>
                  Have played football for over 20 years at a semi-professional level.
                  Team sports have taught me the importance of collaboration, communication,
                  and always pushing to achieve common goals.
                </p>
              </div>
              <div className="about__outside-item">
                <h4>Renovation & Interior Design</h4>
                <p>
                  In my free time I love building and renovating. This is where the
                  similarity to coding became clear. Creating something from scratch,
                  solving problems along the way, and seeing the perfect end product.
                </p>
              </div>
              <div className="about__outside-item">
                <h4>Cooking</h4>
                <p>
                  Enjoy experimenting in the kitchen and creating new dishes. Just like
                  with coding, it's about following recipes but also daring to test
                  new combinations.
                </p>
              </div>
            </div>
          </div>

          <div className="container about__cta">
            <h3>Want to know more?</h3>
            <p>Download my resume or contact me directly!</p>
            <div className="about__cta-buttons">
              <a
                  href="/gustav-nyberg-cv.pdf"
                  download
                  className="btn btn--primary"
              >
                <FiDownload /> Download Resume
              </a>
              <Link to="/contact" className="btn btn--secondary">
                Contact me
              </Link>
            </div>
          </div>
        </section>
      </main>
  );
}

export default About;