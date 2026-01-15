import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProjectById } from "../api/projectApi";
import { fetchIssuesByProjectId } from "../api/issueApi";

const ProjectPage = () => {
    const { id } = useParams();

    const [project, setProject] = useState(null);
    const [loading, setLoading] = useState(true);
    const [issues, setIssues] = useState([]);

    useEffect(() => {
        const loadProject = async () => {
            const data = await fetchProjectById(id);
            setProject(data);
            const issueData = await fetchIssuesByProjectId(id);
            setIssues(issueData);
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

    const open = issues.filter(i => i.status === "OPEN").length;
    const inProgress = issues.filter(i => i.status === "IN_PROGRESS").length;
    const done = issues.filter(i => i.status === "DONE").length;


    return (
        <div className="min-h-screen bg-background p-8">

            {/* Project Header */}
            <div className="bg-white p-6 rounded-xl shadow-md mb-8">
                <h1 className="text-3xl font-bold text-primary">
                    {project.name}
                </h1>
                <p className="text-muted mt-2">
                    {project.description}
                </p>
            </div>

            {/* Issue Stats */}
            <div className="grid grid-cols-3 gap-6 mb-8">
                <div className="bg-white p-4 rounded-xl shadow text-center">
                    <p className="text-muted">Open</p>
                    <p className="text-2xl font-bold text-danger">{open}</p>
                </div>
                <div className="bg-white p-4 rounded-xl shadow text-center">
                    <p className="text-muted">In Progress</p>
                    <p className="text-2xl font-bold text-primary">{inProgress}</p>
                </div>
                <div className="bg-white p-4 rounded-xl shadow text-center">
                    <p className="text-muted">Done</p>
                    <p className="text-2xl font-bold text-success">{done}</p>
                </div>
            </div>

            {/* Issues List */}
            <h2 className="text-xl font-semibold text-text mb-4">Issues</h2>

            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                {issues.map(issue => (
                    <div
                        key={issue.id}
                        className="bg-white p-4 rounded-lg shadow border-l-4 border-primary"
                    >
                        <h3 className="font-semibold text-text">
                            {issue.title}
                        </h3>
                        <p className="text-sm text-gray-500">{issue.description}</p>
                        <p className="text-muted text-sm mt-1">
                            {issue.status} • {issue.priority}
                        </p>
                    </div>
                ))}
            </div>

            {issues.length === 0 && (
                <p className="text-muted mt-4">No issues yet</p>
            )}

        </div>
    );
};

export default ProjectPage;
