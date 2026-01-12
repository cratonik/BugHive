// Create src/api/issuesApi.js: fetchIssuesByProjectId(projectId) using apiClient.get(/projects/${projectId}/issues), fetchProjectById(id)

import apiClient from "./apiClient"

export const fetchIssuesByProjectId = async (projectId) => {
    try {
        const response = await apiClient.get(`/projects/${projectId}/issues`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching issues for project ${projectId}:`, error);
    }
    return [];
}
//fetchProjectById (id)
// Add route: path="/projects/:id" element={}.

export const fetchProjectById = async (id) => {
    try {
        const response = await apiClient.get(`/projects/${id}`);
        return response.data;
    }
    catch (error) {
        console.error(`Error fetching project with id ${id}:`, error);
    }
    return null;
}