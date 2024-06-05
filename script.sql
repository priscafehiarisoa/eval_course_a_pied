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

-- VUES

-- ==============
-- VUE POUR AVOIR LES RANGS DES COUREURS PAR ETAPE
CREATE OR REPLACE VIEW v_rang_coureur_etape AS
    --test
SELECT ce.id,
       ce.coureur_id,
       c2.nom_coureur,
       nom_equipe,
       ce.etape_id,
       e.heure_depart,
       heure_arrive,
       (COALESCE(heure_arrive, NOW()) - e.heure_depart )          AS duree_course,
       dense_rank() OVER (PARTITION BY ce.etape_id ORDER BY (heure_arrive)) AS rang_coureur
FROM temps_coureurs_par_etapes ce
         join etapes e on e.id = ce.etape_id
         join coureur c2 on c2.id = ce.coureur_id
         join public.equipe e2 on c2.equipe_id = e2.id
ORDER BY ce.etape_id, heure_arrive;



-- VUE POUR AVOIR LES Points DES COUREURS EN FONCTION DE LEUR RANG
create or replace view v_classement_etape as
select row_number() over () as id,
       rce.id as id_rang_coureur_etape,
       etape_id,
       coureur_id,
       rang_coureur,
       heure_depart,
       heure_arrive,
       coalesce(points_obtenus, 0)  as points
from v_rang_coureur_etape rce
         left join points pc on rang_coureur = pc.classement
order by etape_id, rang_coureur;


-- modifier vues pour les rangs des coureurs par etapes + penalites
CREATE OR REPLACE VIEW v_rang_coureur_etape AS

SELECT ce.id,
       ce.coureur_id,
       c2.nom_coureur,
       nom_equipe,
       ce.etape_id,
       e.heure_depart,
       heure_arrive,
--        (COALESCE(heure_arrive, NOW()) - e.heure_depart )   AS duree_course,
       (COALESCE((heure_arrive +coalesce((select sum(penalite.penalites ) from penalite WHERE equipe_id=c2.equipe_id and etape_id=e.id and etat=0),'0 seconds'::interval)), NOW()) - e.heure_depart )   AS duree_course,
       dense_rank() OVER (PARTITION BY ce.etape_id ORDER BY (heure_arrive+coalesce((select sum(penalite.penalites ) from penalite WHERE equipe_id=c2.equipe_id and etape_id=e.id and etat=0),'0 seconds'::interval))) AS rang_coureur
FROM temps_coureurs_par_etapes ce
         join etapes e on e.id = ce.etape_id
         join coureur c2 on c2.id = ce.coureur_id
         join public.equipe e2 on c2.equipe_id = e2.id;


 select sum(v_classement_points_categorie.points) as totalPoints,categories_id,equipe_id, dense_rank() over (order by sum(v_classement_points_categorie.points) desc ) from
                 (select coalesce(points_obtenus, 0) as points, rang_coureur, coureur_id,etape_id, rce.equipe_id , categories_id
                  from (SELECT ce.id,
                               ce.coureur_id,
                               cc.categories_id,
                               ce.etape_id,
                               c2.equipe_id,
                               dense_rank() OVER (PARTITION BY ce.etape_id ORDER BY (heure_arrive+coalesce((select sum(penalite.penalites ) from penalite WHERE equipe_id=c2.equipe_id and etape_id=e.id and etat=0),'0 seconds'::interval))) AS rang_coureur
                        FROM temps_coureurs_par_etapes ce
                                 join etapes e on e.id = ce.etape_id
                                 join coureur c2 on c2.id = ce.coureur_id
                                 join coureur_categories cc on c2.id = cc.coureur_id
                        where categories_id=:categorie
                        ORDER BY ce.etape_id, heure_arrive) rce
                           join coureur c on c.id = rce.coureur_id
                           left join points pc on rang_coureur = pc.classement
                  order by etape_id, rang_coureur) v_classement_points_categorie
             group by categories_id, equipe_id order by totalPoints desc


--  alea jour 4
-- VUE POUR AVOIR LES Points DES COUREURS EN FONCTION DE LEUR RANG
create or replace view v_classement_etape as
select row_number() over () as id,
       rce.id as id_rang_coureur_etape,
       etape_id,
       coureur_id,
       rang_coureur,
       heure_depart,
       heure_arrive,
       coalesce(points_obtenus, 0)  as points,
       rce.penalites
from v_rang_coureur_etape rce
         left join points pc on rang_coureur = pc.classement
order by etape_id, rang_coureur;


-- modifier vues pour les rangs des coureurs par etapes + penalites
CREATE OR REPLACE VIEW v_rang_coureur_etape AS

SELECT ce.id,
       ce.coureur_id,
       c2.nom_coureur,
       nom_equipe,
       ce.etape_id,
       e.heure_depart,
       heure_arrive,
       (select sum(penalite.penalites ) from penalite WHERE equipe_id=c2.equipe_id and etape_id=e.id and etat=0) as penalites,
       (COALESCE((heure_arrive +coalesce((select sum(penalite.penalites ) from penalite WHERE equipe_id=c2.equipe_id and etape_id=e.id and etat=0),'0 seconds'::interval)), NOW()) - e.heure_depart )   AS duree_course,
       dense_rank() OVER (PARTITION BY ce.etape_id ORDER BY (heure_arrive+coalesce((select sum(penalite.penalites ) from penalite WHERE equipe_id=c2.equipe_id and etape_id=e.id and etat=0),'0 seconds'::interval))) AS rang_coureur
FROM temps_coureurs_par_etapes ce
         join etapes e on e.id = ce.etape_id
         join coureur c2 on c2.id = ce.coureur_id
         join public.equipe e2 on c2.equipe_id = e2.id;




