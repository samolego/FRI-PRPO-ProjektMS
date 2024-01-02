import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {SeznamiComponent} from './seznami/seznami.component';
import {ArtikelDodajComponent} from './seznami/artikel-dodaj.component';
import {SeznamPodrobnostiComponent} from './seznami/seznam-podrobnosti.component';
import {SeznamiService} from './seznami/services/seznami.service';
import {FilmiComponent} from './seznami/filmi.component';
import {FilmiService} from "./seznami/services/filmi.service";
import {FilmPodrobnostiComponent} from './seznami/film-podrobnosti.component'
import {UporabnikPodrobnostiComponent} from './seznami/uporabnik-podrobnosti.component'
import {FilmiByZanrComponent} from './seznami/filmi-by-zanr.component'
import {DateConvertPipe} from './seznami/pipes/date-convert.pipe'

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        SeznamiComponent,
        SeznamPodrobnostiComponent,
        ArtikelDodajComponent,
        FilmiComponent,
        FilmPodrobnostiComponent,
        UporabnikPodrobnostiComponent,
        FilmiByZanrComponent,
        DateConvertPipe
    ],
    providers: [SeznamiService, FilmiService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

