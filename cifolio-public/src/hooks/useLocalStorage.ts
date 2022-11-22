import { useEffect, useState } from "react";

const getSavedData = <T>(key : string, initialValue: T) => {
    const savedValue = localStorage.getItem(key);
    return savedValue ? JSON.parse(savedValue) : initialValue;
}

export default function useLocalStorage<T>(key : string, initialValue: T) {
    const [value, setValue] = useState(() => {
        return getSavedData(key, initialValue);
    });

    useEffect(() => {
        localStorage.setItem(key, value ? JSON.stringify(value) : '')
    }, [value]);


    return [value, setValue];
}