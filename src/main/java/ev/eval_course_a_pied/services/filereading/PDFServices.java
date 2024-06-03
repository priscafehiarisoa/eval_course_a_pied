package ev.eval_course_a_pied.services.filereading;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.List;

public class PDFServices {
    public static <T> void exportToPDF(List<T> dataList, String filePath) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(filePath);
        T firstObject = dataList.get(0);
        Class<?> clazz = firstObject.getClass();
        Field[] fields = clazz.getDeclaredFields();

        // gerer la taille du pdf
        float totalWidth = 550f;

        // Nombre de colonnes dans le tableau
        int numberOfColumns = fields.length;

        float columnWidth = totalWidth / numberOfColumns;
        float[] pointColumnWidths1 = new float[numberOfColumns];
        for (int i = 0; i < numberOfColumns; i++) {
            pointColumnWidths1[i] = columnWidth;
        }

        // Creating a PdfDocument object
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        Table table = new Table(pointColumnWidths1);

        // add the fields
        for (int i = 0; i < fields.length; i++) {
            table.addCell(new Paragraph(fields[i].getName().replace("_"," ")));
        }
        // add the other details
        for (T data : dataList) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    table.addCell(new Paragraph(String.valueOf(field.get(data))));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        doc.add(table);
        doc.close();
    }

}
