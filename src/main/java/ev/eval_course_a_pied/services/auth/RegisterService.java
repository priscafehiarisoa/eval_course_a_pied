package ev.eval_course_a_pied.services.auth;

import ev.eval_course_a_pied.entity.user.Role;
import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.repository.userRepository.RoleRepository;
import ev.eval_course_a_pied.repository.userRepository.UserModelRepository;
import ev.eval_course_a_pied.utils.Statics;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserModelRepository userModelRepository;
    private final RoleRepository roleRepository;


    public RegisterService(UserModelRepository userModelRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userModelRepository = userModelRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public UserModel register(UserModel userModel, String admin)  {
        String mainRole;
        if(admin==null) {
            mainRole= Statics.EQUIPEROLE;
        }else{
            mainRole= Statics.ADMINROLE;
        }
        Role role_client;
        if(roleRepository.getRoleByRoleName(mainRole).isEmpty()){
            role_client=new Role(mainRole);
            roleRepository.save(role_client);
        }else{
            role_client=roleRepository.getRoleByRoleName(mainRole).get();
        }
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setRoles(List.of(role_client));
        userModelRepository.save(userModel);
        return userModel;
    }

}
