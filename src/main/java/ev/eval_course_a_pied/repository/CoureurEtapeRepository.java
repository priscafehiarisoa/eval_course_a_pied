package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Coureur;
import ev.eval_course_a_pied.entity.CoureurEtape;
import ev.eval_course_a_pied.entity.Etape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CoureurEtapeRepository extends JpaRepository<CoureurEtape, Integer> {

    List<CoureurEtape> getCoureurEtapeByEtape(Etape etape);
    @Query("select s.coureur from CoureurEtape s where s.coureur.id=:coureur_id")
    Optional<CoureurEtape> getCoureurEtapeByCoureurId(int coureur_id);
    @Query(value ="select  count(coureur_etape.etape_id) from coureur_etape  join coureur c on c.id = coureur_etape.coureur_id  join equipe on c.equipe_id=equipe.id  where equipe.id=:idEquipe and etape_id=:idEtape",nativeQuery = true)
    int getCountCoureursParIdEquipeIDEtape(int idEtape,int idEquipe);

    boolean existsCoureurEtapeByCoureurAndEtape(Coureur coureur,Etape etape);
}