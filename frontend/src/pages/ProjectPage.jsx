import { useEffect,useState } from "react";
import { fetchProjects } from "../api/projectApi";


const ProjectPage = () => {

    const[loading,setLoading] = useState(true);
    const[projectData,setProjectData] = useState([]); 

    

    useEffect(() =>{
        const loadProjects = async () =>{
            const data = await fetchProjects();
            setProjectData(data);
            setLoading(false);
        }
        loadProjects();
    },[])

    if(loading){
        return <div>Loading...</div>
    }

    return (
        <div>
            <h1>Project List displayed</h1>
        </div>
    )
    



}

export default ProjectPage;