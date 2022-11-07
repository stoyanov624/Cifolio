import axios from "axios";

interface CityModel {
    id: number,
    name: string,
    photo: string
}

const fetchCities = async (page: number, pageSize: number, cityName?: string) => {
    return (await axios.get("http://localhost:8080/api/cities", {
        params: {
            cityName: cityName,
            page: page,
            pageSize: pageSize
        }
    })).data;
}

const updateCity = async (cityToUpdate: CityModel) => {
    return (await axios.put("http://localhost:8080/api/cities", cityToUpdate))
}

export {
    fetchCities,
    updateCity
};