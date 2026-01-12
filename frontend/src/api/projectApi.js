import apiClient from "./apiClient"

export const fetchProjects = async () => {
    try{
        // Fetch data from db
        const response = await apiClient.get('/projects');
        return response.data;
    }catch(error){
        console.error("Error fetching projects:", error);
    }
    return [];
}

export const fetchProjectById = async (id) => {
    try{
        // Fetch data from db
        const response = await apiClient.get(`/projects/${id}`);
        return response.data;
    }catch(error){
        console.error("Error fetching projects:", error);
    }
    return [];
}

