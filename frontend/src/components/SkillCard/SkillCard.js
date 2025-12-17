import React from 'react';
import './SkillCard.css';

function SkillCard({ skill }) {
  return (
    <article className="skill-card">
      <div className="skill-card__icon">
        {skill.icon}
      </div>
      <div className="skill-card__content">
        <h3 className="skill-card__name">{skill.name}</h3>
        <div className="skill-card__level">
          <div className="skill-card__level-bar">
            <div 
              className="skill-card__level-fill"
              style={{ width: `${skill.proficiencyLevel}%` }}
            />
          </div>
          <span className="skill-card__level-text">{skill.proficiencyLevel}%</span>
        </div>
      </div>
    </article>
  );
}

export default SkillCard;
