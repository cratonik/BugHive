import { Routes,Route } from 'react-router-dom'
import Home from './components/Home'
import Login from './components/Login'
import Error404 from './components/Error404'
import './App.css'
import Header from './components/Header'

function App() {
 
  return (
    <div>
      <Header />

      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='*' element={<Error404 />} />
      </Routes>

      
    </div>
  )
}

export default App
