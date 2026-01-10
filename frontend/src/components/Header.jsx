import { Link } from "react-router-dom"

const Header = () => {
  return (
    <div>
        <h1>Header Component</h1>
        <nav>
            <Link to = '/'>Home</Link>
            <Link to = '/login'>Login</Link>
        </nav>
    </div>
  )
}

export default Header