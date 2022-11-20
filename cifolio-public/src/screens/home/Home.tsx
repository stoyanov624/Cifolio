import "./homeScreen.css"
import MainHeader from "./components/MainHeader/MainHeader";
import CitiesContainer from "./components/CitiesContainer/CitiesContainer";
export default function Home() {
    return (<div>
        <MainHeader></MainHeader>
        <CitiesContainer></CitiesContainer>
    </div>
    )
 }