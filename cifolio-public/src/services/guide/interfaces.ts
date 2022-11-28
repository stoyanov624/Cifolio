import {CityModel} from "../city/interfaces";

export interface TravelGuideDataModel {
    id: number | null,
    name: string,
    cities: CityModel[]
}