import "./SearchBar.css"

import {FaSearchLocation} from "react-icons/fa";


const SearchBar = () => {
    return (
        <div className={"search-bar"}>
            <input placeholder={"Search with Enter"}/>
            <FaSearchLocation className={"clickable search-icon"}/>

        </div>
    )
}

export default SearchBar;