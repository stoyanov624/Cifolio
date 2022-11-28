import "../../homeScreen.css"
import {useEffect, useState} from "react";
import CityContainer from "../CityContainer/CityContainer";
import Pager from "../../../../reusableComponents/Pager/Pager";
import {fetchPageOfCities, updateCity} from "../../../../services/city/controller";
import SearchBar from "../../../../reusableComponents/SearchBar/SearchBar";
import {CityModel} from "../../../../services/city/interfaces";

export interface PagingData {
    currentPage: number,
    totalPages: number;
}

export default function CitiesContainer () {
    const INITIAL_PAGE = 0;
    const DEFAULT_PAGE_SIZE = 8;
    
    const [searchedCity, setSearchedCity] = useState('');
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
        const cityData = await fetchPageOfCities(page - 1, DEFAULT_PAGE_SIZE, searchedCity);
        setCities(cityData.content);
    }

    useEffect(() => {
        loadInitialPage();
    }, [searchedCity]);

    const loadInitialPage = async () => {
        const cityData = await fetchPageOfCities(INITIAL_PAGE, DEFAULT_PAGE_SIZE, searchedCity);
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

    const updateSelectedCity = async (updatedCity: CityModel) => {
        await updateCity(updatedCity)
        modifyCities(updatedCity);
    }

    return (
    <div>
        <SearchBar executeSearch={setSearchedCity}/>
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