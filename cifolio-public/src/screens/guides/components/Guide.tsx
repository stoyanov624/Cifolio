import {ImEye} from "react-icons/im";
import "../Guides.css"
import {FC, useState} from "react";
import {TravelGuideDataModel} from "../../../services/guide/interfaces";
import CityPanel from "./CityPanel";
import {BsFillPencilFill} from "react-icons/bs";
import {TfiClose} from "react-icons/tfi";

interface GuideComponentProps {
    guide: TravelGuideDataModel
    setIsModalOpen: (isModalOpen: boolean) => void;
    setGuideToUpdate: (guideToUpdate: TravelGuideDataModel) => void;
    handleGuideDeletion: (guideId: number) => void;
}

const Guide:FC<GuideComponentProps> = (props) => {
    const [isOpenPanel, setIsOpenPanel] = useState(false);

    const handleModalOpening = () => {
        props.setGuideToUpdate(props.guide);
        props.setIsModalOpen(true);
    }

    return <div className={"guides-container"}>
        <div>
            <div className={"guide-title-holder"}>
                <h1>{props.guide.name}</h1>
                <ImEye onClick={() => setIsOpenPanel(!isOpenPanel)} size={25} className={`${isOpenPanel ? 'active-icon' : ''} clickable`}/>
                <BsFillPencilFill className={"clickable"} onClick={handleModalOpening} />
                <TfiClose className={"clickable"} onClick={() => props?.guide?.id && props.handleGuideDeletion(props.guide.id)}/>
            </div>
            <h2>Guide description</h2>
            {isOpenPanel && props.guide.cities.map((city, index) =>
                <CityPanel
                    key={index}
                    city={city}
                    isOpenPanel={isOpenPanel}
                />
            )}
        </div>
    </div>
}

export default Guide;