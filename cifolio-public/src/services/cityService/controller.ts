import axios from "axios";
import {CityModel} from "./interfaces";

const citiesController = axios.create({
    baseURL: "http://localhost:8080/api/cities",
})

const fetchCities = async (page: number, pageSize: number, cityName?: string) => {
    return (await citiesController.get("/", {
        params: {
            cityName: cityName,
            page: page,
            pageSize: pageSize
        }
    })).data;
}

const updateCity = async (cityToUpdate: CityModel) => {
    return (await citiesController.put("/", cityToUpdate))
}

export {
    fetchCities,
    updateCity
};