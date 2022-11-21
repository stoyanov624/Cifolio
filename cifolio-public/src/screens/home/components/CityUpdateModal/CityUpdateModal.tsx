import { TfiClose } from "react-icons/tfi";
import "./CityUpdateModal.css"
import {FC, useState} from "react";
import {CityModel} from "../../../../services/city/interfaces";
import {updateStateOnInputChange} from "../../../../utils/InputManager";

interface ModalProps {
    cityToUpdate: CityModel
    setIsOpenModal: (isOpen: boolean) => void;
    updateCity: (city: CityModel) => void;
}

const CityUpdateModal:FC<ModalProps> = (modalProps) => {
    const [cityToUpdate, setCityToUpdate] = useState(modalProps.cityToUpdate);

    const isValidInput = () => {
        return cityToUpdate.name
            && cityToUpdate.name.trim()
            && cityToUpdate.photo
            && cityToUpdate.photo.trim();
    }

    const handleSave = () => {
        modalProps.updateCity(cityToUpdate);
        modalProps.setIsOpenModal(false);
    }

    return (
        <div className={"modal-background"}>

            <div className={"update-modal"}>
                <TfiClose
                    onClick={() => modalProps.setIsOpenModal(false)}
                    className={"modal-close-square clickable"}
                />
                <p>City Name</p>
                <input
                    value={cityToUpdate.name.trimStart()}
                    type={"text"}
                    name={"name"}
                    onChange={event => updateStateOnInputChange(event, setCityToUpdate)}
                />
                <p>Photo Url</p>
                <input
                    value={cityToUpdate.photo.trimStart()}
                    type={"text"}
                    name={"photo"}
                    onChange={event => updateStateOnInputChange(event, setCityToUpdate)}
                />
                <button disabled={!isValidInput()} onClick={handleSave}>Save</button>
                {!isValidInput() && <p className={"error-message"}>All fields must be filled!</p>}
            </div>
        </div>
    )
}

export default CityUpdateModal;