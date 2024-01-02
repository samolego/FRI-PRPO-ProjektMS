import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Location} from '@angular/common';

import { switchMap } from 'rxjs/operators';
import {FilmiService} from "./services/filmi.service";
import {Uporabnik} from "./models/uporabnik";
import {Film} from "./models/film";


@Component({
    moduleId: module.id,
    selector: 'uporabnik-podrobnosti',
    templateUrl: 'uporabnik-podrobnosti.component.html'
})
export class UporabnikPodrobnostiComponent implements OnInit {
    uporabnik: Uporabnik;
    filmi: Film[];

    constructor(private filmiService: FilmiService,
                private route: ActivatedRoute,
                private location: Location,
                private router: Router) {
    }

    ngOnInit(): void {
        this.route.params.pipe(
            switchMap((params: Params) => this.filmiService.getUporabnik(+params['id'])))
            .subscribe(
                uporabnik => this.uporabnik = uporabnik,
                (error) => {
                    alert("No user with entered id! Please, try again.");
                    this.router.navigate(['/filmi']);
                });
        this.getFilmi();
    }

    getFilmi(): void {
        this.filmiService
            .getFilmi()
            .subscribe(filmi => this.filmi = filmi);
    }

    priporocaj(): void {
        let zanriFilmov:number[] = [0, 0, 0, 0, 0, 0, 0];

        for (let film of this.filmi) {
            for (let uporabnik of film.uporabnikiVsec) {
                if (uporabnik.id == this.uporabnik.id) {
                    zanriFilmov[film.zanr.id - 1]++;
                }
            }
        }
        let max = 0;
        let id = 1;
        for(let i = 0; i < 7; i++) {
            if ( zanriFilmov[i] > max) {
                max = zanriFilmov[i];
                id = i+1;
            }

        }

        //return id;
        this.router.navigate(['/zanri', id]);
    }


    nazaj(): void {
        this.router.navigate(['filmi']);
    }

}
