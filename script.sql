create table etapes (
                        id serial primary key ,
                        rang_etape int,
                        lieu varchar,
                        etat int ,
                        longueur double precision,
                        heure_depart timestamp
);

create table equipe (
                        id serial primary key ,
                        nom_equipe varchar,
                        username varchar,
                        password varchar(255)
);

create table coureur (
                         id serial primary key ,
                         nom_coureur varchar ,
                         date_de_naissance date,
                         numero_de_dossard int,
                         equipe_id int references equipe(id)

);

create table categorie(
                          id serial primary key ,
                          nom_categorie varchar
);

create table coureur_categorie(
                                  id serial primary key ,
                                  coureur_id int references coureur(id),
                                  categorie_id int references categorie(id)
);

create table coureur_etape(
                              id serial primary key ,
                              etape_id int references etapes(id),
                              coureur_id int references etapes(id)
);

create table temps_coureurs_par_etapes(
                                          id serial primary key ,
                                          coureur_id int references coureur(id),
                                          heure_depart timestamp,
                                          heure_arrive timestamp
);

create table points (
                        id serial primary key ,
                        points_obtenus double precision
);

-- ///////

select  count(coureur_etape.etape_id) from coureur_etape
                                               join coureur c on c.id = coureur_etape.coureur_id
                                               join equipe on c.equipe_id=equipe.id
where equipe.id=:idEquipe and etape_id=:idEtape

select coureur_etape.coureur_id from coureur_etape

select heure_arrive-temps_coureurs_par_etapes.heure_depart,* from temps_coureurs_par_etapes  ;

SELECT
    ROW_NUMBER() OVER (ORDER BY heure_arrive - temps_coureurs_par_etapes.heure_depart) AS rang,
        heure_arrive - temps_coureurs_par_etapes.heure_depart AS duree,
    *
FROM
    temps_coureurs_par_etapes;
-- ==================

-- =================
create view v_classement as SELECT
                                heure_arrive - temps_coureurs_par_etapes.heure_depart AS duree,
                                temps_coureurs_par_etapes.*
                            FROM
                                temps_coureurs_par_etapes

select * from v_classement

select coalesce(points_obtenus,0) as point ,classement.* from
    (select ROW_NUMBER() OVER (ORDER BY duree) as rang, * from v_classement where etape_id=:etape) as classement
        LEFT JOIN
    points
    ON   rang = points.id;

-- classement general par equipe

select sum(point) points ,coureur.equipe_id from (select coalesce(points_obtenus,0) as point ,classement.* from
    (select ROW_NUMBER() OVER (ORDER BY duree) as rang, * from v_classement where etape_id=:etape) as classement
        LEFT JOIN
    points
    ON   rang = points.id) as t
                                                     join coureur on coureur_id=coureur.id
group by coureur.equipe_id

select * from etapes


select distinct temp_resultat.genre from temp_resultat where genre not in (select nom_genre from genre)