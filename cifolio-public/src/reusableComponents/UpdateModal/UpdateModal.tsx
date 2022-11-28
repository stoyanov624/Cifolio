import "./UpdateModal.css"
import {ReactNode} from "react";
import {TfiClose} from "react-icons/tfi";

interface ModalProps {
    setIsOpenModal: (isOpen: boolean) => void;
    isValidInput: () => boolean;
    handleSave: () => void;
}

const UpdateModal = ({children, props} : {children: ReactNode, props: ModalProps}) => {
    return (
        <div className={"modal-background"}>
            <div className={"update-modal"}>
                <TfiClose
                    onClick={() => props.setIsOpenModal(false)}
                    className={"modal-close-square clickable"}
                />

                {children}

                <button disabled={!props.isValidInput()} onClick={props.handleSave}>Save</button>
                {!props.isValidInput() && <p className={"error-message"}>All fields must be filled!</p>}
            </div>
        </div>
    )
}

export default UpdateModal;