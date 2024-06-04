package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Coureur;
import ev.eval_course_a_pied.entity.Etape;
import ev.eval_course_a_pied.entity.TempsCoureursParEtape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Duration;

public interface TempsCoureursParEtapeRepository extends JpaRepository<TempsCoureursParEtape, Integer> {

    @Query("select (s.heureArrive-s.heureDepart) as durre from TempsCoureursParEtape s where s.coureur=:coureur and s.etape=:etape")
    Duration getCoureurDuration(Coureur coureur, Etape etape);

}