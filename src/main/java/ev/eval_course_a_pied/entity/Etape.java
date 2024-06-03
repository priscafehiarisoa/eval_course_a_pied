package ev.eval_course_a_pied.entity;

import ev.eval_course_a_pied.utils.Statics;
import ev.eval_course_a_pied.utils.Utils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "etapes")
public class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rang_etape")
    private Integer rangEtape;

    @Column(name = "lieu", length = Integer.MAX_VALUE)
    private String lieu;

    @Column(name = "etat")
    private Integer etat;

    @Column(name = "longueur")
    private Double longueur;

    @Column(name = "heure_depart")
    private LocalDateTime heureDepart;
    private LocalDate dateEtape;
    private int nombreCoureursParEquipe;

    public String getLongueurFormated(){
        return Utils.formatDouble(getLongueur());
    }

    public Etape(Integer rangEtape, String lieu, Integer etat, Double longueur, LocalDateTime heureDepart) {
        setRangEtape(rangEtape);
        setLieu(lieu);
        setEtat(etat);
        setLongueur(longueur);
        setHeureDepart(heureDepart);
    }
    public Etape(Integer rangEtape, String lieu, Double longueur, LocalTime heureDepart,LocalDate dateEtape) {
        setRangEtape(rangEtape);
        setLieu(lieu);
        setEtat(Statics.INIT_STATE);
        setLongueur(longueur);
        System.out.println(dateEtape.toString()+"T"+heureDepart.toString());
        setHeureDepart(LocalDateTime.of(dateEtape,heureDepart));
        setDateEtape(dateEtape);
    }

    public Etape( String lieu,  String longueur, String nombreCoureursParEquipe,String rangEtape,String dateDepart,String heureDepart) {
        setLieu(lieu.trim());
        setEtat(Statics.INIT_STATE);
        setLongueur(Utils.stringToDouble(longueur));
        setNombreCoureursParEquipe(Utils.stringToInt(nombreCoureursParEquipe));
        setHeureDepart(Utils.concatenateDateTime(dateDepart, heureDepart));
        setRangEtape(Utils.stringToInt(rangEtape));
    }


    public Etape() {
    }
}