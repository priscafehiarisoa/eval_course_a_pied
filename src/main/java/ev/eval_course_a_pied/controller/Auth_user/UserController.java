package ev.eval_course_a_pied.controller.Auth_user;

import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.services.auth.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
 @RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userProfile")
    public ModelAndView userProfile(){
        ModelAndView modelAndView= new ModelAndView("auth/profile");
        UserModel userModel= UserService.getAuthenticatedUser();
        userModel.setPassword(null);
        String pageTitle= "profile" ;
        modelAndView.addObject("pageTitle",pageTitle);
        modelAndView.addObject("user",userModel);
        return modelAndView;
    }
}
