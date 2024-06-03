package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}