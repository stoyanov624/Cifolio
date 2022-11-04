import "./homeScreen.css"
import MainHeader from "./components/MainHeader/MainHeader";
import PhotosContainer from "./components/PhotosContainer/PhotosContainer";
export default function Home() {
    return (<div>
        <MainHeader></MainHeader>
        <PhotosContainer></PhotosContainer>
    </div>
    )
 }