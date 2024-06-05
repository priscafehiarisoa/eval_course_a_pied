package ev.eval_course_a_pied.entity;

import ev.eval_course_a_pied.utils.Utils;
import lombok.Data;

import java.time.Duration;
@Data
public class ClassementCoureurParEtape {
    Duration durree;
    double points;
    int rangs;
    Coureur coureur;
    Etape etape;
    Duration penalites;
    Duration tempsFinal;

    public String getFormatedDurre(){
        return Utils.formatDuration(getDurree());
    }
    public String getFormatedPenalites(){
        return Utils.formatDuration(getPenalites());
    }
    public String getFormatedTempsFinal(){
        return Utils.formatDuration(getTempsFinal());
    }

    public ClassementCoureurParEtape(Duration durree, double points, int rangs, Coureur coureur, Etape etape) {
        setDurree(durree);
        setPoints(points);
        setRangs(rangs);
        setCoureur(coureur);
        setEtape(etape);
    }
    public ClassementCoureurParEtape(Duration durree, double points, int rangs, Coureur coureur, Etape etape,Duration penalites,Duration tempsFinal) {
        setDurree(durree);
        setPoints(points);
        setRangs(rangs);
        setCoureur(coureur);
        setEtape(etape);
        setPenalites(penalites);
        setTempsFinal(tempsFinal);
    }

    public ClassementCoureurParEtape() {
    }

    @Override
    public String toString() {
        return "ClassementCoureurParEtape{" +
                "durree=" + getFormatedDurre() +
                ", points=" + points +
                ", rangs=" + rangs +
                ", coureur=" + coureur +
                ", etape=" + etape +
                '}';
    }
}
