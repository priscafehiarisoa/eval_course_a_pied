package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Coureur;
import ev.eval_course_a_pied.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoureurRepository extends JpaRepository<Coureur, Integer> {

    List<Coureur> getCoureursByEquipe(Equipe equipe);
}