
import './App.css'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import Footer from './components/Footer'
import NavBar from './components/NavBar'
import RegisterPage from './components/RegisterPage'
import { Toaster } from 'react-hot-toast'
import LoginPage from './components/LoginPage'
import DashboardLayout from './components/Dashboard/DashboardLayout'
import { useStoreContext } from './contextapi/Contextapi'
import Logout from './components/Logout'
import ErrorPage from './components/ErrorPage'

function App() {

  const {token}=useStoreContext();
  
  return (
    <>
      <BrowserRouter>
      <NavBar/>
      <Toaster/>
        <Routes>
          <Route  path="/" element={<LandingPage/>}/>
          <Route path="/about" element={<AboutPage/>}/>

          <Route path="/register"
          element={!token ? <RegisterPage /> : <Navigate to="/"/>} 
          />
           

          <Route path="/login" 
          element={!token ? <LoginPage /> : <Navigate to="/"/> } />

          <Route path="/dashboard" element={token?<DashboardLayout/> : <Navigate to="/login"/>}/>

          <Route path="*" element={<ErrorPage message="We cant seem to find the page you are looking for "/>}/>

         


        </Routes>
        <Footer/>
      </BrowserRouter>

    </>
  )
}

export default App
