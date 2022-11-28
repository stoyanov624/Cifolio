import "./SearchBar.css"

import {FaSearchLocation} from "react-icons/fa";
import {FC, MutableRefObject, useRef} from "react";

interface SearchBarProps {
    executeSearch: (cityName: string) => void;
}

const SearchBar:FC<SearchBarProps> = ({executeSearch}) => {
    const searchInput: MutableRefObject<any> = useRef();

    return (
        <div className={"search-bar"}>
            <input onKeyDown={(event) => {
                if(event.key === "Enter") {
                    executeSearch(searchInput.current.value);
                }
            }} ref={searchInput} placeholder={"Search with Enter"}/>
            <FaSearchLocation onClick={() => executeSearch(searchInput.current.value)} className={"clickable search-icon"}/>
        </div>
    )
}

export default SearchBar;