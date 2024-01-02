import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

import {Observable, retry} from 'rxjs';

import { catchError } from 'rxjs/operators';
import {NakupovalniSeznam} from "../models/seznam";
import {Film} from "../models/film";
import {Uporabnik} from "../models/uporabnik";
import {Zanr} from "../models/zanr";

@Injectable()
export class FilmiService {

    private headers = new HttpHeaders({'Content-Type': 'application/json'});
    private url = 'http://localhost:8080/v1';

    constructor(private http: HttpClient) {
    }

    getFilmi(): Observable<Film[]> {
        return this.http.get<Film[]>(this.url + '/filmi')
            .pipe(catchError(this.handleError));
    }

    getFilm(id: number): Observable<Film> {
        const url = `${this.url}/filmi/${id}`;
        return this.http.get<Film>(url)
            .pipe(catchError(this.handleError));
    }


    public deleteFilm(filmId: number) {
        const urlDelete: string = `${this.url}/filmi/${filmId}`;

        return this.http
            .delete(urlDelete, {headers: this.headers})
            .pipe(retry(1), catchError(this.handleError));
    }

    public createFilm(
        film: Film
    ): Observable<Film> {
        const url: string = `${this.url}/filmi`;
        let datumIzida = film.datumIzida + 'T22:00:00Z[UTC]';
        console.log(datumIzida)


        let body = {
            "name": film.ime,
            "opis": film.opis,
            "zanrId": film.zanr.id,
            "datumIzida": datumIzida
        }
        console.log(body);
        let headers = new HttpHeaders({
            "Content-Type": "application/json"
        });

        return this.http
            .post<Film>(url, body, { headers })
            .pipe(retry(1), catchError(this.handleError));
    }

    getUporabnik(id: number): Observable<Uporabnik> {
        const url = `${this.url}/uporabniki/${id}`;
        return this.http.get<Uporabnik>(url)
            .pipe(catchError(this.handleError));
    }

    getZanri(): Observable<Zanr[]> {
        return this.http.get<Zanr[]>(this.url + '/zanri')
            .pipe(catchError(this.handleError));
    }

    getZanr(id: number): Observable<Zanr> {
        const url = `${this.url}/zanri/${id}`;
        return this.http.get<Zanr>(url)
            .pipe(catchError(this.handleError));
    }

    private handleError(error: any): Promise<any> {
        console.error('Prišlo je do napake', error);
        return Promise.reject(error.message || error);
    }
}

