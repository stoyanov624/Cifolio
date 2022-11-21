import Home from "./screens/home/Home";
import Login from "./screens/login/Login";
import {Route, Routes} from "react-router-dom";
import MainHeader from "./reusableComponents/MainHeader/MainHeader";

function App() {
  return (
    <div className="App">
        <MainHeader/>
        <Routes>
            <Route path={"/login"} element={<Login/>}/>
            <Route path={"/home"} element={<Home/>}/>
        </Routes>
    </div>
  )
}

export default App
