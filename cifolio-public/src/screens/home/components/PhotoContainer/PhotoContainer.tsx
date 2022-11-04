import {FC} from "react";

interface PhotoData {
    name: string,
    url: string
}

const PhotoContainer:FC<PhotoData> = ({name, url}) => {
    return (
        <div className={"photoContainer"}>
            <h2>{name}</h2>
            <img src={url}/>
        </div>
    )
}

export default PhotoContainer;