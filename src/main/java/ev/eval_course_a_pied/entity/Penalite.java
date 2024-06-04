package ev.eval_course_a_pied.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;
    private LocalTime penalites;
    private int etat;

    public Penalite(Equipe equipe, Etape etape, LocalTime penalites) {
        this.equipe = equipe;
        this.etape = etape;
        this.penalites = penalites;
    }

    public Penalite() {
    }
}
