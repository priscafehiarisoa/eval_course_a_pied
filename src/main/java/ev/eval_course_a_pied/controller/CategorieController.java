package ev.eval_course_a_pied.controller;

import ev.eval_course_a_pied.services.GlobalServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategorieController {

    private final GlobalServices globalServices;

    public CategorieController(GlobalServices globalServices) {
        this.globalServices = globalServices;
    }

    @GetMapping("generateCategories")
    public ModelAndView generateCategories(){
        ModelAndView modelAndView = new ModelAndView("global/generateCategories");
        String pageTitle = "Generate Categories";
        modelAndView.addObject("pageTitle",pageTitle);
        return modelAndView;
    }
    @PostMapping("generateCategories")
    public ModelAndView generateCategoriesP(){
        ModelAndView modelAndView = new ModelAndView("global/generateCategories");
        String pageTitle = "Generate Categories";
        modelAndView.addObject("pageTitle",pageTitle);
        globalServices.generateCategories();
        return modelAndView;
    }
}
