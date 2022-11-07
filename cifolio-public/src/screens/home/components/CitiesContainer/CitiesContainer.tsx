import "../../homeScreen.css"
import {useEffect, useState} from "react";
import CityContainer from "../CityContainer/CityContainer";
import Pager from "../../../../reusableComponents/Pager/Pager";
import {fetchCities, updateCity} from "../../../../api/cityService";
import SearchBar from "../../../../reusableComponents/SearchBar/SearchBar";

export interface CityModel {
    id: number,
    name: string,
    photo: string
}

export interface PagingData {
    currentPage: number,
    totalPages: number;
}

export default function CitiesContainer () {
    const DEFAULT_PAGE_SIZE = 8;
    const [cities, setCities] = useState<CityModel[]>([]);
    const [pagingData, setPagingData] = useState<PagingData>({
        currentPage: 1,
        totalPages: 1,
    });

    const goToPage = async (page: number) => {
        setPagingData(prevState => ({
            ...prevState,
            currentPage: page
        }))
        const cityData = await fetchCities(page - 1, DEFAULT_PAGE_SIZE);
        setCities(cityData.content);
    }

    useEffect(() => {
        loadPage();
    }, []);

    const loadPage = async (cityName?: string) => {
        const cityData = await fetchCities(0, DEFAULT_PAGE_SIZE, cityName);
        setCities(cityData.content);
        setPagingData(prevState => ({
            currentPage: 1,
            totalPages: cityData.totalPages || 1
        }));
    }

    const modifyCities = (updatedCity: CityModel) => {
        const updatedCities = cities.map(city => {
            if(city.id === updatedCity.id) {
                return {
                    ...city,
                    name: updatedCity.name,
                    photo: updatedCity.photo
                }
            }
            return city
        })

        setCities(updatedCities);
    }

    const updateSelectedCity = (updatedCity: CityModel) => {
        modifyCities(updatedCity);
        updateCity(updatedCity);
    }

    return (
    <div>
        <SearchBar executeSearch={loadPage}/>
        <div className={"photosContainer"}>
            {cities.map((city, index) =>
                <CityContainer
                    key={index}
                    city={city}
                    updateCity={updateSelectedCity}
                />
        )}
        </div>

        <Pager pagingData={pagingData} goToPage={goToPage}/>
    </div>)
}