import "./homeScreen.css"
import MainHeader from "./components/MainHeader/MainHeader";
import CitiesContainer from "./components/CitiesContainer/CitiesContainer";
import Pager from "../../reusableComponents/Pager/Pager";
export default function Home() {
    return (<div>
        <MainHeader></MainHeader>
        <CitiesContainer></CitiesContainer>
    </div>
    )
 }