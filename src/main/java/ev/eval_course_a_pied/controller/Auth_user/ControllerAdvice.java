package ev.eval_course_a_pied.controller.Auth_user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@org.springframework.web.bind.annotation.ControllerAdvice()
public class ControllerAdvice {
    public static final String DEFAULT_NOT_FOUND_VIEW ="1-basic_setup/error";
    public static final String DEFAULT_ACCESS_DENIED_VIEW ="1-basic_setup/access_error";
//
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ModelAndView noRessourceFound(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_NOT_FOUND_VIEW);
        return mav;
    }


//    @ExceptionHandler(value = AccessDeniedException.class)
//    public ModelAndView handleAccessDenied(Exception ex) {
//        ModelAndView modelAndView =new ModelAndView();
//        if(ex instanceof AccessDeniedException) {
//             modelAndView.setViewName(DEFAULT_ACCESS_DENIED_VIEW);
//        }
//        return modelAndView ;
//    }


//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView exeptionHandler(Exception ex)
//    {
//
//    }
}
