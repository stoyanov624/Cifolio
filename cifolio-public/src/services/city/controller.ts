import axios from "../axios";
import {CityModel} from "./interfaces";

const fetchCities = async (page: number, pageSize: number, cityName?: string) => {
    return (await axios.get("/cities", {
        params: {
            cityName: cityName,
            page: page,
            size: pageSize
        }
    })).data;
}

const updateCity = async (cityToUpdate: CityModel) => {
    return (await axios.put("/cities", cityToUpdate))
}

export {
    fetchCities,
    updateCity
};