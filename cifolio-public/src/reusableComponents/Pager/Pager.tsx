import { AiOutlineArrowRight, AiOutlineArrowLeft } from "react-icons/ai";
import "./Pager.css"

const Pager = () => {
    return (
        <div className={"pager"}>
            <p>1</p>
            <AiOutlineArrowLeft className={"clickable"} onClick={() => console.log('go left')} />
            <input type={"text"} className={"pageInput"}/>
            <AiOutlineArrowRight className={"clickable"} onClick={() => console.log('go right')}/>
            <p>100</p>
        </div>)
}

export default Pager;