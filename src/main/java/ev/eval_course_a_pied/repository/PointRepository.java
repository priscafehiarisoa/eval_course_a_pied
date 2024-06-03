package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {
}