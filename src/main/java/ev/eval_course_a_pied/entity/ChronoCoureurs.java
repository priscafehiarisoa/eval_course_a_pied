package ev.eval_course_a_pied.entity;

import ev.eval_course_a_pied.utils.Utils;
import lombok.Data;

import java.time.Duration;
@Data
public class ChronoCoureurs {
    Etape etape;
    Coureur coureur;
    Duration chrono;

    public String getChronoFormatted() {
        return Utils.formatDuration(getChrono());
    }

    public ChronoCoureurs(Etape etape, Coureur coureur, Duration chrono) {
        this.etape = etape;
        this.coureur = coureur;
        this.chrono = chrono;
    }

    public ChronoCoureurs() {
    }
}
