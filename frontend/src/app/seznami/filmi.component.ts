import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Film} from "./models/film";
import {FilmiService} from "./services/filmi.service";
import {Zanr} from "./models/zanr";

@Component({
    moduleId: module.id,
    selector: 'vsi-filmi',
    templateUrl: 'filmi.component.html'
})
export class FilmiComponent implements OnInit {
    filmi: Film[];
    film: Film;
    uporabnikId: number;
    zanri: Zanr[];
    //novFilm: Film;

    protected novFilm: Film = {
        ime: null,
        opis: null,
        datumIzida: null,
        kinoteke: null,
        rating: null,
        uporabnikiVsec: null,
        uporabnikiPogledano: null,
        id: null,
        zanr: {
            id: null,
            ime: null
        },
    };

    constructor(private filmiService: FilmiService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getFilmi();
        this.getZanri();
    }

    getFilmi(): void {
        this.filmiService
            .getFilmi()
            .subscribe(filmi => this.filmi = filmi);
    }

    naPodrobnosti(film: Film): void {
        this.film = film;
        this.router.navigate(['/filmi', this.film.id]);
    }

    idPoslji(): void {
        this.router.navigate(['/uporabniki', this.uporabnikId]);
    }

    getZanri(): void {
        this.filmiService
            .getZanri()
            .subscribe(zanri => this.zanri = zanri);
    }

    getFilmiByZanr(zanr: Zanr): void {
        this.router.navigate(['/zanri', zanr.id]);
    }

    dodaj(): void {
        this.filmiService
            .createFilm(this.novFilm)
            .subscribe(() => {
                this.ngOnInit()
            });
    }

}
