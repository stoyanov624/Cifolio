import {CityModel} from "../city/interfaces";

export interface TravelGuideDataModel {
    id: number,
    name: string,
    cities: CityModel[]
}