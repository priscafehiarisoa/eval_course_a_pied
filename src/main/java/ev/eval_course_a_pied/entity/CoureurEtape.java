package ev.eval_course_a_pied.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "coureur_etape")
public class CoureurEtape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etape_id")
    private Etape etape;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coureur_id")
    private Coureur coureur;
    private LocalDateTime dateAjout;

    public CoureurEtape(Etape etape, Coureur coureur) {
        setEtape(etape);
        setCoureur(coureur);
        setDateAjout(LocalDateTime.now());
    }
    public CoureurEtape(Etape etape, Coureur coureur,LocalDateTime dateAjout ) {
        setEtape(etape);
        setCoureur(coureur);
        setDateAjout(dateAjout);
    }

    public CoureurEtape() {
    }
}