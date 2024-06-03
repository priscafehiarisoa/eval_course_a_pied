package ev.eval_course_a_pied.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class Configuration {
    @Getter
    @Setter
    String jsonPath;

    public <T> T read() throws Exception{
        // get current location
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String separator = File.separator;
        String path = classLoader.getName()+"/conf" + separator + jsonPath;
        Object temp = JsonUtility.parseJson(path, this.getClass());
        return (T)temp;
    }
}
