package ev.eval_course_a_pied.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import ev.eval_course_a_pied.utils.DoubleParser;
import lombok.Data;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Data
public class TempEtape {


    @CsvBindByName(column = "etape")
    private String nom;

    @CsvCustomBindByName(converter = DoubleParser.class)
    private double longueur;

    @CsvBindByName(column = "nb coureur")
    private int coureurParEquipe;

    @CsvBindByName(column = "rang")
    private int rangEtape;

    @CsvBindByName(column = "date départ")
    @CsvDate("dd/MM/yyyy")
    private LocalDate dateDepart;

    @CsvBindByName(column = "heure départ")
    @CsvDate("HH:mm:ss")
    private LocalTime heureDepart;


    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
