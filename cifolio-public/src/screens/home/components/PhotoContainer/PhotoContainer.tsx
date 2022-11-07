import {FC} from "react";
import defaultCityImg from "../../../../assets/default-city-img.jpg"
import {CityModel} from "../PhotosContainer/PhotosContainer";
import { BsFillPencilFill } from "react-icons/bs";


const PhotoContainer:FC<CityModel> = (city) => {
    function handleImageNotLoading (erroneousTarget : HTMLImageElement) {
        erroneousTarget.src = defaultCityImg;
    }

    return (
        <div className={"photoContainer"}>
            <div className={"city-nav"}>
                <h2>{city.name}</h2>
                <BsFillPencilFill className={"clickable"}/>
            </div>
            <img
                src={city.photo}
                onError={event => handleImageNotLoading(event.target as HTMLImageElement)}
                alt={'Missing photo!'}/>
        </div>
    )
}

export default PhotoContainer;