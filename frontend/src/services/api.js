import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const projectService = {
  getAllProjects: () => api.get('/projects'),
  getFeaturedProjects: () => api.get('/projects/featured'),
  getProjectById: (id) => api.get(`/projects/${id}`),
};

export const skillService = {
  getAllSkills: () => api.get('/skills'),
  getSkillsByCategory: (category) => api.get(`/skills/category/${category}`),
};

export const contactService = {
  sendMessage: (message) => api.post('/contact', message),
};

export default api;
