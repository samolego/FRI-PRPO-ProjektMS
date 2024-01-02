import {Kinoteka} from "./kinoteka";
import {Zanr} from "./zanr";
import {Uporabnik} from "./uporabnik";

export class Film {
    datumIzida: string;
    id: number;
    ime: string;
    opis: string;
    kinoteke: Kinoteka[];
    rating: number;
    zanr: Zanr;
    uporabnikiPogledano: Uporabnik[];
    uporabnikiVsec: Uporabnik[];
}