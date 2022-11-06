import "../../homeScreen.css"
import PhotoContainer from "../PhotoContainer/PhotoContainer";
import Pager from "../../../../reusableComponents/Pager/Pager";
import {useEffect, useState} from "react";
import fetchCities from "../../../../api/cityService";

export default function PhotosContainer () {
    const [cities, setCities] = useState([]);

    useEffect(() => {
        prepare();
    }, []);

    const prepare = async () => {
        const cityData = await fetchCities(1, 8);
        setCities(cityData.content);
    }

    return (
    <>
        <div className={"photosContainer"}>
            {cities.map((data : {name: string, photo: string}, index) =>
                <PhotoContainer
                    key={index}
                    name={data.name}
                    url={data.photo}
                />
        )}
        </div>

        <Pager></Pager>

    </>)
}