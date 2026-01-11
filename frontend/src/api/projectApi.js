import apiClient from "./apiClient"

export const fetchProjects = async () => {
   
    try{
         const response = await apiClient.get('/projects');
        return response.data;
    }catch(error){
        console.error("Error fetching projects:", error);
    }
    return [];
}