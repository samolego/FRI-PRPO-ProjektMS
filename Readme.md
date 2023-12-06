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
7. `GET` [localhost:8080/v1/kinoteke?filter=ime:LIKEIC:%cinema%](http://localhost:8080/v1/kinoteke?filter=ime:LIKEIC:%cinema%)
   * izpiše vse kinoteke, ki imajo v nazivu podniz "cinema", ne glede na velikost črk
8. `GET` [localhost:8080/v1/zanri?order=ime ASC&offset=2](http://localhost:8080/v1/zanri?order=ime%20ASC&offset=2)
   * izpiše žanre, urejene po abecedi naraščajoče in preskiči prva dva
9. `GET` [localhost:8080/v1/uporabniki?order=priimek ASC&limit=5](http://localhost:8080/v1/uporabniki?order=priimek%20ASC&limit=5)
   * uredi uporabnike po priimku naraščajoče in izpiše prvih 5
10. `GET` [localhost:8080/v1/filmi?filter=opis:LIKEIC:%classic%](http://localhost:8080/v1/filmi?filter=opis:LIKEIC:%classic%)
    * izpiše vse filme, ki imajo besedo "classic" v svojem opisu, ne glede na velikost črk
11. `GET` [localhost:8080/v1/filmi?filter=datumIzida:GT:'2023-10-13T22:00:00Z[UTC]'&limit=5](http://localhost:8080/v1/filmi?filter=datumIzida:GT:'2023-10-13T22:00:00Z[UTC]'&limit=5)
    * izpiše prvih pet filmov, izdanih po 13.10.2023
12. `GET` [localhost:8080/v1/filmi?filter=rating:EQ:5](http://localhost:8080/v1/filmi?filter=rating:EQ:5)
    * izpiše vse filme, ocenjene z oceno 5


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
