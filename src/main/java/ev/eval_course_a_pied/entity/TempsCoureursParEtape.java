package ev.eval_course_a_pied.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "temps_coureurs_par_etapes")
public class TempsCoureursParEtape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coureur_id")
    private Coureur coureur;

    @Column(name = "heure_depart")
    private LocalDateTime heureDepart;

    @Column(name = "heure_arrive")
    private LocalDateTime heureArrive;
    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;

    public TempsCoureursParEtape(Coureur coureur, LocalDateTime heureDepart, LocalDateTime heureArrive, Etape etape) {
        this.coureur = coureur;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
        this.etape = etape;
    }

    public TempsCoureursParEtape() {
    }
}