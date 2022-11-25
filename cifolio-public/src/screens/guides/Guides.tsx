import './Guides.css'
import {BsClipboardPlus} from "react-icons/bs"
import Guide from "./components/Guide";
import {useEffect, useState} from "react";
import {TravelGuideDataModel} from "../../services/guide/interfaces";
import {fetchTravelGuides} from "../../services/guide/controller";

export default function Guides() {
    const [guides, setGuides] = useState<TravelGuideDataModel[]>([]);

    useEffect(() => {
        prepareComponent();
    }, [])

    const prepareComponent = async () => {
        const guides : TravelGuideDataModel[] = await fetchTravelGuides();
        setGuides(guides);
    }

    return (
        <div className={"guides-screen"}>
            <div className={"title guides-screen-title"}>
                <h1>Travel Guides</h1>
                <BsClipboardPlus className={"clickable"} size={30}/>
            </div>
        {guides.map((guide, index) =>
            <Guide
                key={index}
                guide={guide}
            />
        )}
        </div>
    )
}