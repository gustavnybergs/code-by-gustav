import React from 'react';
import { FiDownload, FiTarget, FiUsers, FiTool, FiAward } from 'react-icons/fi';
import './About.css';
import profileImage from '../../assets/images/Jag.jpg';

function About() {
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
    <main className="about">
      <section className="about__hero">
        <div className="container">
          <div className="about__hero-content">
            <div className="about__hero-text">
              <h1 className="about__title">
                From sales to fullstack development
              </h1>
              <p className="about__lead">
                I'm not your typical IT guy. My background in sales, teaching, 
                and team sports gives me perspectives that are rare in tech.
              </p>
            </div>
            <div className="about__hero-image">
              <img src={profileImage} alt="Gustav Nyberg" />
            </div>
          </div>
        </div>
      </section>

      <section className="about__story">
        <div className="container">
          <h2>My journey to code</h2>
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
      </section>

      <section className="about__strengths">
        <div className="container">
          <h2>What makes me unique</h2>
          <div className="about__strengths-grid">
            {strengths.map((strength, index) => (
              <article key={index} className="about__strength-card">
                <div className="about__strength-icon">{strength.icon}</div>
                <h3>{strength.title}</h3>
                <p>{strength.description}</p>
              </article>
            ))}
          </div>
        </div>
      </section>

      <section className="about__timeline">
        <div className="container">
          <h2>My career path</h2>
          <div className="about__timeline-list">
            {timeline.map((item, index) => (
              <article key={index} className="about__timeline-item">
                <div className="about__timeline-year">{item.year}</div>
                <div className="about__timeline-content">
                  <h3>{item.title}</h3>
                  <h4>{item.org}</h4>
                  <p>{item.description}</p>
                </div>
              </article>
            ))}
          </div>
        </div>
      </section>

      <section className="about__outside">
        <div className="container">
          <h2>Beyond the code</h2>
          <div className="about__outside-content">
            <div className="about__outside-item">
              <h3>⚽ Football</h3>
              <p>
                Have played football for over 20 years at a semi-professional level. 
                Team sports have taught me the importance of collaboration, communication, 
                and always pushing to achieve common goals.
              </p>
            </div>
            <div className="about__outside-item">
              <h3>🔨 Renovation & Interior Design</h3>
              <p>
                In my free time I love building and renovating. This is where the 
                similarity to coding became clear. Creating something from scratch, 
                solving problems along the way, and seeing the perfect end product.
              </p>
            </div>
            <div className="about__outside-item">
              <h3>��‍🍳 Cooking</h3>
              <p>
                Enjoy experimenting in the kitchen and creating new dishes. Just like 
                with coding, it's about following recipes but also daring to test 
                new combinations.
              </p>
            </div>
          </div>
        </div>
      </section>

      <section className="about__cta">
        <div className="container">
          <h2>Want to know more?</h2>
          <p>Download my resume or contact me directly!</p>
          <div className="about__cta-buttons">
            <a 
              href="/Gustav_Nyberg_CV.pdf" 
              download 
              className="btn btn--primary"
            >
              <FiDownload /> Download Resume
            </a>
            <a href="/contact" className="btn btn--secondary">
              Contact me
            </a>
          </div>
        </div>
      </section>
    </main>
  );
}

export default About;
