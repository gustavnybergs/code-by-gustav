import React from 'react';
import './SkillCard.css';

// Importera Devicons
import { 
  DiJava,
  DiReact,
  DiJavascript1,
  DiMysql,
  DiGit,
  DiDatabase
} from 'react-icons/di';

// Importera Feather Icons
import { 
  FiDatabase, 
  FiTool, 
  FiLock,
  FiZap,
  FiBox,
  FiFileText,
  FiCheckSquare,
  FiCloud
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
  'MySQL': <DiMysql />,
  'PostgreSQL': <DiDatabase />,
  'Git': <DiGit />,
  'Jest': <FiCheckSquare />,
  'JUnit': <FiCheckSquare />,
  'Docker': <FiBox />,
  'Vite': <FiZap />,
  'Vercel': <FiCloud />,
  'Railway': <FiCloud />,
  'Azure': <FiCloud />
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
