import UpdateModal from "../../../reusableComponents/UpdateModal/UpdateModal";
import {TravelGuideDataModel} from "../../../services/guide/interfaces";

interface ModalProps {
    setIsModalOpen: (isOpen: boolean) => void;
    guideToUpdate: TravelGuideDataModel;
}

const GuideModerationModal = (props: ModalProps) => {
    const isValidInput = () => {
        return true;
    }

    const handleSave = () => {
        console.log('save');
    }

    return (
        <UpdateModal props={{
            isValidInput: isValidInput,
            handleSave: handleSave,
            setIsOpenModal: props.setIsModalOpen}
        }>

        <p>Guide Name</p>
        <input
            value={props.guideToUpdate.name}
            type={"text"}
            name={"name"}
            // onChange={event => updateStateOnInputChange(event, setCityToUpdate)}
        />
        </UpdateModal>
    )
}

export default GuideModerationModal;