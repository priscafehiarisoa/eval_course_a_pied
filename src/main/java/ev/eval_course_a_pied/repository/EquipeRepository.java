package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Equipe;
import ev.eval_course_a_pied.entity.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

    Optional<Equipe> getEquipeByUserModel(UserModel userModel);
    
    @Query(value = "select sum(points),c.equipe_id from v_classement_etape join coureur c on v_classement_etape.coureur_id = c.id " +
            "group by c.equipe_id",nativeQuery = true)
    List<Object[]> getPointsEquipeParEtape ();

    @Query("select d.userModel from Equipe d")
    List<UserModel> getUserModel();

    @Query(value = " select sum(v_classement_points_categorie.points) as totalPoints,categories_id,equipe_id, dense_rank() over (order by sum(v_classement_points_categorie.points) desc ) from  " +
            "                 (select coalesce(points_obtenus, 0) as points, rang_coureur, coureur_id,etape_id, rce.equipe_id , categories_id  " +
            "                  from (SELECT ce.id,  " +
            "                               ce.coureur_id,  " +
            "                               cc.categories_id,  " +
            "                               ce.etape_id,  " +
            "                               c2.equipe_id,  " +
            "                               dense_rank() OVER (PARTITION BY ce.etape_id ORDER BY (heure_arrive+coalesce((select sum(penalite.penalites ) from penalite WHERE equipe_id=c2.equipe_id and etape_id=e.id and etat=0),'0 seconds'::interval))) AS rang_coureur  " +
            "                        FROM temps_coureurs_par_etapes ce  " +
            "                                 join etapes e on e.id = ce.etape_id  " +
            "                                 join coureur c2 on c2.id = ce.coureur_id  " +
            "                                 join coureur_categories cc on c2.id = cc.coureur_id  " +
            "                        where categories_id=:categorie  " +
            "                        ORDER BY ce.etape_id, heure_arrive) rce  " +
            "                           join coureur c on c.id = rce.coureur_id  " +
            "                           left join points pc on rang_coureur = pc.classement  " +
            "                  order by etape_id, rang_coureur) v_classement_points_categorie  " +
            "             group by categories_id, equipe_id order by totalPoints desc",nativeQuery = true)
    List<Object[]> getPointsEquipeParCategorieId(int categorie);
}