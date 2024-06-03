package ev.eval_course_a_pied.security.exeption;

import ev.eval_course_a_pied.services.auth.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        System.out.println("auth : "+UserService.getAuthenticatedUser());
        response.sendRedirect(request.getContextPath() + "/access-denied");
    }
}
