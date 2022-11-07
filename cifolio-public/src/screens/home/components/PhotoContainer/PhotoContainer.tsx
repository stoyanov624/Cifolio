import {FC, useState} from "react";
import defaultCityImg from "../../../../assets/default-city-img.jpg"
import {CityModel} from "../PhotosContainer/PhotosContainer";
import { BsFillPencilFill } from "react-icons/bs";
import CityUpdateModal from "../CityUpdateModal/CityUpdateModal";

interface PhotoContainerProps {
    city: CityModel,
    updateCity: (city: CityModel) => void
}

const PhotoContainer:FC<PhotoContainerProps> = (photoContainerProps) => {
    const [isOpenModal, setIsOpenModal] = useState(false);

    function handleImageNotLoading (erroneousTarget : HTMLImageElement) {
        erroneousTarget.src = defaultCityImg;
    }

    return (
        <>
            <div className={"photoContainer"}>
                <div className={"city-nav"}>
                    <h2>{photoContainerProps.city.name}</h2>
                    <BsFillPencilFill className={"clickable"} onClick={() => setIsOpenModal(true)} />
                </div>
                <img
                    src={photoContainerProps.city.photo}
                    onError={event => handleImageNotLoading(event.target as HTMLImageElement)}
                    alt={'Missing photo!'}/>
            </div>

            {isOpenModal &&
            <CityUpdateModal
                cityToUpdate={photoContainerProps.city}
                setIsOpenModal={setIsOpenModal}
                updateCity={photoContainerProps.updateCity}
            />}
        </>
    )
}

export default PhotoContainer;