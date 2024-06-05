package ev.eval_course_a_pied.services;
//
import ev.eval_course_a_pied.entity.*;
import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.exeption.CoureurEtapeExeption;
import ev.eval_course_a_pied.exeption.CoureurExeption;
import ev.eval_course_a_pied.exeption.EquipeExeptions;
import ev.eval_course_a_pied.exeption.EtapeExeption;
import ev.eval_course_a_pied.repository.*;
import ev.eval_course_a_pied.services.auth.RegisterService;
import ev.eval_course_a_pied.services.auth.UserService;
import ev.eval_course_a_pied.utils.Statics;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.*;
import java.util.*;

@Service
public class GlobalServices {
    private final UserService userService;
    private final EtapeRepository etapeRepository;
    private final CoureurEtapeRepository coureurEtapeRepository;
    private final CoureurRepository coureurRepository;
    private final TempsCoureursParEtapeRepository tempsCoureursParEtapeRepository;
    private final EquipeService equipeService;
    private final EquipeRepository equipeRepository;
    private final PointRepository pointRepository;
    private final GenreRepository genreRepository;
    private final CategorieRepository categorieRepository;
    private final RegisterService registerService;
    private final PenaliteRepository penaliteRepository;
    private final TempResultatRepository tempResultatRepository;
    private final TempPointsRepository tempPointsRepository;

    public GlobalServices(UserService userService,
                          EtapeRepository etapeRepository,
                          CoureurEtapeRepository coureurEtapeRepository,
                          CoureurRepository coureurRepository,
                          TempsCoureursParEtapeRepository tempsCoureursParEtapeRepository, EquipeService equipeService,
                          EquipeRepository equipeRepository,
                          PointRepository pointRepository,
                          GenreRepository genreRepository,
                          CategorieRepository categorieRepository, RegisterService registerService,
                          PenaliteRepository penaliteRepository,
                          TempResultatRepository tempResultatRepository,
                          TempPointsRepository tempPointsRepository) {
        this.userService = userService;

        this.etapeRepository = etapeRepository;
        this.coureurEtapeRepository = coureurEtapeRepository;
        this.coureurRepository = coureurRepository;
        this.tempsCoureursParEtapeRepository = tempsCoureursParEtapeRepository;
        this.equipeService = equipeService;
        this.equipeRepository = equipeRepository;
        this.pointRepository = pointRepository;
        this.genreRepository = genreRepository;
        this.categorieRepository = categorieRepository;
        this.registerService = registerService;
        this.penaliteRepository = penaliteRepository;
        this.tempResultatRepository = tempResultatRepository;
        this.tempPointsRepository = tempPointsRepository;
    }

    public List<Coureur> getCoureursParEtape(int etapeId) throws Exception {
        Optional<Etape> etape = etapeRepository.findById(etapeId);
        if(etape.isPresent()){
            List<CoureurEtape> coureurEtapes = coureurEtapeRepository.getCoureurEtapeByEtape(etape.get());
            List<Coureur> coureurs = new ArrayList<>();
            for (int i = 0; i < coureurEtapes.size(); i++) {
                coureurs.add(coureurEtapes.get(i).getCoureur());
            }
            return coureurs;
        }
        throw new Exception("etape inexistante ou indisponible");
    }

    public TempsCoureursParEtape saveTempsCoureur (int idEtape, int idCoureur, LocalDateTime dateDepart, LocalDateTime dateArrive) throws DateTimeException,CoureurExeption,EtapeExeption  {
        // get etape
        Optional<Etape> etape = etapeRepository.findById(idEtape);
        if(etape.isEmpty()){
            throw new EtapeExeption("etape inexistante ou indisponible");
        }
        // get Coureur
        Optional<Coureur> coureur = coureurRepository.findById(idCoureur);
        if(coureur.isEmpty()){
            throw new CoureurExeption("coureur inexistante ou indisponible");
        }
        // verification date
        if(dateDepart.isAfter(dateArrive)){
            throw new DateTimeException("l'heure de départ ne peut pas être apres la date d'arrivée");
        }
        TempsCoureursParEtape tempsCoureursParEtape = new TempsCoureursParEtape();
        tempsCoureursParEtape.setCoureur(coureur.get());
        tempsCoureursParEtape.setEtape(etape.get());
        tempsCoureursParEtape.setHeureDepart(dateDepart);
        tempsCoureursParEtape.setHeureArrive(dateArrive);
        tempsCoureursParEtapeRepository.save(tempsCoureursParEtape);
        return tempsCoureursParEtape;

    }

    public List<Coureur> getCoureurParEquipe() throws EquipeExeptions {
        Equipe equipe= equipeService.getConnectedEquipe();
        if(equipe==null){
            throw new EquipeExeptions("l'equipe est introuvable");
        }
        List<Coureur> coureurs= coureurRepository.getCoureursByEquipe(equipe);
        return coureurs;
    }

    public boolean validationNombreCoureurEquipeParEtape(Etape etape, int idEquipe){
        int countCoureurs= coureurEtapeRepository.getCountCoureursParIdEquipeIDEtape(etape.getId(),idEquipe);
        if(countCoureurs==etape.getNombreCoureursParEquipe()){
            return false;
        }
        return true;
    }

    public boolean validationDoublonsCoureurs(Coureur coureur, Etape etape){
        return coureurEtapeRepository.existsCoureurEtapeByCoureurAndEtape(coureur,etape);
    }

    public CoureurEtape saveCoureurEtape(int idCoureur, int idEtape) throws CoureurEtapeExeption {
        Etape etape = etapeRepository.findById(idEtape).orElse(null);
        Coureur coureur = coureurRepository.findById(idCoureur).orElse(null);
        System.out.println("cou : "+coureur);
        boolean test = validationNombreCoureurEquipeParEtape(etape,coureur.getEquipe().getId());
        if(validationNombreCoureurEquipeParEtape(etape,coureur.getEquipe().getId()) ){
            if( !validationDoublonsCoureurs(coureur,etape)) {
                CoureurEtape coureurEtape = new CoureurEtape(etape, coureur);
                coureurEtapeRepository.save(coureurEtape);
                return coureurEtape;
            }
            else{
                throw new CoureurEtapeExeption("vous ne pouvez pas ajouter un coureur plus d'une fois");
            }
        }
        else{
            throw new CoureurEtapeExeption("vous ne pouvez pas ajouter plus de coureurs dans cette etape");
        }

    }

    public HashMap<Integer,String> validationInsertCoureurInEtape( int idEtape, int idEquipe){
        Etape etape = etapeRepository.findById(idEtape).orElse(null);
        HashMap<Integer,String> formError=new HashMap<>();
        if(!validationNombreCoureurEquipeParEtape(etape,idEquipe)){
            formError.put(etape.getId(),"vous ne pouvez pas ajouter plus de coureurs dans cette etape");
        }
        return formError;
    }

    public String uploadFile(MultipartFile file, ModelAndView modelAndView){
        String uploadDirectory = System.getProperty("user.dir") + "/uploadedFile/";
        Path uploadPath = Paths.get(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            }
            catch (IOException e) {
                modelAndView.addObject("error","unable to create a directory");
            }
        }
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory, fileName);
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            modelAndView.addObject("error","there is a problem with the file ");

        }
        return filePath.toString();
    }
    public void resetAll(){
        List<UserModel> tobedeleted= equipeRepository.getUserModel();
        tempsCoureursParEtapeRepository.deleteAll();
        coureurEtapeRepository.deleteAll();
        coureurRepository.deleteAll();
        penaliteRepository.deleteAll();
        equipeRepository.deleteAll();
        etapeRepository.deleteAll();
        pointRepository.deleteAll();
        genreRepository.deleteAll();
//        categorieRepository.deleteAll();
        tempResultatRepository.deleteAll();
        tempPointsRepository.deleteAll();
        userService.resetuser(tobedeleted);
    }

//    get chrono coureurs par rapport aux coureurs d'une étape d'une equipe
    public List<ChronoCoureurs> getChronoCoureurEquipeEtape(Etape etape, Equipe equipe){
        // get liste des coureurs par equipe
        List<Coureur> coureurs = coureurEtapeRepository.getListCoureursParEquipeEtape(etape,equipe);
        List<ChronoCoureurs> chronoCoureurs = new ArrayList<>();
        for (int i = 0; i < coureurs.size(); i++) {
            Duration duration = tempsCoureursParEtapeRepository.getCoureurDuration(coureurs.get(i),etape);
            chronoCoureurs.add(new ChronoCoureurs(etape,coureurs.get(i),duration));
        }
        return chronoCoureurs;
    }
    public HashMap<Etape,List<ChronoCoureurs>> getChronoParCoureur(List<Etape> etapes,Equipe equipe){
        HashMap<Etape,List<ChronoCoureurs>> hashMap = new HashMap<>();
        for (int i = 0; i < etapes.size(); i++) {
            hashMap.put(etapes.get(i),getChronoCoureurEquipeEtape(etapes.get(i),equipe));
        }
        return hashMap;
    }

    public Coureur generateCategorie (Coureur coureur){
        List<Categorie> categories = new ArrayList<>();

        if((LocalDate.now().getYear()-coureur.getDateDeNaissance().getYear())<18){
            categories.add(categorieRepository.getCategorieByNomCategorie("junior").orElse(categorieRepository.getCategorieByNomCategorie("Junior").orElse(categorieRepository.getCategorieByNomCategorie("JUNIOR").orElse(null))));
        }
        else{
            categories.add(categorieRepository.getCategorieByNomCategorie("senior").orElse(categorieRepository.getCategorieByNomCategorie("Senior").orElse(categorieRepository.getCategorieByNomCategorie("SENIOR").orElse(null))));
        }
        String g = coureur.getGenre().getNom_genre();

        if(coureur.getGenre().getNom_genre().equals("M")){
            categories.add(categorieRepository.getCategorieByNomCategorie("homme").orElse(categorieRepository.getCategorieByNomCategorie("Homme").orElse(categorieRepository.getCategorieByNomCategorie("HOMME").orElse(null))));
        }
        else if(coureur.getGenre().getNom_genre().equals("F")){
            categories.add(categorieRepository.getCategorieByNomCategorie("femme").orElse(categorieRepository.getCategorieByNomCategorie("Femme").orElse(categorieRepository.getCategorieByNomCategorie("FEMME").orElse(null))));
        }


        coureur.setCategories(categories);
        return coureur;
    }

    public List<Coureur> generateCategories(){
        List<Coureur> temp = coureurRepository.findAll();
        List<Coureur> withCategories= new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            withCategories.add(generateCategorie(temp.get(i)));
        }
        return coureurRepository.saveAll(withCategories);
    }

    public Penalite savePenalites(int idEtape, int idEquipe, LocalTime penalites) throws EtapeExeption, EquipeExeptions {
        Etape etape = etapeRepository.findById(idEtape).orElse(null);
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);
        if(etape==null){
            throw new EtapeExeption("etape Inexistante ");
        }
        if (equipe == null){
            throw new EquipeExeptions("equipe inexistante");
        }
        Penalite penalite = new Penalite(equipe,etape,penalites);
        return penaliteRepository.save(penalite);
    }

    public void deletePenalites(int idPenalites) throws Exception {
        Penalite penalite = penaliteRepository.findByIdAndAndEtatNot(idPenalites, Statics.DELETE_STATE).orElse(null);
        if(penalite == null){
            throw new Exception("penalites non existante ");
        }
        penalite.setEtat(Statics.DELETE_STATE);
        penaliteRepository.save(penalite);
    }
}
