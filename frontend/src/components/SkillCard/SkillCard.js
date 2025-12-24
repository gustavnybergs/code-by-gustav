import React from 'react';
import './SkillCard.css';

// Importera Devicons
import { 
  DiJava,
  DiReact,
  DiJavascript1,
  DiHtml5,
  DiCss3,
  DiMysql,
  DiGit,
  DiGithubBadge
} from 'react-icons/di';

// Importera Feather Icons
import { 
  FiCode,
  FiDatabase, 
  FiTool, 
  FiUsers, 
  FiTarget,
  FiLock,
  FiZap,
  FiSettings,
  FiBox,
  FiFileText,
  FiCheckSquare
} from 'react-icons/fi';

const iconMap = {
  'Java': <DiJava />,
  'Spring Boot': <FiBox />,
  'Spring Security': <FiLock />,
  'REST API': <FiZap />,
  'JPA/Hibernate': <FiDatabase />,
  'React': <DiReact />,
  'JavaScript': <DiJavascript1 />,
  'TypeScript': <FiFileText />,
  'HTML5': <DiHtml5 />,
  'CSS3': <DiCss3 />,
  'MySQL': <DiMysql />,
  'SQL': <FiDatabase />,
  'Git': <DiGit />,
  'GitHub': <DiGithubBadge />,
  'GitHub Actions': <FiSettings />,
  'Jest': <FiCheckSquare />,
  'JUnit': <FiCheckSquare />,
  'Agile/Scrum': <FiUsers />,
  'Problem Solving': <FiTarget />
};

function SkillCard({ skill }) {
  const icon = iconMap[skill.name] || <FiTool />;
  
  return (
    <article className="skill-card">
      <div className="skill-card__icon">
        {icon}
      </div>
      <h3 className="skill-card__name">{skill.name}</h3>
      <p className="skill-card__category">{skill.category}</p>
    </article>
  );
}

export default SkillCard;
