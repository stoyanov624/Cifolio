import axios from "../axios";
import {TravelGuideDataModel} from "./interfaces";

const fetchTravelGuides = async () => {
    return (await axios.get("/guides")).data;
}

const createNewTravelGuide = async (newTravelGuide: TravelGuideDataModel) => {
    return (await axios.post("/guides", newTravelGuide)).data;
}

export {fetchTravelGuides, createNewTravelGuide}