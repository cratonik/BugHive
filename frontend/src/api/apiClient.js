import axios from 'axios';

export const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    timeout: 5000,
    headers:{
        'Content-Type': 'application/json',
    }
})

export default apiClient;
