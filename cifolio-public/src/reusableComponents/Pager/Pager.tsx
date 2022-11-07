import { AiOutlineArrowRight, AiOutlineArrowLeft } from "react-icons/ai";
import "./Pager.css"
import {FC} from "react";
import {PagingData} from "../../screens/home/components/PhotosContainer/PhotosContainer";

interface PagerProps {
    pagingData: PagingData,
    goToPage: (page: number) => void,
}

const Pager:FC<PagerProps> = (pagerProps) => {
    return (
        <div className={"pager"}>
            <p>1</p>
            <AiOutlineArrowLeft className={"clickable"} onClick={() => pagerProps.goToPage(pagerProps.pagingData.currentPage - 1)} />
            <input value={pagerProps.pagingData.currentPage} onChange={event => pagerProps.goToPage(Number(event.target.value))} type={"number"} className={"pageInput"}/>
            <AiOutlineArrowRight className={"clickable"} onClick={() => pagerProps.goToPage(pagerProps.pagingData.currentPage + 1)}/>
            <p>{pagerProps.pagingData.totalPages}</p>
        </div>)
}

export default Pager;