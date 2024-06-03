package ev.eval_course_a_pied.controller;

import ev.eval_course_a_pied.services.GlobalServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class DatabaseController {

    private final GlobalServices globalServices;

    public DatabaseController(GlobalServices globalServices) {
        this.globalServices = globalServices;
    }

    @GetMapping("reset")
    public String reset(){
        return "admin/reset";
    }

    @PostMapping("reset")
    public String resetForm(){
        globalServices.resetAll();
        return "admin/reset";
    }
}
