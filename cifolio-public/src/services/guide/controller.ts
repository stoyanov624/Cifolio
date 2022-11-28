import axios from "../axios";
import {TravelGuideDataModel} from "./interfaces";

const fetchTravelGuides = async () => {
    return (await axios.get("/guides")).data;
}

const createNewTravelGuide = async (newTravelGuide: TravelGuideDataModel) => {
    return (await axios.post("/guides", newTravelGuide)).data;
}

const updateExistingGuide = async (updatedTravelGuide: TravelGuideDataModel) => {
    return (await axios.put("/guides", updatedTravelGuide)).data;
}

const deleteGuide = async (guideId: number) => {
    return (await axios.delete(`/guides/${guideId}`)).data;
}

export {fetchTravelGuides, createNewTravelGuide, updateExistingGuide, deleteGuide}