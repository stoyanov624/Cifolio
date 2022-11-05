import "../../homeScreen.css"
import PhotoContainer from "../PhotoContainer/PhotoContainer";
import Pager from "../../../../reusableComponents/Pager/Pager";

const dummyData: Array<any> = [
    {
    name: "Sofia",
    url: "https://planetofhotels.com/guide/sites/default/files/styles/paragraph__live_banner__lb_image__1880bp/public/live_banner/sofia-1.jpg"
    },
    {
        name: "Tokyo",
        url: "https://upload.wikimedia.org/wikipedia/commons/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg"
    },
    {
        name: "Sofia",
        url: "https://planetofhotels.com/guide/sites/default/files/styles/paragraph__live_banner__lb_image__1880bp/public/live_banner/sofia-1.jpg"
    },
    {
        name: "Tokyo",
        url: "https://upload.wikimedia.org/wikipedia/commons/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg"
    },
    {
        name: "Sofia",
        url: "https://planetofhotels.com/guide/sites/default/files/styles/paragraph__live_banner__lb_image__1880bp/public/live_banner/sofia-1.jpg"
    },
    {
        name: "Sofia",
        url: "https://planetofhotels.com/guide/sites/default/files/styles/paragraph__live_banner__lb_image__1880bp/public/live_banner/sofia-1.jpg"
    },
    {
        name: "Tokyo",
        url: "https://upload.wikimedia.org/wikipedia/commons/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg"
    },
    {
        name: "Sofia",
        url: "https://planetofhotels.com/guide/sites/default/files/styles/paragraph__live_banner__lb_image__1880bp/public/live_banner/sofia-1.jpg"
    }
]

export default function PhotosContainer () {
    return (
    <>
        <div className={"photosContainer"}>
            {dummyData.map(data =>
                <PhotoContainer
                    name={data.name}
                    url={data.url}
                />
        )}
        </div>

        <Pager></Pager>

    </>)
}