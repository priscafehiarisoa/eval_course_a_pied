package ev.eval_course_a_pied.entity.chart;

import lombok.Data;

@Data
public class Histogramme {
    int mois;
    double value;

    public Histogramme(int mois, double value) {
        setMois(mois);
        setValue(value);
    }
    public Histogramme(String mois, String value) {
        setMois(Integer.valueOf(mois));
        setValue(Double.valueOf(value));
    }

//    public static List<Histogramme> getMontantDevisParMoisParAnnee(DevisRepository devisRepository, int annee){
//        List<Object[]> result= devisRepository.devisParMoisParAnnee(annee);
//        return result.stream()
//                .map(row -> new Histogramme( String.valueOf(row[0]),String.valueOf(row[1])))
//                .collect(Collectors.toList());
//    }
}
