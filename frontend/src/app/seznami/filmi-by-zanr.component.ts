import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Location} from '@angular/common';

import { switchMap } from 'rxjs/operators';
import {FilmiService} from "./services/filmi.service";
import {Uporabnik} from "./models/uporabnik";
import {Film} from "./models/film";
import {Zanr} from "./models/zanr";


@Component({
    moduleId: module.id,
    selector: 'filmi-by-zanr',
    templateUrl: 'filmi-by-zanr.component.html'
})
export class FilmiByZanrComponent implements OnInit {
    zanr: Zanr;
    filmi: Film[];
    film: Film;

    constructor(private filmiService: FilmiService,
                private route: ActivatedRoute,
                private location: Location,
                private router: Router) {
    }

    ngOnInit(): void {
        this.route.params.pipe(
            switchMap((params: Params) => this.filmiService.getZanr(+params['id'])))
            .subscribe(zanr => this.zanr = zanr);
        this.getFilmi();
    }

    getFilmi(): void {
        this.filmiService
            .getFilmi()
            .subscribe(filmi => this.filmi = filmi);
    }

    nazaj(): void {
        this.router.navigate(['filmi']);
    }

    naPodrobnosti(film: Film): void {
        this.film = film;
        this.router.navigate(['/filmi', this.film.id]);
    }

    protected readonly Film = Film;
}
