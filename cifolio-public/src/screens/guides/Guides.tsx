import './Guides.css'
import {AiOutlineEye} from "react-icons/ai"

export default function Guides() {
    return (<div className={"guides-screen"}>
            <h1 className={"title"}>Travel Guides</h1>
            <div className={"guides-container"}>
                <div>
                    <div className={"guide-title-holder"}>
                        <h1>Guide1</h1>
                        <AiOutlineEye  size={25} className={"clickable"}/>
                    </div>
                    <h2>Guide description</h2>
                </div>
                <div>
                    <div className={"guide-title-holder"}>
                        <h1>Guide2</h1>
                        <AiOutlineEye  size={25} className={"clickable"}/>
                    </div>
                    <h2>Guide description</h2>
                </div>
                <div>
                    <div className={"guide-title-holder"}>
                        <h1>Guide3</h1>
                        <AiOutlineEye  size={25} className={"clickable"}/>
                    </div>
                    <h2>Guide description</h2>
                </div>
                <div>
                    <div className={"guide-title-holder"}>
                        <h1>Guide3</h1>
                        <AiOutlineEye  size={25} className={"clickable"}/>
                    </div>
                    <h2>Guide description</h2>
                </div>
                <div>
                    <div className={"guide-title-holder"}>
                        <h1>Guide3</h1>
                        <AiOutlineEye  size={25} className={"clickable"}/>
                    </div>
                    <h2>Guide description</h2>
                </div>
                <div>
                    <div className={"guide-title-holder"}>
                        <h1>Guide3</h1>
                        <AiOutlineEye  size={25} className={"clickable"}/>
                    </div>
                    <h2>Guide description</h2>
                </div>
                <div>
                    <div className={"guide-title-holder"}>
                        <h1>Guide3</h1>
                        <AiOutlineEye  size={25} className={"clickable"}/>
                    </div>
                    <h2>Guide description</h2>
                </div>
            </div>
        </div>
    )
}