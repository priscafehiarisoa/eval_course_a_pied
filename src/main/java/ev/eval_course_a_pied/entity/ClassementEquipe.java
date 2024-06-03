package ev.eval_course_a_pied.entity;

import lombok.Data;

@Data
public class ClassementEquipe implements Comparable<ClassementEquipe>  {
    Equipe equipe;
    int rang ;

    double points;

    public ClassementEquipe(Equipe equipe, double points) {
        this.equipe = equipe;
        this.points = points;
    }

    public ClassementEquipe() {
    }
    @Override
    public int compareTo(ClassementEquipe other) {
        // Compare this.points with other.points
        // Multiply by -1 to sort in descending order
        return Double.compare(other.points, this.points);
    }

    @Override
    public String toString() {
        return "ClassementEquipe{" +
                "equipe=" + equipe +
                ", rang=" + rang +
                ", points=" + points +
                '}';
    }
}
