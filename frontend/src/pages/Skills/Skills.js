import React, { useState, useEffect } from 'react';
import { skillService } from '../../services/api';
import SkillCard from '../../components/SkillCard/SkillCard';
import './Skills.css';

function Skills() {
  const [skills, setSkills] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const response = await skillService.getAllSkills();
        setSkills(response.data);
        setLoading(false);
      } catch (err) {
        setError('Failed to load skills');
        setLoading(false);
        console.error('Error:', err);
      }
    };

    fetchSkills();
  }, []);

  const groupSkillsByCategory = () => {
    const grouped = {};
    skills.forEach(skill => {
      if (!grouped[skill.category]) {
        grouped[skill.category] = [];
      }
      grouped[skill.category].push(skill);
    });
    return grouped;
  };

  if (loading) {
    return (
      <main className="skills">
        <div className="container">
          <p>Loading skills...</p>
        </div>
      </main>
    );
  }

  if (error) {
    return (
      <main className="skills">
        <div className="container">
          <p style={{color: 'red'}}>{error}</p>
        </div>
      </main>
    );
  }

  const groupedSkills = groupSkillsByCategory();

  return (
    <main className="skills">
      <section className="skills__hero">
        <div className="container">
          <h1 className="skills__title">My Skills</h1>
          <p className="skills__subtitle">
            Technologies and tools I work with. From backend development 
            to modern frontend frameworks.
          </p>
        </div>
      </section>

      <section className="skills__content">
        <div className="container">
          {Object.keys(groupedSkills).sort().map(category => (
            <div key={category} className="skills__category">
              <h2 className="skills__category-title">{category}</h2>
              <div className="skills__grid">
                {groupedSkills[category].map(skill => (
                  <SkillCard key={skill.id} skill={skill} />
                ))}
              </div>
            </div>
          ))}
        </div>
      </section>
    </main>
  );
}

export default Skills;
