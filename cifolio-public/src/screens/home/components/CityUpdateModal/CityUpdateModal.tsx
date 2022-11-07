import { TfiClose } from "react-icons/tfi";
import "./CityUpdateModal.css"
import {ChangeEvent, FC, FormEvent, SyntheticEvent, useState} from "react";
import {CityModel} from "../PhotosContainer/PhotosContainer";

interface ModalProps {
    cityToUpdate: CityModel
    setIsOpenModal: (isOpen: boolean) => void;
    updateCity: (city: CityModel) => void;
}

const CityUpdateModal:FC<ModalProps> = (modalProps) => {
    const [cityToUpdate, setCityToUpdate] = useState(modalProps.cityToUpdate);

    const updateCityState = (event : ChangeEvent) => {
        const {name, value} = event.target as HTMLInputElement;
        setCityToUpdate(prevState => ({
            ...prevState,
            [name]: value
        }))
    }

    const handleSave = () => {
        modalProps.updateCity(cityToUpdate);
        modalProps.setIsOpenModal(false);
    }

    return (
        <div className={"modal-background"}>
            <div className={"update-modal center"}>
                <TfiClose
                    onClick={() => modalProps.setIsOpenModal(false)}
                    className={"modal-close-square clickable"}
                />
                <p>City Name</p>
                <input
                    value={cityToUpdate.name}
                    type={"text"}
                    name={"name"}
                    onChange={updateCityState}
                />
                <p>Photo Url</p>
                <input
                    value={cityToUpdate.photo}
                    type={"text"}
                    name={"photo"}
                    onChange={updateCityState}
                />
                <button onClick={handleSave}>Save</button>
            </div>
        </div>
    )
}

export default CityUpdateModal;