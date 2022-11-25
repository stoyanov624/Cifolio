import {ImEye} from "react-icons/im";
import "../Guides.css"
import {FC, useState} from "react";
import {TravelGuideDataModel} from "../../../services/guide/interfaces";
import CityPanel from "./CityPanel";

interface GuideComponentProps {
    guide: TravelGuideDataModel
}

const Guide:FC<GuideComponentProps> = (props) => {
    const [isOpenPanel, setIsOpenPanel] = useState(false);

    return <div className={"guides-container"}>
        <div>
            <div className={"guide-title-holder"}>
                <h1>{props.guide.name}</h1>
                <ImEye onClick={() => setIsOpenPanel(!isOpenPanel)} size={25} className={"clickable"}/>
            </div>
            <h2>Guide description</h2>
            {isOpenPanel && props.guide.cities.map((city, index) =>
                <CityPanel
                    key={index}
                    city={city}
                />
            )}
        </div>
    </div>
}

export default Guide;