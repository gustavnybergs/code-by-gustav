import axios from 'axios';

// Använd environment variable, fallback till localhost
const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Contact endpoints
export const contactService = {
  sendMessage: (message) => api.post('/contact', message),
};

export default api;
