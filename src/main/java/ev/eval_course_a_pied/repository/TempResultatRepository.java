package ev.eval_course_a_pied.repository;

import ev.eval_course_a_pied.model.TempResultat;
import org.apache.poi.ss.formula.functions.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TempResultatRepository extends JpaRepository<TempResultat, Integer> {

    @Query( value = "select distinct equipe from temp_resultat where equipe not in (select equipe.nom_equipe from equipe)",nativeQuery = true)
    List<String> getDistinctEquipe();

    @Query(value = "select distinct temp_resultat.genre from temp_resultat where genre not in (select nom_genre from genre)",nativeQuery = true)
    List<String> getDistinctGenre();

    @Query(value = "select distinct numero_dossard,nom,date_naisssance,equipe.id as equipe_id ,public.genre.id as genre_id from temp_resultat join equipe on equipe=equipe.nom_equipe join genre on temp_resultat.genre= public.genre.nom_genre\n",nativeQuery = true)
    List<Object[]> getCoureur ();

    @Query(value = "select etapes.id,coureur.id from temp_resultat join coureur on coureur.numero_de_dossard = numero_dossard join etapes on etape_rang= etapes.rang_etape",nativeQuery = true)
    List<Object[]> getEtapeCoureur();

    @Query(value = "select etapes.id,coureur.id, arrivee,heure_depart from temp_resultat join coureur on coureur.numero_de_dossard = numero_dossard join etapes on etape_rang= etapes.rang_etape",nativeQuery = true)
    List<Object[]> getTempsCoureur();
}