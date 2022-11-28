import UpdateModal from "../../../reusableComponents/UpdateModal/UpdateModal";
import {TravelGuideDataModel} from "../../../services/guide/interfaces";
import {useState} from "react";
import {updateStateOnInputChange} from "../../../utils/InputManager";

interface ModalProps {
    setIsModalOpen: (isOpen: boolean) => void;
    guideToUpdate: TravelGuideDataModel;
    updateGuides: (guide: TravelGuideDataModel) => void;
}

const GuideModerationModal = (props: ModalProps) => {
    const [guideToUpdate, setGuideToUpdate] = useState<TravelGuideDataModel>(props.guideToUpdate);

    const isValidInput = () => {
        return Boolean(
            guideToUpdate.name &&
            guideToUpdate.name.trim()
        );
    }

    const handleSave = () => {
        props.updateGuides(guideToUpdate);
        props.setIsModalOpen(false);
    }

    return (
        <UpdateModal props={{
            isValidInput: isValidInput,
            handleSave: handleSave,
            setIsOpenModal: props.setIsModalOpen}
        }>

        <p>Guide Name</p>
        <input
            value={guideToUpdate.name}
            type={"text"}
            name={"name"}
            onChange={event => updateStateOnInputChange(event, setGuideToUpdate)}
        />
        </UpdateModal>
    )
}

export default GuideModerationModal;