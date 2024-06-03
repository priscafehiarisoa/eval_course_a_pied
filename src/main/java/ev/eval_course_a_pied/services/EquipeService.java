package ev.eval_course_a_pied.services;

import ev.eval_course_a_pied.entity.Equipe;
import ev.eval_course_a_pied.entity.Etape;
import ev.eval_course_a_pied.entity.user.Role;
import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.repository.EquipeRepository;
import ev.eval_course_a_pied.repository.userRepository.RoleRepository;
import ev.eval_course_a_pied.services.auth.RegisterService;
import ev.eval_course_a_pied.services.auth.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EquipeService {

    private final RoleRepository roleRepository;
    private final UserService userService;
    private final EquipeRepository equipeRepository;
    private final RegisterService registerService;

    public EquipeService(RoleRepository roleRepository, UserService userService,
                         EquipeRepository equipeRepository, RegisterService registerService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.equipeRepository = equipeRepository;
        this.registerService = registerService;
    }

    public Role getEquipeRole(){
        return roleRepository.getRoleByRoleName("EQUIPE").orElse(roleRepository.save(new Role("EQUIPE")));
    }

    public Equipe getConnectedEquipe(){
        UserModel userModel= UserService.getAuthenticatedUser();
        Optional<Equipe> equipe= equipeRepository.getEquipeByUserModel(userModel);
        return equipe.orElse(null);
    }
    public Equipe saveEquipe(String nomEtape) throws Exception {
        UserModel userModel = new UserModel(nomEtape,nomEtape);
        registerService.register(userModel,null);
        Equipe equipe = new Equipe(nomEtape,userModel);
        equipeRepository.save(equipe);
        return equipe;
    }


}
