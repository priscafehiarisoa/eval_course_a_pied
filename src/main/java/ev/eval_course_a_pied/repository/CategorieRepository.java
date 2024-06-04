package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {

    Optional<Categorie> getCategorieByNomCategorie(String nomCategorie);
}