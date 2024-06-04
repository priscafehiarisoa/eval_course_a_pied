package ev.eval_course_a_pied.services;

import ev.eval_course_a_pied.entity.*;
import ev.eval_course_a_pied.model.TempEtape;
import ev.eval_course_a_pied.model.TempPoints;
import ev.eval_course_a_pied.model.TempResultat;
import ev.eval_course_a_pied.repository.*;
import ev.eval_course_a_pied.services.filereading.ExelReadingService;
import ev.eval_course_a_pied.utils.ImportErrorLogs;
import ev.eval_course_a_pied.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ImportService {
    private final ExelReadingService exelReadingService;
    private final CsvService csvService;
    private final EquipeService equipeService;
    List<ImportErrorLogs> logErrors;
    private final TempPointsRepository tempPointsRepository;
    private final PointRepository pointRepository;
    private final EtapeRepository etapeRepository;
    private final TempResultatRepository tempResultatRepository;
    private final GenreRepository genreRepository;
    private final EquipeRepository equipeRepository;
    private final CoureurRepository coureurRepository;
    private final CoureurEtapeRepository coureurEtapeRepository;
    private final TempsCoureursParEtapeRepository tempsCoureursParEtapeRepository;

    public ImportService(ExelReadingService exelReadingService,
                         TempPointsRepository tempPointsRepository,
                         PointRepository pointRepository, CsvService csvService,
                         EtapeRepository etapeRepository,
                         TempResultatRepository tempResultatRepository, EquipeService equipeService,
                         GenreRepository genreRepository,
                         EquipeRepository equipeRepository,
                         CoureurRepository coureurRepository,
                         CoureurEtapeRepository coureurEtapeRepository,
                         TempsCoureursParEtapeRepository tempsCoureursParEtapeRepository) {
        this.exelReadingService = exelReadingService;
        this.tempPointsRepository = tempPointsRepository;
        this.pointRepository = pointRepository;
        this.csvService = csvService;
        this.etapeRepository = etapeRepository;
        this.tempResultatRepository = tempResultatRepository;
        this.equipeService = equipeService;
        this.genreRepository = genreRepository;
        this.equipeRepository = equipeRepository;
        this.coureurRepository = coureurRepository;
        this.coureurEtapeRepository = coureurEtapeRepository;
        this.tempsCoureursParEtapeRepository = tempsCoureursParEtapeRepository;
    }

    public List<TempPoints> readFile(String path,String delimiter) throws FileNotFoundException {
        this.logErrors=new ArrayList<>();
        Scanner scanner =new Scanner(new File(path));
        scanner.useDelimiter(delimiter);

        //object to return
        List<TempPoints> testTables1= new ArrayList<>();
//        -------------
        int i =0;
        // Skip the first line
        if (scanner.hasNextLine()) {
            scanner.nextLine();
            i++;
        }
        while (scanner.hasNextLine()){
            i++;
            String [] line = scanner.nextLine().split(delimiter);
            try {
                TempPoints testTable = new TempPoints(line[0], line[1]);
                testTables1.add(testTable);
            }
            catch (Exception e){
                this.logErrors.add(new ImportErrorLogs(i,e.getMessage(),0));
            }
        }
        scanner.close();
        return testTables1;
    }
    public List<TempEtape> readEtapeFile(MultipartFile file) throws FileNotFoundException {
        this.logErrors=new ArrayList<>();
        List<TempEtape> etapes = csvService.getData(TempEtape.class,file);
        return etapes;
    }


//    import Points

    public List<Point> insertIntoTablePoint (String path) throws FileNotFoundException {
        List<TempPoints> tempPoints = readFile(path,",");
        tempPointsRepository.saveAll(tempPoints);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < tempPoints.size(); i++) {
            points.add(new Point(tempPoints.get(i).getClassement(),tempPoints.get(i).getPoints()));
        }
        return pointRepository.saveAll(points);
    }

    public List<Etape> insertListEtape(MultipartFile file) throws FileNotFoundException {
        List<TempEtape> readedFromCsv = readEtapeFile(file);
        readedFromCsv.forEach(System.out::println);
        List<Etape> etapes = new ArrayList<>();
        for (int i = 0; i < readedFromCsv.size(); i++) {
            System.out.println("thegrfedasxq");
            Etape etape= new Etape(readedFromCsv.get(i).getRangEtape(),readedFromCsv.get(i).getNom(),readedFromCsv.get(i).getLongueur(),readedFromCsv.get(i).getHeureDepart(),readedFromCsv.get(i).getDateDepart(),readedFromCsv.get(i).getCoureurParEquipe());
            System.out.println(etape);
            etapes.add(etape);
        }
        etapeRepository.saveAll(etapes);
        etapes.forEach(System.out::println);

        return etapes;
    }

    public List<TempResultat> insertResultat (MultipartFile file) throws Exception {
        List<TempResultat> tempResultats = csvService.getData(TempResultat.class,file);
        tempResultatRepository.deleteAll();
        tempResultatRepository.saveAll(tempResultats);
        // insertEquipe
        insertListEquipe();
        // insert genre
        insertListGenre();
        // insert Coureur
        insertCoureur();
        // insert etape coureur
        insertEtapeCoureur();
        // insert Temps Coureur
        insertTempsCoureur();
        return new ArrayList<>();
    }

    // insert List Equipe
    public List<Equipe> insertListEquipe() throws Exception {
        List<String> listDistinct= tempResultatRepository.getDistinctEquipe();
        List<Equipe> listeEquipe= new ArrayList<>();
        for (int i = 0; i < listDistinct.size(); i++) {
            listeEquipe.add(equipeService.saveEquipe(listDistinct.get(i)));
        }
        return listeEquipe;
    }

    // insert Genre
    public List<Genre> insertListGenre(){
        List<String> distinctGenre = tempResultatRepository.getDistinctGenre();
        List<Genre> genres= new ArrayList<>();
        for (int i = 0; i < distinctGenre.size(); i++) {
            genres.add(new Genre(distinctGenre.get(i)));
        }
        genreRepository.saveAll(genres);
        return genres;
    }
    public List <Coureur> insertCoureur(){
        List<Object[]> coureurstemp = tempResultatRepository.getCoureur();
        List<Coureur> coureurs = new ArrayList<>();
        for (int i = 0; i < coureurstemp.size(); i++) {
            Equipe e = equipeRepository.findById(Utils.stringToInt(String.valueOf(coureurstemp.get(i)[3]))).orElse(null);
            Genre g = genreRepository.findById(Utils.stringToInt(String.valueOf(coureurstemp.get(i)[4]))).orElse(null);
            /* Coureur(String nomCoureur, LocalDate dateDeNaissance, Integer numeroDeDossard, Equipe equipe, List<Categorie> categories, Genre genre)   Coureur() */
            Coureur coureur = new Coureur(
                    String.valueOf(coureurstemp.get(i)[1]),
                    Utils.parseAnyStringToLocalDate(String.valueOf(coureurstemp.get(i)[2])),
                    Utils.stringToInt(String.valueOf(coureurstemp.get(i)[0])),
                    e,
                    new ArrayList<>(),
                    g
            );
            coureurs.add(coureur);
        }
        coureurRepository.saveAll(coureurs);
        return coureurs;
    }
    public List<CoureurEtape> insertEtapeCoureur(){
        List<Object[]> listCoureursEtape = tempResultatRepository.getEtapeCoureur();
        List<CoureurEtape> coureurEtapes = new ArrayList<>();
        for (int i = 0; i < listCoureursEtape.size(); i++) {
            Etape etape = etapeRepository.findById(Integer.valueOf(String.valueOf(listCoureursEtape.get(i)[0]))).orElse(null);
            Coureur coureur = coureurRepository.findById(Integer.valueOf(String.valueOf(listCoureursEtape.get(i)[1]))).orElse(null);
            coureurEtapes.add(new CoureurEtape(etape,coureur));
        }
        coureurEtapeRepository.saveAll(coureurEtapes);
        return coureurEtapes;
    }
    public List<TempsCoureursParEtape> insertTempsCoureur(){
        List<Object[]> listObject = tempResultatRepository.getTempsCoureur();
        List<TempsCoureursParEtape> tempsCoureursParEtapes = new ArrayList<>();
        for (int i = 0; i < listObject.size(); i++) {
            Etape  etape = etapeRepository.findById(Integer.valueOf(String.valueOf(listObject.get(i)[0]))).orElse(null);
            Coureur  coureur = coureurRepository.findById(Integer.valueOf(String.valueOf(listObject.get(i)[1]))).orElse(null);
            Timestamp heureDepart= (Timestamp) (listObject.get(i)[3]);
            Timestamp heureArrivee= (Timestamp) (listObject.get(i)[2]);
            tempsCoureursParEtapes.add(new TempsCoureursParEtape(coureur,heureDepart.toLocalDateTime(),heureArrivee.toLocalDateTime(),etape));
            System.out.println(tempsCoureursParEtapes.get(i));
        }
        return tempsCoureursParEtapeRepository.saveAll(tempsCoureursParEtapes);
    }
}
