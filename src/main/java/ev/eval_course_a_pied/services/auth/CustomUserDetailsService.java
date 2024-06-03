package ev.eval_course_a_pied.services.auth;

import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.repository.userRepository.RoleRepository;
import ev.eval_course_a_pied.repository.userRepository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private  UserService userService;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserModelRepository userModelRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserModel userModel= userService.findByUsername(username);
        System.out.println("user details : "+userModel);
               if(userModel==null){
                   System.out.println("eto erreur");
                       throw  new UsernameNotFoundException("impossible de trouver l'utilisateur");
               }

           System.out.println("return");
           return new CustumUserDetails(userModel);
    }
}
