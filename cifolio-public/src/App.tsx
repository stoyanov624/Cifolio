import Home from "./screens/home/Home";
import SignIn from "./screens/signing/SignIn";
import {Route, Routes} from "react-router-dom";
import MainHeader from "./reusableComponents/MainHeader/MainHeader";
import SignUp from "./screens/signing/SignUp";
import ProtectedRoutes from "./utils/ProtectedRoutes";

function App() {
  return (
    <div className="App">
        <MainHeader/>
        <Routes>
            <Route element={<ProtectedRoutes/>}>
                <Route path={"/home"} element={<Home/>}/>
                <Route path={"*"} element={<Home/>}/>
            </Route>
            <Route path={"/login"} element={<SignIn/>}/>
            <Route path={"/register"} element={<SignUp/>}/>
        </Routes>
    </div>
  )
}

export default App
