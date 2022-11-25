import UpdateModal from "../../../reusableComponents/UpdateModal/UpdateModal";

interface ModalProps {
    setIsOpenModal: (isOpen: boolean) => void;
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
            setIsOpenModal: props.setIsOpenModal}
        }>

        <p>Guide Name</p>
        <input
            value={"Some Default value"}
            type={"text"}
            name={"name"}
            // onChange={event => updateStateOnInputChange(event, setCityToUpdate)}
        />
        </UpdateModal>
    )
}

export default GuideModerationModal;