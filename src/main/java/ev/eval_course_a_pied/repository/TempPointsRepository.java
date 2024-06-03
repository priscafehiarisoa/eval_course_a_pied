package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.model.TempPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempPointsRepository extends JpaRepository<TempPoints, Integer> {
}