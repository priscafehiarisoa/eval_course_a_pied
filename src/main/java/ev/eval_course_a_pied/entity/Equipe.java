package ev.eval_course_a_pied.entity;

import ev.eval_course_a_pied.entity.user.Role;
import ev.eval_course_a_pied.entity.user.UserModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "equipe")
@ToString
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom_equipe", length = Integer.MAX_VALUE)
    private String nomEquipe;

    @ManyToOne
    @JoinColumn(name = "user_model_id")
    private UserModel userModel;

    public Equipe(String nomEquipe, UserModel userModel) {
        this.nomEquipe = nomEquipe;
        this.userModel = userModel;
    }

    public Equipe() {
    }
}