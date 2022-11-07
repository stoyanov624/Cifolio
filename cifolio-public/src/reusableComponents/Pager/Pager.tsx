import { AiOutlineArrowRight, AiOutlineArrowLeft } from "react-icons/ai";
import "./Pager.css"
import {FC} from "react";
import {PagingData} from "../../screens/home/components/CitiesContainer/CitiesContainer";

interface PagerProps {
    pagingData: PagingData,
    goToPage: (page: number) => void,
}

const Pager:FC<PagerProps> = (pagerProps) => {
    const FIRST_PAGE = 1;
    const isLessOrEqualThanMinimumPage = (page: number) : boolean => page <= 1;
    const isGreaterOrEqualThanMaximumPage = (page: number) : boolean => page >= pagerProps.pagingData.totalPages;

    const handleGoToPage = (page: number) => {
        page = isLessOrEqualThanMinimumPage(page) ? 1 : page;
        page = isGreaterOrEqualThanMaximumPage(page) ? pagerProps.pagingData.totalPages : page;
        pagerProps.goToPage(page);
    }

    const handleGoToNextPage = (page: number) => {
        if (isGreaterOrEqualThanMaximumPage(page)) return;
        pagerProps.goToPage(page + 1);
    }

    const handleGoToPreviousPage = (page: number) => {
        if (isLessOrEqualThanMinimumPage(page)) return;
        pagerProps.goToPage(page - 1);
    }

    return (
        <div className={"pager"}>
            <p>{FIRST_PAGE}</p>
            <AiOutlineArrowLeft
                className={isLessOrEqualThanMinimumPage(pagerProps.pagingData.currentPage) ? "disabled" :"clickable"}
                onClick={() => handleGoToPreviousPage(pagerProps.pagingData.currentPage)}
            />
            <input
                value={pagerProps.pagingData.currentPage}
                onChange={event => handleGoToPage(Number(event.target.value))}
                type={"number"}
                className={"pageInput"}
            />
            <AiOutlineArrowRight
                className={isGreaterOrEqualThanMaximumPage(pagerProps.pagingData.currentPage) ? "disabled" :"clickable"}
                onClick={() => handleGoToNextPage(pagerProps.pagingData.currentPage)}
            />
            <p>{pagerProps.pagingData.totalPages}</p>
        </div>)
}

export default Pager;