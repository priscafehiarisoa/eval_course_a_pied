package ev.eval_course_a_pied.exeption;

import java.util.HashMap;

public class CoureurEtapeExeption extends Exception{
    HashMap<String,String> error;
    public CoureurEtapeExeption(String message) {
        super(message);
    }

}
