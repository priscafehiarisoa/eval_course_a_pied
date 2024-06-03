package ev.eval_course_a_pied.services.auth;

import ev.eval_course_a_pied.entity.user.Role;
import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.repository.EquipeRepository;
import ev.eval_course_a_pied.repository.userRepository.RoleRepository;
import ev.eval_course_a_pied.repository.userRepository.UserModelRepository;
import ev.eval_course_a_pied.utils.Statics;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserModelRepository userModelRepository;
    private final RoleRepository roleRepository;
    private final EquipeRepository equipeRepository;


    public UserService(UserModelRepository userModelRepository, RoleRepository roleRepository,
                       EquipeRepository equipeRepository) {
        this.userModelRepository = userModelRepository;
        this.roleRepository = roleRepository;
        this.equipeRepository = equipeRepository;
    }


    public UserModel findByUsername(String username) {
        return userModelRepository.findUserModelByUsername(username).orElse(null);
    }




    public static UserModel getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof CustumUserDetails) {
            return ((CustumUserDetails) principal).getUserModel();
        }

        return null;
    }
    public void resetuser(List<UserModel> tobedeleted){
        userModelRepository.deleteAll(tobedeleted);
    }

}
