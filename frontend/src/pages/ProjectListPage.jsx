import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { fetchProjects } from "../api/projectApi";
import { fetchIssuesByProjectId } from "../api/issueApi";

const ProjectsListPage = () => {

    const [projects, setProjects] = useState([]);
    const [issueCounts, setIssueCounts] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        const loadData = async () => {
            const projectData = await fetchProjects();
            setProjects(projectData);

            const counts = {};
            for (const project of projectData) {
                const issues = await fetchIssuesByProjectId(project.id);
                counts[project.id] = issues.length;
            }
            setIssueCounts(counts);
        };

        loadData();
    }, []);

    return (
        <div className="min-h-screen bg-background p-8">
            <h1 className="text-3xl font-bold text-text mb-6">Projects</h1>

            <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                {projects.map(project => (
                    <div
                        key={project.id}
                        onClick={() => navigate(`/projects/${project.id}`)}
                        className="bg-white p-6 rounded-xl shadow-md cursor-pointer hover:shadow-xl transition border border-gray-200"
                    >
                        <div className="flex justify-between items-center">
                            <h2 className="text-lg font-semibold text-primary">
                                {project.name}
                            </h2>

                            <span className="bg-primary text-white text-sm px-3 py-1 rounded-full">
                                {issueCounts[project.id] || 0} issues
                            </span>
                        </div>

                        <p className="text-muted mt-2">
                            {project.description}
                        </p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProjectsListPage;
