import { TfiClose } from "react-icons/tfi";
import "./CityUpdateModal.css"

const CityUpdateModal = () => {
    return <div className={"update-modal center"}>
        <TfiClose className={"modal-close-square clickable"}/>
        <p>City Name</p>
        <input type={"text"} name={"city-name"}/>
        <p>Photo Url</p>
        <input type={"text"} name={"city-photo"}/>
        <button>Save</button>
    </div>
}

export default CityUpdateModal;