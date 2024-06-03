package ev.eval_course_a_pied.controller;

import ev.eval_course_a_pied.entity.Coureur;
import ev.eval_course_a_pied.entity.Etape;
import ev.eval_course_a_pied.entity.TempsCoureursParEtape;
import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.exeption.CoureurEtapeExeption;
import ev.eval_course_a_pied.exeption.CoureurExeption;
import ev.eval_course_a_pied.exeption.EtapeExeption;
import ev.eval_course_a_pied.model.Pagination;
import ev.eval_course_a_pied.repository.EtapeRepository;
import ev.eval_course_a_pied.services.GlobalServices;
import ev.eval_course_a_pied.services.auth.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class EtapesController {

    private final EtapeRepository etapeRepository;
    private final GlobalServices globalServices;

    public EtapesController(EtapeRepository etapeRepository, GlobalServices globalServices) {
        this.etapeRepository = etapeRepository;
        this.globalServices = globalServices;
    }

    @GetMapping("/listEtapes")
    public ModelAndView listEtapes(@RequestParam(value = "pageNumber" , required = false) Integer pageNumber, @RequestParam( value = "pageSize", required = false) Integer pageSize){
        if(pageNumber==null)pageNumber=0;
        if(pageSize==null)pageSize=8;
        String pageTitle="Liste des Etapes";
        ModelAndView  modelAndView = new ModelAndView("global/etapeList");
        Page<Etape> films= etapeRepository.findAllEtapes( PageRequest.of(pageNumber,pageSize));
        Pagination pagination=new Pagination(pageNumber,pageSize, films.getTotalPages(), films.getNumberOfElements(), (pageNumber!=0),(pageNumber!= films.getTotalPages()-1));
        modelAndView.addObject("listObject",films);
        modelAndView.addObject("pagination",pagination);
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("pageRedirection","/listEtapes");
        return modelAndView;
    }
/**
 * todo : regler le problème de redirection ici
 * */
    @GetMapping("/admin/tempsCoureurParEtape")
    public ModelAndView tempsCoureurParEtape(@RequestParam("id") int idEtape){
        ModelAndView modelAndView= new ModelAndView("admin/tempsCoureurParEtapeForm");
        List<Coureur> coureurs = new ArrayList<>();
        String pageTitle= "Ajouter les temps des coureurs";
        try{
            coureurs=globalServices.getCoureursParEtape(idEtape);
        }catch (Exception e){
//            todo handle etape exeption
        }
        modelAndView.addObject("coureurs",coureurs);
        modelAndView.addObject("idEtape",idEtape);
        modelAndView.addObject("formError",new HashMap<String,String>());
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("pageRedirection","/admin/tempsCoureurParEtape");

        return modelAndView;
    }
    @PostMapping("/admin/tempsCoureurParEtape")
    public ModelAndView postTempsCoureurs(@RequestParam ("idEtape") int idEtape,@RequestParam("idCoureur") int idCoureur,@RequestParam("heureDepart") LocalDateTime dateDepart,@RequestParam("heureArrive") LocalDateTime dateArrive){

        ModelAndView modelAndView= new ModelAndView("admin/tempsCoureurParEtapeForm");
        List<Coureur> coureurs = new ArrayList<>();
        String pageTitle= "Ajouter les temps des coureurs";
        try{
            coureurs=globalServices.getCoureursParEtape(idEtape);
        }catch (Exception e){
//            todo handle etape exeption
        }
        modelAndView.addObject("coureurs",coureurs);
        modelAndView.addObject("idEtape",idEtape);
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("pageRedirection","/admin/tempsCoureurParEtape");        HashMap<String,String> formErrors= new HashMap<>();
        try{
            globalServices.saveTempsCoureur(idEtape,idCoureur,dateDepart,dateArrive);
        }catch (EtapeExeption etapeExeption){
            formErrors.put("etape", etapeExeption.getMessage());
        }
        catch (DateTimeException dateTimeException){
            formErrors.put("date", dateTimeException.getMessage());
        }
        catch (CoureurExeption coureurExeption){
            formErrors.put("coureur", coureurExeption.getMessage());
        }
        modelAndView.addObject("formError",formErrors);
        return modelAndView;
    }
    @GetMapping("/equipe/coureurParEtape")
    public ModelAndView coureurParEtape(@RequestParam("id") int idEtape){
        ModelAndView modelAndView= new ModelAndView("equipe/coureurParEquipeForm");
        List<Coureur> coureurs = new ArrayList<>();
        String pageTitle= "Ajouter des coureurs pour les étapes";
        try{
            coureurs=globalServices.getCoureurParEquipe();
        }catch (Exception e){
//            todo handle etape exeption
        }
        modelAndView.addObject("coureurs",coureurs);
        modelAndView.addObject("idEtape",idEtape);
        modelAndView.addObject("formError",new HashMap<String,String>());
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("pageRedirection","/equipe/coureurParEtape");

        return modelAndView;
    }
    /**
     * todo : regler le problème de redirection ici aussi
     * */
    @PostMapping("/equipe/coureurParEtape")
    public ModelAndView postcoureurParEtape(@RequestParam("idEtape") int idEtape,@RequestParam("idCoureur")int idCoureur){
        ModelAndView modelAndView= new ModelAndView("equipe/coureurParEquipeForm");
        List<Coureur> coureurs = new ArrayList<>();
        String pageTitle= "Ajouter des coureurs pour les étapes";
        try{
            coureurs=globalServices.getCoureurParEquipe();
        }catch (Exception e){
//            todo handle etape exeption
        }
        modelAndView.addObject("coureurs",coureurs);
        modelAndView.addObject("idEtape",idEtape);
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("pageRedirection","/equipe/coureurParEtape");
        HashMap<String,String> formErrors = new HashMap<>();
        try{
            globalServices.saveCoureurEtape(idCoureur,idEtape);
        }catch (Exception coureurEtapeExeption){
            formErrors.put("error", coureurEtapeExeption.getMessage());
        }
        modelAndView.addObject("formError",formErrors);
        return modelAndView;
    }
}
