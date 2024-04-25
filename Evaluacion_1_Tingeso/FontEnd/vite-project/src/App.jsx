import './App.css'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Navbar from "./components/Navbar"
import Home from "./components/Home"
import NotFound from './components/NotFound'
import RegisterCar from "./components/RegisterCar.jsx";
import RegisterBrandBond from "./components/RegisterBrandBond.jsx";
import CarList from "./components/CarList.jsx";
function App() {
  return (
      <Router>
          <div className="container">
              <Navbar></Navbar>
              <Routes>
                  <Route path="/home" element={<Home/>} />
                  <Route path="/cars/list" element={<CarList/>}/>
                  <Route path="/cars/add" element={<RegisterCar/>} />
                  <Route path="/cars/edit/:plateURL" element={<RegisterCar/>}/>
                  <Route path="/car_brand/register" element={<RegisterBrandBond/>} />
                  <Route path="*" element={<NotFound/>} />
              </Routes>
          </div>
      </Router>
  )
}

export default App
