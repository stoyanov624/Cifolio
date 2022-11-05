import { AiOutlineArrowRight, AiOutlineArrowLeft } from "react-icons/ai";
import "./Pager.css"

const Pager = () => {
    return (
        <div className={"pager"}>
            <p>1</p>
            <AiOutlineArrowLeft/>
            <input type={"text"} className={"pageInput"}/>
            <AiOutlineArrowRight/>
            <p>100</p>
        </div>)
}

export default Pager;