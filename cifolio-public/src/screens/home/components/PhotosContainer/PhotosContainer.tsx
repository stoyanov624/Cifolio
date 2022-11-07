import "../../homeScreen.css"
import {useEffect, useState} from "react";
import PhotoContainer from "../PhotoContainer/PhotoContainer";
import Pager from "../../../../reusableComponents/Pager/Pager";
import fetchCities from "../../../../api/cityService";
import CityUpdateModal from "../CityUpdateModal/CityUpdateModal";

export interface CityModel {
    id: number,
    name: string,
    photo: string
}

export interface PagingData {
    currentPage: number,
    totalPages: number;
}

export default function PhotosContainer () {
    const DEFAULT_PAGE_SIZE = 8;
    const [cities, setCities] = useState<CityModel[]>([]);
    const [pagingData, setPagingData] = useState<PagingData>({
        currentPage: 1,
        totalPages: 0,
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

    const loadPage = async () => {
        const cityData = await fetchCities(pagingData.currentPage - 1, DEFAULT_PAGE_SIZE);
        setCities(cityData.content);
        setPagingData(prevState => ({
            ...prevState,
            totalPages: cityData.totalPages
        }));
    }

    const updateCity = (updatedCity: CityModel) => {
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

    return (
    <div>
        <div className={"photosContainer"}>
            {cities.map((city, index) =>
                <PhotoContainer
                    key={index}
                    city={city}
                    updateCity={updateCity}
                />
        )}
        </div>

        <Pager
            pagingData={pagingData}
            goToPage={goToPage}
        />


    </div>)
}