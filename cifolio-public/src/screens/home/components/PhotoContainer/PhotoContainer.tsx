import {FC} from "react";
import defaultCityImg from "../../../../assets/default-city-img.jpg"
import {CityModel} from "../PhotosContainer/PhotosContainer";

const PhotoContainer:FC<CityModel> = (city) => {
    function handleImageNotLoading (erroneousTarget : HTMLImageElement) {
        erroneousTarget.src = defaultCityImg;
    }

    return (
        <div className={"photoContainer"}>
            <h2>{city.name}</h2>
            <img
                src={city.photo}
                onError={event => handleImageNotLoading(event.target as HTMLImageElement)}
                alt={'Missing photo!'}/>
        </div>
    )
}

export default PhotoContainer;