import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Location} from '@angular/common';

import { switchMap } from 'rxjs/operators';
import {Film} from "./models/film";
import {FilmiService} from "./services/filmi.service";


@Component({
    moduleId: module.id,
    selector: 'film-podrobnosti',
    templateUrl: 'film-podrobnosti.component.html'
})
export class FilmPodrobnostiComponent implements OnInit {
    film: Film;
    novOpis: string;

    constructor(private filmiService: FilmiService,
                private route: ActivatedRoute,
                private location: Location,
                private router: Router) {
    }

    ngOnInit(): void {
        this.route.params.pipe(
            switchMap((params: Params) => this.filmiService.getFilm(+params['id'])))
            .subscribe(film => this.film = film);
    }


    izbrisi(): void {
        this.filmiService
            .deleteFilm(this.film.id)
            .subscribe(() =>{ this.film = null; this.router.navigate(['filmi']);});
    }

    nazaj(): void {
        this.router.navigate(['filmi']);
    }
}
