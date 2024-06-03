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
    
    @Query(value = "select coalesce(points_obtenus,0) as point ,classement.* from " +
            "                        (select DENSE_RANK() OVER (ORDER BY duree) as rang, * from v_classement where etape_id=:etape) as classement " +
            "                            LEFT JOIN " +
            "                         points " +
            "                         ON   rang = points.classement",nativeQuery = true)
    public List<Object[]> getClassementParIdEtape(@Param("etape") int idEtape);
}