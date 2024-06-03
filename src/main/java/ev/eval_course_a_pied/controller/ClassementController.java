package ev.eval_course_a_pied.controller;

import ev.eval_course_a_pied.entity.ClassementCoureurParEtape;
import ev.eval_course_a_pied.entity.ClassementEquipe;
import ev.eval_course_a_pied.entity.Etape;
import ev.eval_course_a_pied.repository.EtapeRepository;
import ev.eval_course_a_pied.services.ClassementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassementController {

    private final EtapeRepository etapeRepository;
    private final ClassementService classementService;

    public ClassementController(EtapeRepository etapeRepository, ClassementService classementService) {
        this.etapeRepository = etapeRepository;
        this.classementService = classementService;
    }

    /**
     * liste des etapes
     * liste an'ze classement ho an'ny etape voalohany raha tsisy / na ho an'ny etape selectionnee raha misy
     * */
    @GetMapping("classementGeneralPArEtape")
    public ModelAndView classementGeneralParEtape(@RequestParam(value = "etapeId",required = false) Integer etapeId){
        ModelAndView modelAndView = new ModelAndView("global/classementGeneralParEtape");
        List<Etape> etapes = etapeRepository.findAllEtapes();
        List<ClassementCoureurParEtape> classement = new ArrayList<>();
        Etape current = new Etape();
        if(etapeId==null && !etapes.isEmpty()){
            etapeId=etapes.get(0).getId();
            current = etapes.get(0);
            classement= classementService.getClassementParEtape(etapeId);

        }
        else if(etapeId!=null){
            current = etapeRepository.findById(etapeId).orElse(null);
            classement= classementService.getClassementParEtape(etapeId);

        }
        else{
            classement= new ArrayList<>();
        }
        String pageTitle = "liste des classement de l'étape : "+ current.getLieu();
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("etapes",etapes);
        modelAndView.addObject("classement",classement);
        return modelAndView;
    }

    @GetMapping("classementGeneralParEquipe")
    public ModelAndView classementGeneralParEquipe(){
        ModelAndView modelAndView = new ModelAndView("global/classementGeneralParEquipe");
        List<ClassementEquipe> classement = new ArrayList<>();
        classement= classementService.getClassementEquipe();
        String pageTitle = "liste des classement par equipe ";
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("classement",classement);
        return modelAndView;
    }
}
