import './Guides.css'
import {BsClipboardPlus} from "react-icons/bs"
import Guide from "./components/Guide";
import {useState} from "react";

export default function Guides() {
    const [guides, setGuides] = useState([]);

    return (<div className={"guides-screen"}>
            <div className={"title guides-screen-title"}>
                <h1>Travel Guides</h1>
                <BsClipboardPlus className={"clickable"} size={30}/>
            </div>
            <Guide/>
        </div>
    )
}