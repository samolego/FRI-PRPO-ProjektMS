-- public.kinoteka
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('CineCity', 'www.cinecity.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('Star Cineplex', 'www.starcineplex.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('FilmHouse Cinemas', 'www.filmhousecinemas.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('Silver Screen Cinemas', 'www.silverscreencinemas.com');
INSERT INTO kinoteka (ime, spletna_stran) VALUES ('Hollywood Cinemas', 'www.hollywoodcinemas.com');

-- public.uporabnik
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('alice@gmail.com', 'Alice', 'Smith', 'alice');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('carol@outlook.com', 'Carol', 'Williams', 'carol');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('olivia@aol.com', 'Olivia', 'Hernandez', 'olivia');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('henry@outlook.com', 'Henry', 'Martinez', 'henry');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('isabella@hotmail.com', 'Isabella', 'Hernandez', 'isabella');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('rachel@outlook.com', 'Rachel', 'Wilson', 'rachel');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('jack@aol.com', 'Jack', 'Lopez', 'jack');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('kate@gmail.com', 'Kate', 'Gonzalez', 'kate');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('leonard@yahoo.com', 'Leonard', 'Wilson', 'leonard');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('mary@outlook.com', 'Mary', 'Anderson', 'mary');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('nathan@hotmail.com', 'Nathan', 'Martinez', 'nathan');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('peter@gmail.com', 'Peter', 'Lopez', 'peter');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('quinn@yahoo.com', 'Quinn', 'Gonzalez', 'quinn');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('dave@hotmail.com', 'Dave', 'Brown', 'dave');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('eve@aol.com', 'Eve', 'Davis', 'eve');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('frank@gmail.com', 'Frank', 'Wilson', 'frank');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('grace@yahoo.com', 'Grace', 'Anderson', 'grace');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('sam@hotmail.com', 'Sam', 'Anderson', 'sam');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('bob@yahoo.com', 'Bob', 'Johnson', 'bob');
INSERT INTO uporabnik (email, ime, priimek, uporabnisko_ime) VALUES ('thomas@aol.com', 'Thomas', 'Martinez', 'thomas');

-- public.zanr
INSERT INTO zanr (ime) VALUES ('Action');
INSERT INTO zanr (ime) VALUES ('Comedy');
INSERT INTO zanr (ime) VALUES ('Drama');
INSERT INTO zanr (ime) VALUES ('Sci-Fi');
INSERT INTO zanr (ime) VALUES ('Horror');
INSERT INTO zanr (ime) VALUES ('Crime');
INSERT INTO zanr (ime) VALUES ('Animation');

-- public.film
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-01-01', 'Inception', 'A mind-bending thriller', 4, 4);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-02-15', 'Joker', 'A dark and intense character study', 5, 3);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-03-30', 'Avengers: Endgame', 'Epic superhero battle', 4, 1);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-04-10', 'Parasite', 'A dark comedy thriller', 5, 2);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-05-20', 'The Conjuring', 'Horror movie with jump scares', 2, 5);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-06-05', 'The Matrix', 'Sci-fi action', 4, 4);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-07-18', 'The Shawshank Redemption', 'Drama about hope and survival', 5, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-08-22', 'The Godfather', 'Classic crime drama', 5, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-09-10', 'Pulp Fiction', 'Quirky crime tale', 4, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-10-14', 'Interstellar', 'Sci-fi odyssey', 4, 4);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-11-28', 'Forrest Gump', 'Heartwarming drama', 5, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2023-12-30', 'The Dark Knight', 'Superhero crime thriller', 5, 3);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-01-12', 'Fight Club', 'Mind-bending drama', 4, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-02-20', 'The Silence of the Lambs', 'Suspenseful psychological thriller', 5, 5);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-03-25', 'The Prestige', 'Mystery drama', 4, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-04-30', 'Gladiator', 'Epic historical drama', 5, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-05-10', 'The Departed', 'Gritty crime thriller', 4, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-06-15', 'The Green Mile', 'Emotional drama', 5, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-07-20', 'The Usual Suspects', 'Twisty crime mystery', 4, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-08-28', 'The Lion King', 'Animated family classic', 5, 7);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-09-05', 'Inglourious Basterds', 'Alternate history war film', 4, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-10-10', 'The Shining', 'Psychological horror', 5, 5);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-11-15', 'The Truman Show', 'Satirical comedy-drama', 4, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2024-12-20', 'Goodfellas', 'Gangster crime film', 5, 6);
INSERT INTO film (datum_izida, ime, opis, rating, zanr_id) VALUES ('2025-01-25', 'The Avengers', 'Superhero ensemble', 4, 1);

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
