package ev.eval_course_a_pied.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@ToString
@Entity
public class TempResultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @CsvBindByName(column = "etape_rang")
    int etape_rang;
    @CsvBindByName(column = "numero dossard")
    int numero_dossard;
    @CsvBindByName(column = "nom")
    String nom;
    @CsvBindByName(column = "genre")
    String genre;
    @CsvBindByName(column = "date naissance")
    @CsvDate("dd/MM/yyyy")
    LocalDate date_naisssance;
    @CsvBindByName(column = "equipe")
    String equipe;
    @CsvBindByName(column = "arrivée")
    @CsvDate("dd/MM/yyyy HH:mm:ss")
    LocalDateTime arrivee;
}
