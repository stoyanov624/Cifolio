import {FC} from "react";
import defaultCityImg from "../../../../assets/default-city-img.jpg"
interface PhotoData {
    name: string,
    url: string
}

const PhotoContainer:FC<PhotoData> = ({name, url}) => {
    function handleImageNotLoading (erroneousTarget : HTMLImageElement) {
        erroneousTarget.src = defaultCityImg;
    }

    return (
        <div className={"photoContainer"}>
            <h2>{name}</h2>
            <img
                src={url}
                onError={(event) => handleImageNotLoading(event.target as HTMLImageElement)}
                alt={'Missing photo!'}/>
        </div>
    )
}

export default PhotoContainer;