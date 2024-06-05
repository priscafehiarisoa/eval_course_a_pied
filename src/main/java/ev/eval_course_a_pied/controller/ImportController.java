package ev.eval_course_a_pied.controller;

import ev.eval_course_a_pied.services.GlobalServices;
import ev.eval_course_a_pied.services.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ImportController {

    private final GlobalServices globalServices;
    private final ImportService importService;

    public ImportController(GlobalServices globalServices, ImportService importService) {
        this.globalServices = globalServices;
        this.importService = importService;
    }

    @GetMapping("/admin/importPoints")
    public ModelAndView importPoints(){
        ModelAndView modelAndView =  new ModelAndView("admin/importPoints");
        String pageTitle = "Import Points ";
        modelAndView.addObject("pageTitle",pageTitle);
        return modelAndView;
    }
    @PostMapping("/admin/importPoints")
    public ModelAndView importPointsPost(@RequestPart("points") MultipartFile points){
        ModelAndView modelAndView =  new ModelAndView("admin/importPoints");

        String pageTitle = "Import Points ";
        modelAndView.addObject("pageTitle",pageTitle);
        String pointsFilePath=globalServices.uploadFile(points,modelAndView);
        if(points.isEmpty()){
            modelAndView.addObject("error","oops! you haven't uploaded any file");
            return modelAndView;
        }

        try {
            importService.insertIntoTablePoint(pointsFilePath);
            modelAndView.addObject("success","Files added t the database successfully");
        }catch (Exception e){
            modelAndView.addObject("error",e.getMessage());
        }
        return modelAndView;
    }
    @GetMapping("/admin/importDonnee")
    public ModelAndView importDonnee(){
        ModelAndView modelAndView =  new ModelAndView("admin/importDonnee");
        String pageTitle = "Import Donnee ";
        modelAndView.addObject("pageTitle",pageTitle);
        return modelAndView;
    }
    @PostMapping("/admin/importDonnee")
    public ModelAndView importPointsPost(@RequestPart("etape") MultipartFile etape,@RequestPart("resultat") MultipartFile resultat){
        ModelAndView modelAndView =  new ModelAndView("admin/importDonnee");

        String pageTitle = "Import Donnée ";
        modelAndView.addObject("pageTitle",pageTitle);
        if(etape.isEmpty() && resultat.isEmpty()){
            modelAndView.addObject("error","oops! you haven't uploaded any file");
            return modelAndView;
        }

        try {
            // make the imports here
            importService.insertListEtape(etape);
            importService.insertResultat(resultat);
            modelAndView.addObject("success","Files added t the database successfully");
        }catch (Exception e){
            modelAndView.addObject("error",e.getMessage());
        }
        return modelAndView;
    }



}
