import { Routes,Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Error404 from './pages/Error404'
import './App.css'
import Header from './components/Header'
import ProjectPage from './pages/ProjectPage'
import ProjectsListPage from './pages/ProjectListPage'

function App() {
 
  return (
    <div>
      <Header />

      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/projects' element={<ProjectsListPage />} />
        <Route path='*' element={<Error404 />} />
        <Route path="/projects/:id" element={<ProjectPage />} />
      </Routes>

      
    </div>
  )
}

export default App
