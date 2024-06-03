package ev.eval_course_a_pied.services;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
@Service
public class CsvService {

    public <T> List<T> getData(Class<T> clazz, MultipartFile file) {
        List<T> result = null;

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();

            if (fileName.endsWith(".csv")) {
                try (Reader reader = new InputStreamReader(file.getInputStream());) {
                    result = new CsvToBeanBuilder<T>(reader)
                            .withType(clazz)
                            .build()
                            .parse();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        } else {
            throw new RuntimeException("Données invalide");
        }

        return result;
    }


}
