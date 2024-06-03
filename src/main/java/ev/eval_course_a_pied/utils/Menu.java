package ev.eval_course_a_pied.utils;

import lombok.Data;

@Data
public class Menu {
    private String icon;
    private String menuName;
    private String menuPath;

    public Menu(String icon, String menuName,String menuPath) {
        setIcon(icon);
        setMenuName(menuName);
        setMenuPath(menuPath);
    }
}
