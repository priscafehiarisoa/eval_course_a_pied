package ev.eval_course_a_pied.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "points_obtenus")
    private Double pointsObtenus;
    private int classement;

    public Point(Integer classement, Double pointsObtenus) {
        this.classement = classement;
        this.pointsObtenus = pointsObtenus;
    }

    public Point() {
    }
}