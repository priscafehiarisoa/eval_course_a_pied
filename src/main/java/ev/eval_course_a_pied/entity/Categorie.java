package ev.eval_course_a_pied.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nom_categorie", length = Integer.MAX_VALUE)
    private String nomCategorie;

    public Categorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Categorie() {

    }
}