-- public.kinoteka
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('CineCity', 'www.cinecity.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('Star Cineplex', 'www.starcineplex.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('FilmHouse Cinemas', 'www.filmhousecinemas.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('Silver Screen Cinemas', 'www.silverscreencinemas.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('Hollywood Cinemas', 'www.hollywoodcinemas.com');

-- public.uporabnik
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('alice@gmail.com', 'Alice', 'Smith', 'alice');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('bob@yahoo.com', 'Bob', 'Johnson', 'bob');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('carol@outlook.com', 'Carol', 'Williams', 'carol');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('dave@hotmail.com', 'Dave', 'Brown', 'dave');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('eve@aol.com', 'Eve', 'Davis', 'eve');

-- public.zanr
INSERT INTO zanr (ime) VALUES ('Action');
INSERT INTO zanr (ime) VALUES ('Comedy');
INSERT INTO zanr (ime) VALUES ('Drama');
INSERT INTO zanr (ime) VALUES ('Sci-Fi');
INSERT INTO zanr (ime) VALUES ('Horror');

-- public.film
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-01-01', 'Inception', 'A mind-bending thriller', 4, 4);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-02-15', 'Joker', 'A dark and intense character study', 5, 3);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-03-30', 'Avengers: Endgame', 'Epic superhero battle', 4, 1);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-04-10', 'Parasite', 'A dark comedy thriller', 5, 2);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-05-20', 'The Conjuring', 'Horror movie with jump scares', 2, 5);

-- public.film_kinoteka
INSERT INTO film_kinoteka (film_id, kinoteke_id) VALUES (1, 1);
INSERT INTO film_kinoteka (film_id, kinoteke_id) VALUES (2, 2);
INSERT INTO film_kinoteka (film_id, kinoteke_id) VALUES (3, 3);
INSERT INTO film_kinoteka (film_id, kinoteke_id) VALUES (4, 4);
INSERT INTO film_kinoteka (film_id, kinoteke_id) VALUES (5, 5);

-- public.film_uporabnik
INSERT INTO film_uporabnik (film_id, uporabnikipogledano_id, uporabnikivsec_id) VALUES (1, 1, 2);
INSERT INTO film_uporabnik (film_id, uporabnikipogledano_id, uporabnikivsec_id) VALUES (2, 2, 3);
INSERT INTO film_uporabnik (film_id, uporabnikipogledano_id, uporabnikivsec_id) VALUES (3, 3, 4);
INSERT INTO film_uporabnik (film_id, uporabnikipogledano_id, uporabnikivsec_id) VALUES (4, 4, 5);
INSERT INTO film_uporabnik (film_id, uporabnikipogledano_id, uporabnikivsec_id) VALUES (5, 5, 1);

-- public.kinoteka_film
INSERT INTO kinoteka_film (kinoteka_id, filmi_id) VALUES (1, 1);
INSERT INTO kinoteka_film (kinoteka_id, filmi_id) VALUES (2, 2);
INSERT INTO kinoteka_film (kinoteka_id, filmi_id) VALUES (3, 3);
INSERT INTO kinoteka_film (kinoteka_id, filmi_id) VALUES (4, 4);
INSERT INTO kinoteka_film (kinoteka_id, filmi_id) VALUES (5, 5);
