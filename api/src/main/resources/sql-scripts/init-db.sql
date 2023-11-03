/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     28.10.2023. 10:15:25                         */
/*==============================================================*/

create table DOSTOPEN_V (KINOTEKA_ID INT4 not null, FILM_ID INT4 not null, constraint PK_DOSTOPEN_V primary key (KINOTEKA_ID, FILM_ID));

create unique index DOSTOPEN_V_PK on DOSTOPEN_V (KINOTEKA_ID, FILM_ID);
create index DOSTOPEN_V_FK on DOSTOPEN_V (KINOTEKA_ID);
create index DOSTOPEN_V2_FK on DOSTOPEN_V (FILM_ID);

create table FILM (FILM_ID INT4 not null, ZANR_ID INT4 not null, FILM_IME TEXT null, RATING INT4 null, DATUM_IZIDA DATE null, OPIS TEXT null, constraint PK_FILM primary key (FILM_ID));

create unique index FILM_PK on FILM (FILM_ID);
create index ZANR_FILMA_FK on FILM (ZANR_ID);

create table KINOTEKA (KINOTEKA_ID INT4 not null, KINOTEKA_IME TEXT null, SPLETNA_STRAN TEXT null, constraint PK_KINOTEKA primary key (KINOTEKA_ID));

create unique index KINOTEKA_PK on KINOTEKA (KINOTEKA_ID);

create table POGLEDANI_FILMI (FILM_ID INT4 not null, UPORABNIK_ID INT4 not null, constraint PK_POGLEDANI_FILMI primary key (FILM_ID, UPORABNIK_ID));

create unique index POGLEDANI_FILMI_PK on POGLEDANI_FILMI (FILM_ID, UPORABNIK_ID);
create index POGLEDANI_FILMI_FK on POGLEDANI_FILMI (FILM_ID);
create index POGLEDANI_FILMI2_FK on POGLEDANI_FILMI (UPORABNIK_ID);

create table UPORABNIK (UPORABNIK_ID INT4 not null, IME VARCHAR(50) null, PRIIMEK VARCHAR(100) null, UPORABNISKO_IME TEXT null, EMAIL TEXT null, constraint PK_UPORABNIK primary key (UPORABNIK_ID));

create unique index UPORABNIK_PK on UPORABNIK (UPORABNIK_ID);

create table VSEC (FILM_ID INT4 not null, UPORABNIK_ID INT4 not null, constraint PK_VSEC primary key (FILM_ID, UPORABNIK_ID));

create unique index VSEC_PK on VSEC (FILM_ID, UPORABNIK_ID);
create index VSEC_FK on VSEC (FILM_ID);
create index VSEC2_FK on VSEC (UPORABNIK_ID);

create table ZANR (ZANR_ID INT4 not null, ZANR_IME TEXT null, constraint PK_ZANR primary key (ZANR_ID));

create unique index ZANR_PK on ZANR (ZANR_ID);


alter table DOSTOPEN_V add constraint FK_DOSTOPEN_DOSTOPEN__KINOTEKA foreign key (KINOTEKA_ID) references KINOTEKA (KINOTEKA_ID) on delete restrict on update restrict;

alter table DOSTOPEN_V add constraint FK_DOSTOPEN_DOSTOPEN__FILM foreign key (FILM_ID) references FILM (FILM_ID) on delete restrict on update restrict;

alter table FILM add constraint FK_FILM_ZANR_FILM_ZANR foreign key (ZANR_ID) references ZANR (ZANR_ID) on delete restrict on update restrict;

alter table POGLEDANI_FILMI add constraint FK_POGLEDAN_POGLEDANI_FILM foreign key (FILM_ID) references FILM (FILM_ID) on delete restrict on update restrict;

alter table POGLEDANI_FILMI add constraint FK_POGLEDAN_POGLEDANI_UPORABNI foreign key (UPORABNIK_ID) references UPORABNIK (UPORABNIK_ID) on delete restrict on update restrict;

alter table VSEC add constraint FK_VSEC_VSEC_FILM foreign key (FILM_ID) references FILM (FILM_ID) on delete restrict on update restrict;

alter table VSEC add constraint FK_VSEC_VSEC2_UPORABNI foreign key (UPORABNIK_ID) references UPORABNIK (UPORABNIK_ID) on delete restrict on update restrict;



-- Sample data:
INSERT INTO DOSTOPEN_V (KINOTEKA_ID, FILM_ID) VALUES (1, 101);
INSERT INTO DOSTOPEN_V (KINOTEKA_ID, FILM_ID) VALUES (2, 102);
INSERT INTO DOSTOPEN_V (KINOTEKA_ID, FILM_ID) VALUES (3, 103);
INSERT INTO DOSTOPEN_V (KINOTEKA_ID, FILM_ID) VALUES (4, 104);
INSERT INTO DOSTOPEN_V (KINOTEKA_ID, FILM_ID) VALUES (5, 105);


INSERT INTO FILM (FILM_ID, ZANR_ID, FILM_IME, RATING, DATUM_IZIDA, OPIS) VALUES (101, 1, 'Movie A', 4, '2023-01-15', 'Description of Movie A');
INSERT INTO FILM (FILM_ID, ZANR_ID, FILM_IME, RATING, DATUM_IZIDA, OPIS) VALUES (102, 2, 'Movie B', 3, '2022-11-30', 'Description of Movie B');
INSERT INTO FILM (FILM_ID, ZANR_ID, FILM_IME, RATING, DATUM_IZIDA, OPIS) VALUES (103, 3, 'Movie C', 5, '2023-03-20', 'Description of Movie C');
INSERT INTO FILM (FILM_ID, ZANR_ID, FILM_IME, RATING, DATUM_IZIDA, OPIS) VALUES (104, 1, 'Movie D', 4, '2023-04-10', 'Description of Movie D');
INSERT INTO FILM (FILM_ID, ZANR_ID, FILM_IME, RATING, DATUM_IZIDA, OPIS) VALUES (105, 2, 'Movie E', 4, '2022-08-05', 'Description of Movie E');


INSERT INTO KINOTEKA (KINOTEKA_ID, KINOTEKA_IME, SPLETNA_STRAN) VALUES (1, 'Cinema X', 'www.cinemax.com');
INSERT INTO KINOTEKA (KINOTEKA_ID, KINOTEKA_IME, SPLETNA_STRAN) VALUES (2, 'Cinema Y', 'www.cinemay.com');
INSERT INTO KINOTEKA (KINOTEKA_ID, KINOTEKA_IME, SPLETNA_STRAN) VALUES (3, 'Cinema Z', 'www.cinemaz.com');
INSERT INTO KINOTEKA (KINOTEKA_ID, KINOTEKA_IME, SPLETNA_STRAN) VALUES (4, 'Cinema W', 'www.cinemaw.com');
INSERT INTO KINOTEKA (KINOTEKA_ID, KINOTEKA_IME, SPLETNA_STRAN) VALUES (5, 'Cinema V', 'www.cinemav.com');


INSERT INTO POGLEDANI_FILMI (FILM_ID, UPORABNIK_ID) VALUES (101, 1001);
INSERT INTO POGLEDANI_FILMI (FILM_ID, UPORABNIK_ID) VALUES (102, 1002);
INSERT INTO POGLEDANI_FILMI (FILM_ID, UPORABNIK_ID) VALUES (103, 1003);
INSERT INTO POGLEDANI_FILMI (FILM_ID, UPORABNIK_ID) VALUES (104, 1004);
INSERT INTO POGLEDANI_FILMI (FILM_ID, UPORABNIK_ID) VALUES (105, 1005);


INSERT INTO UPORABNIK (UPORABNIK_ID, IME, PRIIMEK, UPORABNISKO_IME, EMAIL) VALUES (1001, 'John', 'Doe', 'johndoe', 'john@example.com');
INSERT INTO UPORABNIK (UPORABNIK_ID, IME, PRIIMEK, UPORABNISKO_IME, EMAIL) VALUES (1002, 'Jane', 'Smith', 'janesmith', 'jane@example.com');
INSERT INTO UPORABNIK (UPORABNIK_ID, IME, PRIIMEK, UPORABNISKO_IME, EMAIL) VALUES (1003, 'Michael', 'Johnson', 'michaelj', 'michael@example.com');
INSERT INTO UPORABNIK (UPORABNIK_ID, IME, PRIIMEK, UPORABNISKO_IME, EMAIL) VALUES (1004, 'Emily', 'Wilson', 'emilyw', 'emily@example.com');
INSERT INTO UPORABNIK (UPORABNIK_ID, IME, PRIIMEK, UPORABNISKO_IME, EMAIL) VALUES (1005, 'David', 'Brown', 'davidb', 'david@example.com');


INSERT INTO ZANR (ZANR_ID, ZANR_IME) VALUES (1, 'Action');
INSERT INTO ZANR (ZANR_ID, ZANR_IME) VALUES (2, 'Drama');
INSERT INTO ZANR (ZANR_ID, ZANR_IME) VALUES (3, 'Comedy');
INSERT INTO ZANR (ZANR_ID, ZANR_IME) VALUES (4, 'Sci-Fi');
INSERT INTO ZANR (ZANR_ID, ZANR_IME) VALUES (5, 'Horror');
