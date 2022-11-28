import './Guides.css'
import {BsClipboardPlus} from "react-icons/bs"
import Guide from "./components/Guide";
import {useEffect, useState} from "react";
import {TravelGuideDataModel} from "../../services/guide/interfaces";
import {createNewTravelGuide, fetchTravelGuides, updateExistingGuide} from "../../services/guide/controller";
import GuideModerationModal from "./components/GuideModerationModal/GuideModerationModal";

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

    const updateGuides = async (guide: TravelGuideDataModel) => {
        if (guide.id === null) {
            await handleGuideCreation(guide);
        } else {
            await handleGuideModification(guide);
        }
    }

    const handleGuideCreation = async (newGuide: TravelGuideDataModel) => {
        const {id} : {id: number} = await createNewTravelGuide(newGuide);
        newGuide.id = id;
        setGuides([...guides, newGuide]);
    }

    const handleGuideModification = async (updatedGuide: TravelGuideDataModel) => {
        await updateExistingGuide(updatedGuide);
        const updatedGuides = guides.map(guide => {
            if(guide.id === updatedGuide.id) {
                return {
                    ...guide,
                    name: updatedGuide.name
                }
            }
            return guide
        })
        setGuides(updatedGuides);
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
            setIsModalOpen={setIsModalOpen}
            updateGuides={updateGuides}/>}
        </div>
    )
}