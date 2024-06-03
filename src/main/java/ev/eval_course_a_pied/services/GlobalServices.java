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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    public GlobalServices(UserService userService,
                          EtapeRepository etapeRepository,
                          CoureurEtapeRepository coureurEtapeRepository,
                          CoureurRepository coureurRepository,
                          TempsCoureursParEtapeRepository tempsCoureursParEtapeRepository, EquipeService equipeService,
                          EquipeRepository equipeRepository,
                          PointRepository pointRepository,
                          GenreRepository genreRepository,
                          CategorieRepository categorieRepository, RegisterService registerService) {
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
        equipeRepository.deleteAll();
        etapeRepository.deleteAll();
        pointRepository.deleteAll();
        genreRepository.deleteAll();
        categorieRepository.deleteAll();
        userService.resetuser(tobedeleted);
    }


}
