package ev.eval_course_a_pied.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nom_genre;

    public Genre(String nom_genre) {
        this.nom_genre = nom_genre;
    }

    public Genre() {
    }
}
