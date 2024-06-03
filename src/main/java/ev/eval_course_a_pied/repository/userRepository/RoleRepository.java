package ev.eval_course_a_pied.repository.userRepository;

import ev.eval_course_a_pied.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> getRoleByRoleName(String rolename);

    List<Role> getRolesByRoleName(String rolename);
}