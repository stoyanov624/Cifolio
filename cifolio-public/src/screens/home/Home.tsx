import "./homeScreen.css"
import MainHeader from "./components/MainHeader/MainHeader";
import PhotosContainer from "./components/PhotosContainer/PhotosContainer";
import Pager from "../../reusableComponents/Pager/Pager";
export default function Home() {
    return (<div>
        <MainHeader></MainHeader>
        <PhotosContainer></PhotosContainer>
    </div>
    )
 }