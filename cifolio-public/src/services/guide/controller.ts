import axios from "../axios";

const fetchTravelGuides = async () => {
    return (await axios.get("/guides")).data;
}