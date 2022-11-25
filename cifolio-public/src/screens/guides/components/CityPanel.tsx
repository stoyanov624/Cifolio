import {CityModel} from "../../../services/city/interfaces";
import {FC} from "react";
import defaultCityImg from "../../../assets/default-city-img.jpg";
import "../Guides.css"

interface CityPanelProps {
    city: CityModel;
}

function handleImageNotLoading (erroneousTarget : HTMLImageElement) {
    erroneousTarget.src = defaultCityImg;
}

const CityPanel:FC<CityPanelProps> = (props) => {
    return (<div className={"city-panel"}>
        <img
            src={props.city.photo}
            loading={"eager"}
            onError={event => handleImageNotLoading(event.target as HTMLImageElement)}
            alt={'Missing photo!'}
        />
        <div className={"city-info"}>
            <h2>{props.city.name}</h2>
            <p>City description:</p>
        </div>
    </div>)
}

export default CityPanel;