package ev.eval_course_a_pied.model;

import ev.eval_course_a_pied.utils.Utils;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TempPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private int classement;
    private double points;

    public TempPoints(int classement, double points) {
        this.classement = classement;
        this.points = points;
    }
    public TempPoints(String classement, String points) {
        setClassement(Utils.stringToInt(classement));
        setPoints(Utils.stringToDouble(points));
    }

    public TempPoints() {
    }
}
