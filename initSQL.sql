/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     28.10.2023. 10:15:25                         */
/*==============================================================*/


drop index DOSTOPEN_V2_FK;

drop index DOSTOPEN_V_FK;

drop index DOSTOPEN_V_PK;

drop table DOSTOPEN_V;

drop index ZANR_FILMA_FK;

drop index FILM_PK;

drop table FILM;

drop index KINOTEKA_PK;

drop table KINOTEKA;

drop index POGLEDANI_FILMI2_FK;

drop index POGLEDANI_FILMI_FK;

drop index POGLEDANI_FILMI_PK;

drop table POGLEDANI_FILMI;

drop index UPORABNIK_PK;

drop table UPORABNIK;

drop index VSEC2_FK;

drop index VSEC_FK;

drop index VSEC_PK;

drop table VSEC;

drop index ZANR_PK;

drop table ZANR;

/*==============================================================*/
/* Table: DOSTOPEN_V                                            */
/*==============================================================*/
create table DOSTOPEN_V (
   KINOTEKA_ID          INT4                 not null,
   FILM_ID              INT4                 not null,
   constraint PK_DOSTOPEN_V primary key (KINOTEKA_ID, FILM_ID)
);

/*==============================================================*/
/* Index: DOSTOPEN_V_PK                                         */
/*==============================================================*/
create unique index DOSTOPEN_V_PK on DOSTOPEN_V (
KINOTEKA_ID,
FILM_ID
);

/*==============================================================*/
/* Index: DOSTOPEN_V_FK                                         */
/*==============================================================*/
create  index DOSTOPEN_V_FK on DOSTOPEN_V (
KINOTEKA_ID
);

/*==============================================================*/
/* Index: DOSTOPEN_V2_FK                                        */
/*==============================================================*/
create  index DOSTOPEN_V2_FK on DOSTOPEN_V (
FILM_ID
);

/*==============================================================*/
/* Table: FILM                                                  */
/*==============================================================*/
create table FILM (
   FILM_ID              INT4                 not null,
   ZANR_ID              INT4                 not null,
   FILM_IME             TEXT                 null,
   RATING               INT4                 null,
   DATUM_IZIDA          DATE                 null,
   OPIS                 TEXT                 null,
   constraint PK_FILM primary key (FILM_ID)
);

/*==============================================================*/
/* Index: FILM_PK                                               */
/*==============================================================*/
create unique index FILM_PK on FILM (
FILM_ID
);

/*==============================================================*/
/* Index: ZANR_FILMA_FK                                         */
/*==============================================================*/
create  index ZANR_FILMA_FK on FILM (
ZANR_ID
);

/*==============================================================*/
/* Table: KINOTEKA                                              */
/*==============================================================*/
create table KINOTEKA (
   KINOTEKA_ID          INT4                 not null,
   KINOTEKA_IME         TEXT                 null,
   SPLETNA_STRAN        TEXT                 null,
   constraint PK_KINOTEKA primary key (KINOTEKA_ID)
);

/*==============================================================*/
/* Index: KINOTEKA_PK                                           */
/*==============================================================*/
create unique index KINOTEKA_PK on KINOTEKA (
KINOTEKA_ID
);

/*==============================================================*/
/* Table: POGLEDANI_FILMI                                       */
/*==============================================================*/
create table POGLEDANI_FILMI (
   FILM_ID              INT4                 not null,
   UPORABNIK_ID         INT4                 not null,
   constraint PK_POGLEDANI_FILMI primary key (FILM_ID, UPORABNIK_ID)
);

/*==============================================================*/
/* Index: POGLEDANI_FILMI_PK                                    */
/*==============================================================*/
create unique index POGLEDANI_FILMI_PK on POGLEDANI_FILMI (
FILM_ID,
UPORABNIK_ID
);

/*==============================================================*/
/* Index: POGLEDANI_FILMI_FK                                    */
/*==============================================================*/
create  index POGLEDANI_FILMI_FK on POGLEDANI_FILMI (
FILM_ID
);

/*==============================================================*/
/* Index: POGLEDANI_FILMI2_FK                                   */
/*==============================================================*/
create  index POGLEDANI_FILMI2_FK on POGLEDANI_FILMI (
UPORABNIK_ID
);

/*==============================================================*/
/* Table: UPORABNIK                                             */
/*==============================================================*/
create table UPORABNIK (
   UPORABNIK_ID         INT4                 not null,
   IME                  VARCHAR(50)          null,
   PRIIMEK              VARCHAR(100)         null,
   UPORABNISKO_IME      TEXT                 null,
   EMAIL                TEXT                 null,
   constraint PK_UPORABNIK primary key (UPORABNIK_ID)
);

/*==============================================================*/
/* Index: UPORABNIK_PK                                          */
/*==============================================================*/
create unique index UPORABNIK_PK on UPORABNIK (
UPORABNIK_ID
);

/*==============================================================*/
/* Table: VSEC                                                  */
/*==============================================================*/
create table VSEC (
   FILM_ID              INT4                 not null,
   UPORABNIK_ID         INT4                 not null,
   constraint PK_VSEC primary key (FILM_ID, UPORABNIK_ID)
);

/*==============================================================*/
/* Index: VSEC_PK                                               */
/*==============================================================*/
create unique index VSEC_PK on VSEC (
FILM_ID,
UPORABNIK_ID
);

/*==============================================================*/
/* Index: VSEC_FK                                               */
/*==============================================================*/
create  index VSEC_FK on VSEC (
FILM_ID
);

/*==============================================================*/
/* Index: VSEC2_FK                                              */
/*==============================================================*/
create  index VSEC2_FK on VSEC (
UPORABNIK_ID
);

/*==============================================================*/
/* Table: ZANR                                                  */
/*==============================================================*/
create table ZANR (
   ZANR_ID              INT4                 not null,
   ZANR_IME             TEXT                 null,
   constraint PK_ZANR primary key (ZANR_ID)
);

/*==============================================================*/
/* Index: ZANR_PK                                               */
/*==============================================================*/
create unique index ZANR_PK on ZANR (
ZANR_ID
);

alter table DOSTOPEN_V
   add constraint FK_DOSTOPEN_DOSTOPEN__KINOTEKA foreign key (KINOTEKA_ID)
      references KINOTEKA (KINOTEKA_ID)
      on delete restrict on update restrict;

alter table DOSTOPEN_V
   add constraint FK_DOSTOPEN_DOSTOPEN__FILM foreign key (FILM_ID)
      references FILM (FILM_ID)
      on delete restrict on update restrict;

alter table FILM
   add constraint FK_FILM_ZANR_FILM_ZANR foreign key (ZANR_ID)
      references ZANR (ZANR_ID)
      on delete restrict on update restrict;

alter table POGLEDANI_FILMI
   add constraint FK_POGLEDAN_POGLEDANI_FILM foreign key (FILM_ID)
      references FILM (FILM_ID)
      on delete restrict on update restrict;

alter table POGLEDANI_FILMI
   add constraint FK_POGLEDAN_POGLEDANI_UPORABNI foreign key (UPORABNIK_ID)
      references UPORABNIK (UPORABNIK_ID)
      on delete restrict on update restrict;

alter table VSEC
   add constraint FK_VSEC_VSEC_FILM foreign key (FILM_ID)
      references FILM (FILM_ID)
      on delete restrict on update restrict;

alter table VSEC
   add constraint FK_VSEC_VSEC2_UPORABNI foreign key (UPORABNIK_ID)
      references UPORABNIK (UPORABNIK_ID)
      on delete restrict on update restrict;

