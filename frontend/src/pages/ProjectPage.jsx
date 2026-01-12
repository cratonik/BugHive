import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProjectById } from "../api/projectApi";

const ProjectPage = () => {
    const { id } = useParams();

    const [project, setProject] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadProject = async () => {
            const data = await fetchProjectById(id);
            setProject(data);
            setLoading(false);
        };
        loadProject();
    }, [id]);

    if (loading) {
        return <div className="p-8 text-muted">Loading...</div>;
    }

    if (!project) {
        return <div className="p-8 text-danger">Project not found</div>;
    }

    return (
        <div className="min-h-screen bg-background p-8">
            <div className="bg-white p-6 rounded-xl shadow-md">
                <h1 className="text-3xl font-bold text-primary">
                    {project.name}
                </h1>
                <p className="text-muted mt-2">
                    {project.description}
                </p>
            </div>
        </div>
    );
};

export default ProjectPage;
