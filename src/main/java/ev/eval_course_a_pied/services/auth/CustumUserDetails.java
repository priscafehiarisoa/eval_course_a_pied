package ev.eval_course_a_pied.services.auth;

import ev.eval_course_a_pied.entity.user.Role;
import ev.eval_course_a_pied.entity.user.UserModel;
import ev.eval_course_a_pied.repository.userRepository.RoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustumUserDetails implements UserDetails {
    public final UserModel userModel;

    public UserModel getUserModel() {
        return userModel;
    }

    public CustumUserDetails(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles= userModel.getRoles();
        List<SimpleGrantedAuthority> authorities= new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(roles.get(i).getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userModel.getPassword();
    }

    @Override
    public String getUsername() {
        return userModel.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
