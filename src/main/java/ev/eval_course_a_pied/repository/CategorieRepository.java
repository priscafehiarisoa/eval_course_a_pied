package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
}