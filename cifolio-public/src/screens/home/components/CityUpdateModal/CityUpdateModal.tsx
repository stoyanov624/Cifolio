import "./CityUpdateModal.css"
import {FC, useState} from "react";
import {CityModel} from "../../../../services/city/interfaces";
import {updateStateOnInputChange} from "../../../../utils/InputManager";
import UpdateModal from "../../../../reusableComponents/UpdateModal/UpdateModal";

interface ModalProps {
    cityToUpdate: CityModel
    setIsOpenModal: (isOpen: boolean) => void;
    updateCity: (city: CityModel) => void;
}

const CityUpdateModal:FC<ModalProps> = (modalProps) => {
    const [cityToUpdate, setCityToUpdate] = useState(modalProps.cityToUpdate);

    const isValidInput = () => {
        return Boolean(cityToUpdate.name
            && cityToUpdate.name.trim()
            && cityToUpdate.photo
            && cityToUpdate.photo.trim());
    }

    const handleSave = () => {
        modalProps.updateCity(cityToUpdate);
        modalProps.setIsOpenModal(false);
    }

    return (
        <UpdateModal
            props={{
                setIsOpenModal: modalProps.setIsOpenModal,
                isValidInput: isValidInput,
                handleSave: handleSave}}>

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
        </UpdateModal>
    )
}

export default CityUpdateModal;