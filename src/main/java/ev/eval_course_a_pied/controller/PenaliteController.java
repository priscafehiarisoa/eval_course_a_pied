package ev.eval_course_a_pied.controller;

import ev.eval_course_a_pied.entity.Equipe;
import ev.eval_course_a_pied.entity.Etape;
import ev.eval_course_a_pied.entity.Penalite;
import ev.eval_course_a_pied.exeption.EquipeExeptions;
import ev.eval_course_a_pied.exeption.EtapeExeption;
import ev.eval_course_a_pied.model.Pagination;
import ev.eval_course_a_pied.repository.EquipeRepository;
import ev.eval_course_a_pied.repository.EtapeRepository;
import ev.eval_course_a_pied.repository.PenaliteRepository;
import ev.eval_course_a_pied.services.GlobalServices;
import ev.eval_course_a_pied.utils.Statics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Controller
public class PenaliteController {

    private final PenaliteRepository penaliteRepository;
    private final GlobalServices globalServices;
    private final EquipeRepository equipeRepository;
    private final EtapeRepository etapeRepository;

    public PenaliteController(PenaliteRepository penaliteRepository, GlobalServices globalServices,
                              EquipeRepository equipeRepository,
                              EtapeRepository etapeRepository) {
        this.penaliteRepository = penaliteRepository;
        this.globalServices = globalServices;
        this.equipeRepository = equipeRepository;
        this.etapeRepository = etapeRepository;
    }

    @GetMapping("admin/penalites")
    public ModelAndView getPenalitePage(@RequestParam(value = "pageNumber" , required = false) Integer pageNumber, @RequestParam( value = "pageSize", required = false) Integer pageSize, @ModelAttribute("formError")HashMap<String,String> formError){
        ModelAndView  modelAndView = new ModelAndView("admin/penalites");
        HashMap<String,String> error= new HashMap<>();
        if( formError!=null){
            error=formError;
        }
        if(pageNumber==null)pageNumber=0;
        if(pageSize==null)pageSize=8;
        String pageTitle="Liste des Pénalités";
        List<Equipe> equipe = equipeRepository.findAll();
        List<Etape> etapes = etapeRepository.findAllEtapes();
        Page<Penalite> films= penaliteRepository.getPenalitesByEtatNot(Statics.DELETE_STATE,PageRequest.of(pageNumber,pageSize));
        Pagination pagination=new Pagination(pageNumber,pageSize, films.getTotalPages(), films.getNumberOfElements(), (pageNumber!=0),(pageNumber!= films.getTotalPages()-1));
        modelAndView.addObject("listObject",films);
        modelAndView.addObject("etape",etapes);
        modelAndView.addObject("equipe",equipe);
        modelAndView.addObject("pagination",pagination);
        modelAndView.addObject("formError",error);
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("pageRedirection","admin/penalites");
        return modelAndView;
    }

    @PostMapping("admin/penalites")
    public String postPenalites(@RequestParam("idEtape") int idEtape, @RequestParam ("idEquipe")int idEquipe, @RequestParam("penalites") LocalTime penalites, RedirectAttributes redirectAttributes){
        String modelAndView ="redirect:/admin/penalites";
        HashMap<String,String> fromError = new HashMap<>();
        try{
            globalServices.savePenalites(idEtape, idEquipe,penalites);
        } catch (EquipeExeptions equipeExeptions){
            fromError.put("equipe",equipeExeptions.getMessage());
        }
        catch (EtapeExeption exeption){
            fromError.put("etape",exeption.getMessage());
        }
        redirectAttributes.addFlashAttribute("formError",fromError);
        return modelAndView;
    }

    @PostMapping("admin/deletePenalite")
    public ModelAndView deletePenalites (@RequestParam("id") int idPenalites){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/penalites");
        try {
            globalServices.deletePenalites(idPenalites);
        }catch (Exception e){
            modelAndView.addObject("error",e.getMessage());
        }
        return modelAndView;
    }
}
