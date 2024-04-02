import { IPig } from "../pig/pig.interface";

export interface SanitaryActivity {

    date: string;
    activity: string;
    description: string;
    pigs: IPig[];

    key?: string;

}
