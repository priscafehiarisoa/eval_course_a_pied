package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.entity.Etape;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtapeRepository extends JpaRepository<Etape, Integer> {


    @Query(value = "select e from Etape e order by e.rangEtape ")
    Page<Etape> findAllEtapes(Pageable pageable);
    @Query(value = "select e from Etape e order by e.rangEtape ")
    List<Etape> findAllEtapes();
    
    @Query(value = "select points,rang_coureur, (heure_arrive-heure_depart) as durree, v_classement_etape.id,heure_arrive,heure_depart,coureur_id,v_classement_etape.etape_id from v_classement_etape where etape_id=:etape ",nativeQuery = true)
    List<Object[]> getClassementParIdEtape(@Param("etape") int idEtape);
}