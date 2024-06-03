package ev.eval_course_a_pied.utils;

import lombok.Data;

@Data
public class ImportErrorLogs {
    Integer line;
    String error_description;
    Integer column_number;

    public ImportErrorLogs(Integer line, String error_description, Integer column_number) {
        setLine(line);
        setError_description(error_description);
        setColumn_number(column_number);
    }
}
