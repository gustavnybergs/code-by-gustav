import axios from 'axios';

// Använd environment variable, fallback till localhost
const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Project endpoints
export const projectService = {
  getAllProjects: () => api.get('/projects'),
  getFeaturedProjects: () => api.get('/projects/featured'),
  getProjectById: (id) => api.get(`/projects/${id}`),
};

// Skill endpoints
export const skillService = {
  getAllSkills: () => api.get('/skills'),
  getSkillsByCategory: (category) => api.get(`/skills/category/${category}`),
};

// Contact endpoints
export const contactService = {
  sendMessage: (message) => api.post('/contact', message),
};

export default api;
