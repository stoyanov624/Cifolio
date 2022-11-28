import axios from "../axios";
import {CityModel} from "./interfaces";

const fetchPageOfCities = async (page: number, pageSize: number, cityName?: string) => {
    return (await axios.get("/cities-page", {
        params: {
            cityName: cityName,
            page: page,
            size: pageSize
        }
    })).data;
}

const fetchCities = async () => {
    return (await axios.get("/cities")).data
}

const updateCity = async (cityToUpdate: CityModel) => {
    return (await axios.put("/cities", cityToUpdate))
}

export {
    fetchPageOfCities,
    updateCity,
    fetchCities
};