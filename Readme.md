# MikroStoritev - Priporočilnica Filmov
Zasnovana na vajah predmeta PRPO - Postopki razvoja programske opreme.

## Rest API

Primeri klicev:
1. `GET` [http://localhost:8080/v1/uporabniki?limit=10&order=ime%20DESC](http://localhost:8080/v1/uporabniki?limit=10&order=ime%20DESC)
   * sortira uporabnike po imenu padajoče (Ž-A) in izpiše prvih 10.
2. `GET` [http://localhost:8080/v1/filmi?order=datumIzida DESC](http://localhost:8080/v1/filmi?order=datumIzida%20DESC)
   * izpiše filme, od najnovejšega do najstarejšega
3. `GET` [http://localhost:8080/v1/filmi?filter=rating:GTE:5](http://localhost:8080/v1/filmi?filter=rating:GTE:5)
   * izpiše filme, ki imajo rating večji ali enak 5
4. `GET` [http://localhost:8080/v1/filmi?order=ime](http://localhost:8080/v1/filmi?order=ime)
   * izpiše filme, urejene po imenu (naslovu)
5. `GET` [localhost:8080/v1/uporabniki?filter=email:LIKE:'%yahoo.com'&order=priimek ASC](http://localhost:8080/v1/uporabniki?filter=email:LIKE:%27%yahoo.com%27&order=priimek%20ASC)
    * izpiše vse uporabnike, ki uporabljajo yahoo mail in jih uredi po priimku naraščajoče (A-Ž)
6. `GET` [localhost:8080/v1/kinoteke?fields=spletnaStran&offset=2](http://localhost:8080/v1/kinoteke?fields=spletnaStran&offset=2)
    * izpiše vse kinoteke, ki imajo spletno stran in preskoči prvi dve


## Baza
### Filmi
- film_id (primarni ključ)
- film_ime
- rating
- datum_izida
- opis
- informacija o žanru (tuji ključ)
- kinoteke, kjer je film dostopen
- uporabniki, ki so gledali film 
- uporabniki, katerim je film bil všeč

### Žanr
- zanr_id (primarni ključ)
- ime žanra
- filmi tega žanra

### Kinoteka
- kinoteka_id (primarni ključ)
- kinoteka_ime
- spletna_stran
- dostopni filmi

### Uporabnik
- uporabnik_id (primarni ključ)
- ime 
- priimek
- uporabnisko_ime
- email
- pogledani filmi
- filmi, ki so uporabniku bili všeč


Konceptualni model:
![model.jpg](model.jpg)
