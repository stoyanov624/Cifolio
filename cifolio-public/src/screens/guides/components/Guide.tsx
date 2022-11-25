import {ImEye} from "react-icons/im";
import "../Guides.css"
const Guide = () => {
    return <div className={"guides-container"}>
        <div>
            <div className={"guide-title-holder"}>
                <h1>Guide1</h1>
                <ImEye  size={25} className={"clickable"}/>
            </div>
            <h2>Guide description</h2>
        </div>
    </div>
}

export default Guide;