// Create src/pages/ProjectDetailPage.jsx: useParams for id, useState/useEffect fetch project + issues, display project name/desc + issues list (title, status, priority).

import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProjectById, fetchIssuesByProjectId } from "../api/issueApi";

const ProjectDetailPage = () =>{
    const { id } = useParams();
    const [project, setProject] = useState(null);
    const [issues, setIssues] = useState([]);

    useEffect(() =>{
        const loadProjectData = async() =>{
            const projectData = await fetchProjectById(id);
            setProject(projectData);
            
            const issuesData = await fetchIssuesByProjectId(id);
            setIssues(issuesData);
        }
    })
}