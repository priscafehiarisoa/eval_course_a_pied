package ev.eval_course_a_pied.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class DoubleParser extends AbstractBeanField<Double , String> {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException {
        return Double.parseDouble(value.replace(",", "."));
    }
}