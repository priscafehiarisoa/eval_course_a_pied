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
    
    @Query(value = "select sum(point) points ,coureur.equipe_id from (select coalesce(points_obtenus,0) as point ,classement.* from  " +
            "    (select ROW_NUMBER() OVER (ORDER BY duree) as rang, * from v_classement where etape_id=:etape) as classement  " +
            "        LEFT JOIN  " +
            "    points  " +
            "    ON   rang = points.classement) as t  " +
            "    join coureur on coureur_id=coureur.id  " +
            "group by coureur.equipe_id",nativeQuery = true)
    List<Object[]> getPointsEquipeParEtape (@Param("etape") int idEtape);

    @Query("select d.userModel from Equipe d")
    List<UserModel> getUserModel();
}