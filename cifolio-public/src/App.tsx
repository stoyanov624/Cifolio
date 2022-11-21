import Home from "./screens/home/Home";
import SignIn from "./screens/signing/SignIn";
import {Route, Routes} from "react-router-dom";
import MainHeader from "./reusableComponents/MainHeader/MainHeader";
import SignUp from "./screens/signing/SignUp";

function App() {
  return (
    <div className="App">
        <MainHeader/>
        <Routes>
            <Route path={"/login"} element={<SignIn/>}/>
            <Route path={"/register"} element={<SignUp/>}/>
            <Route path={"/home"} element={<Home/>}/>
        </Routes>
    </div>
  )
}

export default App
