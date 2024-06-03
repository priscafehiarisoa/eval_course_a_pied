package ev.eval_course_a_pied.entity.user;

import ev.eval_course_a_pied.utils.JsonUtility;
import ev.eval_course_a_pied.utils.Menu;
import ev.eval_course_a_pied.utils.Statics;
import ev.eval_course_a_pied.utils.Utils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@ToString
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles",joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleId",referencedColumnName = "id"))
    private List<Role> roles= new ArrayList<>();

    @Transient
    private List<Menu> menuList;

    public UserModel() {

    }

    public UserModel(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
    public void setMenuList() throws Exception {
        String menuPath = Statics.menuFile;
        Role role_global=new Role();
        role_global.setRoleName(Statics.globalName);
        List<Menu> global= JsonUtility.parseJson(menuPath,List.of(role_global));
        setMenuList(global);

        List<Menu> menu = JsonUtility.parseJson(menuPath,this.getRoles());
        // pour obtenir toute les parties globale du menu
        getMenuList().addAll(menu);
    }

    public UserModel( String username, String password) throws Exception {

        setUsername(username);
        setPassword(password);
        setMenuList();
    }

    public void setUsername(String username) {
        this.username =  Utils.formatString(username,"email");
    }

    public void setPassword(String password) {
        this.password =  Utils.formatString(password,"password");
    }



}
