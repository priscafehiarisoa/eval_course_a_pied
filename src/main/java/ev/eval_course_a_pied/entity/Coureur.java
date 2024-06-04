package ev.eval_course_a_pied.entity;

import ev.eval_course_a_pied.utils.Utils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "coureur")
public class Coureur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom_coureur", length = Integer.MAX_VALUE)
    private String nomCoureur;

    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @Column(name = "numero_de_dossard")
    private Integer numeroDeDossard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToMany
    private List<Categorie> categories;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public String getDateDeNaissanceFormated() {
        return Utils.formatLocalDate(getDateDeNaissance());
    }

    public Coureur(String nomCoureur, LocalDate dateDeNaissance, Integer numeroDeDossard, Equipe equipe,List<Categorie> categories,Genre genre) {
        setNomCoureur(nomCoureur);
        setDateDeNaissance(dateDeNaissance);
        setNumeroDeDossard(numeroDeDossard);
        setEquipe(equipe);
        setCategories(categories);
        setGenre(genre);
    }

    public Coureur() {
    }


}