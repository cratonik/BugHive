import { Routes,Route } from 'react-router-dom'
import Home from './pages/Home'
import Login from './pages/Login'
import Error404 from './pages/Error404'
import './App.css'
import Header from './components/Header'
import ProjectPage from './pages/ProjectPage'

function App() {
 
  return (
    <div>
      <Header />

      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
         <Route path='/projects' element={<ProjectPage />} />
        <Route path='*' element={<Error404 />} />
        
      </Routes>

      
    </div>
  )
}

export default App
