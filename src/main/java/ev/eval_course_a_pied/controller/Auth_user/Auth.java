package ev.eval_course_a_pied.controller.Auth_user;

import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.services.auth.RegisterService;
import ev.eval_course_a_pied.services.auth.UserService;
import ev.eval_course_a_pied.utils.Statics;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Auth {
    private final RegisterService registerService;

    public Auth(  RegisterService registerService) {
        this.registerService = registerService;
    }
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView= new ModelAndView("index");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if(authority.getAuthority().equals(Statics.ADMINROLE)){
                modelAndView= new ModelAndView("redirect:/classementGeneralPArEtape");
            }
            if(authority.getAuthority().equals(Statics.EQUIPEROLE)){
                modelAndView= new ModelAndView("redirect:/classementGeneralParEquipe");
            }
        }
        if(modelAndView==null){
            modelAndView= new ModelAndView("redirect:/login");
        }

        return modelAndView;
    }

    @GetMapping("/loginAdmin")
    public ModelAndView loginAdmin() {
        ModelAndView modelAndView=new ModelAndView("auth/login");
        String pageTitle="login";
        modelAndView.addObject("pageTitle",pageTitle);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView=new ModelAndView("auth/loginClient");
        String pageTitle="login";
        modelAndView.addObject("pageTitle",pageTitle);
        return modelAndView;
    }

    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }
    @GetMapping("/registerAdmin")
    public String registerAdmin(){
        return "auth/registerAdmin";
    }

    @PostMapping("/register")
    public ModelAndView registerpost(@RequestParam(value = "admin" , required = false)String admin, @RequestParam String username,@RequestParam String password , @RequestParam("confirmPassword") String confirmPassword) throws Exception {
        UserModel userModel=new UserModel();
        ModelAndView modelAndView= new ModelAndView();
        boolean haveErrors=false;
        try{
            userModel.setUsername(username);
        }catch (Exception exception){
            modelAndView.addObject("username", exception.getMessage());
            haveErrors=true;
        }
        // check if empty
        if(!password.equals(confirmPassword)){
            modelAndView.addObject("password","the given password doesn't match ");
            haveErrors=true;
        }
        else{
            userModel.setPassword(password);
        }

        if(haveErrors && admin==null){
            modelAndView.setViewName("auth/register");
        } else if (haveErrors && admin!=null) {
            modelAndView.setViewName("auth/registerAdmin");
        } else if (!haveErrors && admin!=null) {
            registerService.register(userModel,admin);
            modelAndView.setViewName("redirect:loginAdmin");
        } else {
            registerService.register(userModel,admin);
            modelAndView.setViewName("redirect:login");
        }
        return modelAndView;
    }

    @GetMapping("/accessDenied")
    public String accessdenied(){
        System.out.println("auth : "+ UserService.getAuthenticatedUser());
        return ControllerAdvice.DEFAULT_ACCESS_DENIED_VIEW;
    }
}
