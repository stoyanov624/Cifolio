import {CityModel} from "../../../services/city/interfaces";
import {FC} from "react";
import defaultCityImg from "../../../assets/default-city-img.jpg";

interface CityPanelProps {
    city: CityModel;
}

function handleImageNotLoading (erroneousTarget : HTMLImageElement) {
    erroneousTarget.src = defaultCityImg;
}

const CityPanel:FC<CityPanelProps> = (props) => {
    return (<div>
        <img
            src={props.city.photo}
            loading={"eager"}
            onError={event => handleImageNotLoading(event.target as HTMLImageElement)}
            alt={'Missing photo!'}
        />
        {/*<p>{props.city.name}</p>*/}
    </div>)
}

export default CityPanel;