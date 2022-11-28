import './Guides.css'
import {BsClipboardPlus} from "react-icons/bs"
import Guide from "./components/Guide";
import {useEffect, useRef, useState} from "react";
import {TravelGuideDataModel} from "../../services/guide/interfaces";
import {fetchTravelGuides} from "../../services/guide/controller";
import GuideModerationModal from "./components/GuideModerationModal";

export default function Guides() {
    const [guides, setGuides] = useState<TravelGuideDataModel[]>([]);
    const [guideToUpdate, setGuideToUpdate] = useState<TravelGuideDataModel>({id: null, name: '', cities: []})
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        prepareComponent();
    }, [])

    const prepareComponent = async () => {
        const guides : TravelGuideDataModel[] = await fetchTravelGuides();
        setGuides(guides);
    }

    const handleModalOpening = () => {
        setGuideToUpdate({id: null, name: '', cities: []});
        setIsModalOpen(true);
    }

    return (
        <div className={"guides-screen"}>
            <div className={"title guides-screen-title"}>
                <h1>Travel Guides</h1>
                <BsClipboardPlus onClick={handleModalOpening} className={"clickable"} size={30}/>
            </div>
        {guides.map((guide, index) =>
            <Guide
                key={index}
                guide={guide}
                setIsModalOpen={setIsModalOpen}
                setGuideToUpdate={setGuideToUpdate}
            />
        )}
        {isModalOpen && <GuideModerationModal
            guideToUpdate={guideToUpdate}
            setIsModalOpen={setIsModalOpen}/>}
        </div>
    )
}