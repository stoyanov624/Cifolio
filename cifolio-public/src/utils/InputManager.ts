import {ChangeEvent, Dispatch, SetStateAction} from "react";

const updateStateOnInputChange = <T>(event : ChangeEvent, setter : Dispatch<SetStateAction<T>>) => {
    const {name, value} = event.target as HTMLInputElement;
    setter(prevState => ({
        ...prevState,
        [name]: value
    }))
}

export {updateStateOnInputChange};