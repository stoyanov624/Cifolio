import axios from "axios";

const fetchCities = async (page: number, pageSize: number) => {
    return (await axios.get("http://localhost:8080/api/cities", {
        params: {
            page: page,
            pageSize: pageSize
        }
    })).data;
}

export default fetchCities;