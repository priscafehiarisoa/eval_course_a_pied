package ev.eval_course_a_pied.repository.userRepository;

import ev.eval_course_a_pied.entity.user.Role;
import ev.eval_course_a_pied.entity.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserModelRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findUserModelByUsername(String username);

    List<UserModel> getUserModelByRolesContains(Role role);


    List<UserModel> getUserModelByRolesNot(Role role);

    Optional<UserModel> getUserModelByRoles(Role role);

   

}