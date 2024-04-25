import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import { LocalizationProvider} from '@mui/x-date-pickers'
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs'
import Navbar from "./components/Navbar"
import Home from "./components/Home"
import NotFound from './components/NotFound'
import RegisterCar from "./components/RegisterCar.jsx";
function App() {
  return (
      <Router>
          <div className="container">
              <Navbar></Navbar>
              <Routes>
                  <Route path="/home" element={<Home/>} />
                  <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <Route path="/cars/add" element={<RegisterCar/>} />
                  </LocalizationProvider>


                  <Route path="*" element={<NotFound/>} />
              </Routes>
          </div>
      </Router>
  )
}

export default App
