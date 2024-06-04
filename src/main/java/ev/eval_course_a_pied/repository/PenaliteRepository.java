package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Penalite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {

    Page<Penalite> getPenalitesByEtatNot(int etat, Pageable pageable);

    Optional<Penalite> findByIdAndAndEtatNot(int id, int etat);
}