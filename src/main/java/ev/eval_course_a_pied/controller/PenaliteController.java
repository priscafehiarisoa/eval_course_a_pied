package ev.eval_course_a_pied.controller;

import ev.eval_course_a_pied.entity.Etape;
import ev.eval_course_a_pied.entity.Penalite;
import ev.eval_course_a_pied.exeption.EquipeExeptions;
import ev.eval_course_a_pied.exeption.EtapeExeption;
import ev.eval_course_a_pied.model.Pagination;
import ev.eval_course_a_pied.repository.PenaliteRepository;
import ev.eval_course_a_pied.services.GlobalServices;
import ev.eval_course_a_pied.utils.Statics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalTime;
import java.util.HashMap;

@Controller
public class PenaliteController {

    private final PenaliteRepository penaliteRepository;
    private final GlobalServices globalServices;

    public PenaliteController(PenaliteRepository penaliteRepository, GlobalServices globalServices) {
        this.penaliteRepository = penaliteRepository;
        this.globalServices = globalServices;
    }

    @GetMapping("admin/penalites")
    public ModelAndView getPenalitePage(@RequestParam(value = "pageNumber" , required = false) Integer pageNumber, @RequestParam( value = "pageSize", required = false) Integer pageSize){
        if(pageNumber==null)pageNumber=0;
        if(pageSize==null)pageSize=8;
        String pageTitle="Liste des Pénalités";
        ModelAndView  modelAndView = new ModelAndView("admin/penalites");
        Page<Penalite> films= penaliteRepository.getPenalitesByEtatNot(Statics.DELETE_STATE,PageRequest.of(pageNumber,pageSize));
        Pagination pagination=new Pagination(pageNumber,pageSize, films.getTotalPages(), films.getNumberOfElements(), (pageNumber!=0),(pageNumber!= films.getTotalPages()-1));
        modelAndView.addObject("listObject",films);
        modelAndView.addObject("pagination",pagination);
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("pageRedirection","admin/penalites");
        return modelAndView;
    }

    @PostMapping("admin/penalites")
    public ModelAndView postPenalites(@RequestParam("idEtape") int idEtape, @RequestParam ("idEquipe")int idEquipe,@RequestParam("penalites") LocalTime penalites){
        ModelAndView modelAndView = new ModelAndView("redirect:admin/penalites");
        HashMap<String,String> fromError = new HashMap<>();
        try{
        globalServices.savePenalites(idEtape, idEquipe,penalites);
        } catch (EquipeExeptions equipeExeptions){
            fromError.put("equipe",equipeExeptions.getMessage());
        }
        catch (EtapeExeption exeption){
            fromError.put("etape",exeption.getMessage());
        }
        fromError.put("test","test");
        modelAndView.addObject("formError",fromError);
        return modelAndView;
    }

    @PostMapping("admin/deletePenalite")
    public ModelAndView deletePenalites (@RequestParam("id") int idPenalites){
        ModelAndView modelAndView = new ModelAndView("redirect:admin/penalites");
        try {
            globalServices.deletePenalites(idPenalites);
        }catch (Exception e){
            modelAndView.addObject("error",e.getMessage());
        }
        return modelAndView;
    }
}
