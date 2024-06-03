package ev.eval_course_a_pied.services.filereading;


import ev.eval_course_a_pied.utils.ImportErrorLogs;
import ev.eval_course_a_pied.utils.Statics;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ExelReadingService {
     List<ImportErrorLogs> logErrors=new ArrayList<>();
//    List<TestTable> testTables=new ArrayList<>();



    private Object getCellValue(Cell cell) {
        switch(cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
        }
        return null;
    }
//    public List<TestTable> readExel(String exelPath) throws Exception {
//        logErrors=new ArrayList<>();
//        List<TestTable> readedFromExel=new ArrayList<>();
//        FileInputStream inputStream = new FileInputStream(new File(exelPath));
//        Workbook workbook = new XSSFWorkbook(inputStream);
//        Sheet firstSheet = workbook.getSheetAt(0);
//        Iterator<Row> iterator = firstSheet.iterator();
//
//        //            creer les variables pour la table de test
//                    /*** eto no ovaina raha miova ny données avy any anaty exel*/
//        int numSeance=0 ;
//        String  film="";
//        LocalDate date = null;
//        LocalTime heure = null;
//        String salle="", categorie="";
//
//        while (iterator.hasNext()) {
//            Row nextRow = iterator.next();
//            if (nextRow.getRowNum() == 0) {
//                continue; // Passe à la prochaine itération (ignore la première ligne)
//            }
//            int rowIndex = nextRow.getRowNum();
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//            while (cellIterator.hasNext()) {
//                Cell nextCell = cellIterator.next();
//                int columnIndex = nextCell.getColumnIndex();
//                if(getCellValue(nextCell)==null){
//                    break;
//                }
//                switch (columnIndex) {
//                    // chaque chiffre correspond à une colonne du tableau
//                    case 0:
//                        numSeance= (int) nextCell.getNumericCellValue();
//                        break;
//
//                    case 1:
//                        film= nextCell.getStringCellValue();
//                        break;
//
//                    case 2:
//                        categorie=nextCell.getStringCellValue();
//                        break;
//
//                    case 3:
//                        salle=nextCell.getStringCellValue();
//                        break;
//
//                    case 4:
//                        date= LocalDate.from(nextCell.getLocalDateTimeCellValue());
//                        break;
//
//                    case 5:
//                        heure= nextCell.getLocalDateTimeCellValue().toLocalTime();
//                        break;
//                }
//
//            }
//            try {
//                TestTable testTable = new TestTable(numSeance, film, categorie, salle, date, heure);
//                readedFromExel.add(testTable);
//            } catch (Exception e){
//                this.logErrors.add(new ImportErrorLogs(rowIndex,e.getMessage(), rowIndex));
//            }
//
//        }
//        workbook.close();
//        inputStream.close();
//        return readedFromExel;
//    }
//    public List<TestTable> readFile(String path,String delimiter) throws FileNotFoundException {
//        this.logErrors=new ArrayList<>();
//        Scanner scanner =new Scanner(new File(path));
//        scanner.useDelimiter(delimiter);
//
//        //object to return
//        List<TestTable> testTables1= new ArrayList<>();
////        -------------
//        int i =0;
//        // Skip the first line
//        if (scanner.hasNextLine()) {
//            scanner.nextLine();
//            i++;
//        }
//        while (scanner.hasNextLine()){
//            i++;
//            String [] line = scanner.nextLine().split(delimiter);
//            try {
//                TestTable testTable = new TestTable(line[0], line[1], line[2], line[3], line[4], line[5]);
//                testTables1.add(testTable);
//            }
//            catch (Exception e){
//                this.logErrors.add(new ImportErrorLogs(i,e.getMessage(),0));
//            }
//        }
//        scanner.close();
//        return testTables1;
//    }
    public void exportToExcel(List<?> objects, String filePath) throws IOException {
        Workbook workbook = WorkbookFactory.create(true); // Create a new workbook

        Sheet sheet = workbook.createSheet("Sheet1"); // Create a new sheet

        if (objects.isEmpty()) {
            // If the list is empty, just save the workbook and return
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            return;
        }

        Object firstObject = objects.get(0);
        Class<?> clazz = firstObject.getClass();

        Field[] fields = clazz.getDeclaredFields();

        // Create header row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName());
        }

        // Fill data rows
        for (int rowIndex = 0; rowIndex < objects.size(); rowIndex++) {
            Object obj = objects.get(rowIndex);
            Row row = sheet.createRow(rowIndex + 1); // Start from the second row

            for (int colIndex = 0; colIndex < fields.length; colIndex++) {
                Field field = fields[colIndex];
                field.setAccessible(true);

                try {
                    Object value = field.get(obj);
                    Cell cell = row.createCell(colIndex);
                    if (value != null) {
                        if (value instanceof String) {
                            cell.setCellValue((String) value);
                        } else if (value instanceof Number) {
                            cell.setCellValue(((Number) value).doubleValue());
                        } else if (value instanceof Boolean) {
                            cell.setCellValue((Boolean) value);
                        } else if (value instanceof Character) {
                            cell.setCellValue(String.valueOf((Character) value));
                        } else if (value instanceof Date) {
                            cell.setCellValue((Date) value);
                            CellStyle cellStyle = workbook.createCellStyle();
                            CreationHelper createHelper = workbook.getCreationHelper();
                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
                            cell.setCellStyle(cellStyle);
                        } else if (value instanceof java.sql.Date) {
                            cell.setCellValue((java.sql.Date) value);
                            CellStyle cellStyle = workbook.createCellStyle();
                            CreationHelper createHelper = workbook.getCreationHelper();
                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
                            cell.setCellStyle(cellStyle);
                        } else if (value instanceof LocalDateTime) {
                            cell.setCellValue((LocalDateTime) value);
                            CellStyle cellStyle = workbook.createCellStyle();
                            CreationHelper createHelper = workbook.getCreationHelper();
                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
                            cell.setCellStyle(cellStyle);
                        }
                        else if (value instanceof LocalDate) {
                            cell.setCellValue((LocalDate) value);
                            CellStyle cellStyle = workbook.createCellStyle();
                            CreationHelper createHelper = workbook.getCreationHelper();
                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
                            cell.setCellStyle(cellStyle);
                        }
//                        else if (value instanceof LocalTime) {
//                            cell.setCellValue((LocalDateTime) value);
//                            CellStyle cellStyle = workbook.createCellStyle();
//                            CreationHelper createHelper = workbook.getCreationHelper();
//                            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("hh:mm:ss"));
//                            cell.setCellStyle(cellStyle);
//                        }
                        else {
                            cell.setCellValue(value.toString()); // Convert other types to strings
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // Auto-size columns
        for (int i = 0; i < fields.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Close the workbook
        workbook.close();
    }
    public String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return "";
    }
    public boolean checkExtension(String extension)
    {
        boolean response= false;
        for (int i = 0; i < Statics.IMAGE_TYPE.size(); i++) {
            if(Statics.IMAGE_TYPE.get(i).trim().equals(extension.trim())){
                return true;
            }
        }
        return response;
    }
    public  <T> void exportToCSV(List<T> dataList, String filePath) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);

        // Write header row
        T firstObject = dataList.get(0);
        Class<?> clazz = firstObject.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            csvWriter.append(field.getName()).append(",");
        }
        csvWriter.append("\n");

        // Write data rows
        for (T data : dataList) {
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    csvWriter.append(String.valueOf(field.get(data))).append(",");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            csvWriter.append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }


    public <T> void exportToPDF(List<T> dataList, String filePath) throws IOException,  URISyntaxException {
        PDFServices.exportToPDF(dataList,filePath);
    }


/*    todo : 1- lire le fichier
                2- enregistrer le fichier dans la table TestTable
                3- traitement des donnees : insert from selects
                4- gerer les exeptions : raha misy erreurs ao @ le logs de tonga de mamoaka erreurs
 */




    public static void main(String[] args) throws Exception {
        String excelFilePath = "/Users/priscafehiarisoadama/Downloads/donnees-import.xlsx";
        String excelFilePathcsv = "/Users/priscafehiarisoadama/me/s6/eval/donnees-import.csv";
        String exportedExel = "/Users/priscafehiarisoadama/me/s6/eval/exemple.xlsx";
        String source="/Users/priscafehiarisoadama/me/s6/eval/";

//        ExelReadingService exelReadingService = new ExelReadingService();
//        List<TestTable> testTables1=exelReadingService.readExel(excelFilePath);
////        System.out.println("test");
////        testTables1.forEach(System.out::println);
////        System.out.println("logs");
//
//        exelReadingService.logErrors.forEach(System.out::println);
//
//        exelReadingService.readFile(excelFilePathcsv,",").forEach(System.out::println);
//
//        exelReadingService.logErrors.forEach(System.out::println);
///*
//        ExelReadingService.exportToExcel(testTables1,exportedExel);
//        ExelReadingService.exportToCSV(testTables1,source+"expCSV.java");
//        */
//
//        exelReadingService.


    }

}
