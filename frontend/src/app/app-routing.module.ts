import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {SeznamiComponent} from './seznami/seznami.component';
import {SeznamPodrobnostiComponent} from './seznami/seznam-podrobnosti.component';
import { ArtikelDodajComponent } from './seznami/artikel-dodaj.component';
import {FilmiComponent} from "./seznami/filmi.component";
import {FilmPodrobnostiComponent} from "./seznami/film-podrobnosti.component";
import {UporabnikPodrobnostiComponent} from "./seznami/uporabnik-podrobnosti.component";
import {FilmiByZanrComponent} from './seznami/filmi-by-zanr.component'

const routes: Routes = [
    {path: '', redirectTo: '/filmi', pathMatch: 'full'},
    {path: 'filmi', component: FilmiComponent},
    {path: 'filmi/:id', component: FilmPodrobnostiComponent},
    {path: 'uporabniki/:id', component: UporabnikPodrobnostiComponent},
    {path: 'zanri/:id', component: FilmiByZanrComponent},
    {path: 'seznami/:id', component: SeznamPodrobnostiComponent},
    {path: 'seznami/:id/dodaj', component: ArtikelDodajComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
