package ev.eval_course_a_pied.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

//    HANDLE DATE TIME

    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("d/M/yyyy")
    );

    private static final List<DateTimeFormatter> TIME_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS"),
            DateTimeFormatter.ofPattern("HH:mm:ss"),
            DateTimeFormatter.ofPattern("H:mm:ss"),
            DateTimeFormatter.ofPattern("HH:mm"),
            DateTimeFormatter.ofPattern("H:mm")
    );

    public static LocalDate parseAnyStringToLocalDate(String dateString) {
        dateString=dateString.trim();
        List<String> patterns = Arrays.asList(
                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "MM/dd/yyyy",
                "yyyy/MM/dd",
                "dd-MM-yyyy",
                "MM-dd-yyyy",
                "yyyy-MM-dd HH:mm:ss",
                "dd/MM/yyyy HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "dd-MM-yyyy HH:mm:ss",
                "MM-dd-yyyy HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss.SSSSSS"

                );

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Ignore and try the next pattern
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate parsedDate = LocalDate.parse(dateString, formatter);
                return parsedDate;
            }
        }

        throw new IllegalArgumentException("Date |"+dateString+"|string could not be parsed with any of the provided patterns.");
    }
    public static LocalDateTime parseAnyStringToLocalDateTime(String dateTimeString) {
        dateTimeString=dateTimeString.trim();
        List<String> patterns = Arrays.asList(
                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "MM/dd/yyyy",
                "yyyy/MM/dd",
                "dd-MM-yyyy",
                "MM-dd-yyyy",
                "d/M/yyyy",
                "yyyy-MM-dd HH:mm:ss",
                "dd/MM/yyyy HH:mm:ss",
                "MM/dd/yyyy HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "dd-MM-yyyy HH:mm:ss",
                "MM-dd-yyyy HH:mm:ss",
                "yyyy-MM-dd HH:mm:ss.SSS",
                "dd/MM/yyyy HH:mm:ss.SSS",
                "MM/dd/yyyy HH:mm:ss.SSS",
                "yyyy/MM/dd HH:mm:ss.SSS",
                "dd-MM-yyyy HH:mm:ss.SSS",
                "MM-dd-yyyy HH:mm:ss.SSS",
                "yyyy-MM-dd HH:mm:ss.SSSSSS",
                "yyyy-MM-dd'T'HH:mm:ss", // ISO 8601 format
                "yyyy-MM-dd'T'HH:mm:ss.SSS", // ISO 8601 format with milliseconds
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ" // ISO 8601 format with milliseconds and timezone
        );

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if(pattern.length()==new String("yyyy-MM-dd").length()){
                    pattern+=" HH:mm:ss";
                    return LocalDateTime.parse(dateTimeString + " 00:00:00", DateTimeFormatter.ofPattern(pattern));
                }
                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                String defaultTime = "00:00:00"; // Default time (midnight)

                // Define the date-time formatter for the given pattern
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm:ss");

                // Parse the string and append the default time
                LocalDateTime parsedDateTime = LocalDateTime.parse(dateTimeString + " " + defaultTime, formatter);
                return parsedDateTime;
            }
        }

        throw new IllegalArgumentException("DateTime string could not be parsed with any of the provided patterns.");
    }
    public static LocalTime convertToTime(String timeString) {
        String pattern = "HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalTime DL;
        try{
           DL= LocalTime.parse(timeString, formatter);
        }catch (Exception e){
            DL= LocalTime.parse(timeString+":00", formatter);
        }
        return DL;
    }
    public static LocalTime addMinuteHour(LocalTime time, int minutes ){
        long totalSeconds = (long) (minutes * 60L);
        LocalTime finalTime = time.plus(totalSeconds, ChronoUnit.SECONDS);
        return finalTime;
    }
    public static int differenceInYears(LocalDate startDate, LocalDate endDate) {
        // Calcul de la différence en années
        Period period = Period.between(startDate, endDate);

        // Récupération de la différence en années
        int years = period.getYears();

        return years;
    }
//    HANDLE VALUES
    public static int stringToInt(String number){
        number=number.trim();
        return Integer.valueOf(number);
    }
    public static double stringToDouble(String number){
        number=number.trim();
        return Double.valueOf(number);
    }
    public static float stringToFloat(String number){
        number=number.trim();
        return Float.valueOf(number);
    }
    public static Long stringToLong(String number){
        number=number.trim();
        return Long.valueOf(number);
    }
    public static boolean checkIfNegative(Object number){

        if (number instanceof Number) {
            double value = ((Number) number).doubleValue();
            return value < 0;
        } else {
            throw new IllegalArgumentException("L'objet n'est pas un nombre.");
        }
    }
// CAST SOMETHING
    public static <T> T castToObject(Object obj, Class<T> clazz) {
        if (clazz.isInstance(obj)) {
            return clazz.cast(obj);
        } else {
            throw new IllegalArgumentException("Object is not an instance of the specified class");
        }
    }

    public static String formatString(String toformat,String fieldName){
        toformat=toformat.trim();
        if(toformat.isEmpty() || toformat.isBlank()){
            throw new IllegalArgumentException("this field : "+fieldName+" cannot be empty");
        }
        return toformat;
    }

//    compress image = tsy de tsara ny rendu
    public static void compressImage(String destinationImagePath,String sourceImagePath) throws IOException {
        File input = new File(sourceImagePath);
        BufferedImage image = ImageIO.read(input);

        File compressedImageFile = new File(destinationImagePath);
        OutputStream os =new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.15f);
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();
    }
    public static boolean verifierSiMajeur(LocalDate dateNaissance){
        if((LocalDate.now().getYear()-dateNaissance.getYear())<18) {
            return false;
        }
        return true;
    }

    public static void compressImage(String destinationImagePath, MultipartFile file) throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);
        BufferedImage image = ImageIO.read(tempFile);

        File compressedImageFile = new File(destinationImagePath);
        OutputStream os =new FileOutputStream(compressedImageFile);

        Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = (ImageWriter) writers.next();

        ImageOutputStream ios = ImageIO.createImageOutputStream(os);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.15f);
        writer.write(null, new IIOImage(image, null, null), param);

        os.close();
        ios.close();
        writer.dispose();
        tempFile.delete();
    }

    public static String getDownloadPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String downloadPath = "";

        if (os.contains("win")) {
            downloadPath = System.getenv("USERPROFILE") + "\\Downloads";
        } else if (os.contains("mac")) {
            downloadPath = System.getProperty("user.home") + "/Downloads";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            downloadPath = System.getProperty("user.home") + "/Downloads";
        }

        return downloadPath;
    }
    public static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    public static String addZeros(String originalString, int numberOfZeros) {
        StringBuilder stringBuilder = new StringBuilder(originalString);
        for (int i = 0; i < numberOfZeros; i++) {
            stringBuilder.append("0");
        }
        return stringBuilder.toString();
    }
    public static LocalDate addDaysToLocalDate(LocalDate date, double numberOfDays) {
        long daysToAdd = (long) numberOfDays;
        return date.plusDays(daysToAdd);
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String format = "\\+(26132|26133|26134|26138|26120)\\d{7}|(032|034|033|038)\\d{7}|02022\\d{5}";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    //    format fields
    public static String formatLocalDate(LocalDate date) {
        String pattern = " MMMM d, yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }
    public static String formatLocalDateTime(LocalDateTime date) {
        String pattern = " MMMM d, yyyy 'at' h:mm a";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }

    public static String formatDouble(double value) {
        String pattern = "#,##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(value);
    }

    private static final Pattern DURATION_PATTERN = Pattern.compile(
            "(\\d+) years? (\\d+) mons? (\\d+) days? (\\d+) hours? (\\d+) mins? ([\\d\\.]+) secs?");

    public static Duration parseDuration(String durationStr) {
        Matcher matcher = DURATION_PATTERN.matcher(durationStr);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid duration format: " + durationStr);
        }

        long hours = Long.parseLong(matcher.group(4));
        long minutes = Long.parseLong(matcher.group(5));
        double seconds = Double.parseDouble(matcher.group(6));

        long totalSeconds = hours * 3600 + minutes * 60 + (long) seconds;
        long nanos = (long) ((seconds - (long) seconds) * 1_000_000_000);

        return Duration.ofSeconds(totalSeconds, nanos);
    }
    public static String formatInt(int value) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        return numberFormat.format(value);
    }
    public static String formatDuration(Duration duration) {
        long days = duration.toDays();
        duration = duration.minusDays(days);

        long hours = duration.toHours();
        duration = duration.minusHours(hours);

        long minutes = duration.toMinutes();
        duration = duration.minusMinutes(minutes);

        long seconds = duration.getSeconds();

        StringBuilder formatted = new StringBuilder();

        if (days > 0) {
            formatted.append(days).append(" days ");
        }
        if (hours > 0 || formatted.length() > 0) {
            formatted.append(hours).append(" hours ");
        }
        if (minutes > 0 || formatted.length() > 0) {
            formatted.append(minutes).append(" mins ");
        }
        if (seconds > 0 || formatted.length() > 0) {
            formatted.append(seconds).append(" secs ");
        }


        // Trim any trailing whitespace
        return formatted.toString().trim();
    }

    public static LocalDateTime concatenateDateTime(String date, String time) {
        LocalDate localDate = null;
        for (DateTimeFormatter dateFormatter : DATE_FORMATTERS) {
            try {
                localDate = LocalDate.parse(date, dateFormatter);
                break; // Exit loop if parsing is successful
            } catch (DateTimeParseException e) {
                // Continue to next format if current format fails
            }
        }
        if (localDate == null) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }
        LocalTime localTime = null;
        for (DateTimeFormatter timeFormatter : TIME_FORMATTERS) {
            try {
                localTime = LocalTime.parse(time, timeFormatter);
                break; // Exit loop if parsing is successful
            } catch (DateTimeParseException e) {
                // Continue to next format if current format fails
            }
        }
        if (localTime == null) {
            throw new IllegalArgumentException("Invalid time format: " + time);
        }
        // Combine date and time to LocalDateTime
        LocalDateTime dateTime = LocalDateTime.of(localDate, localTime);
        return dateTime;
    }
    public static void main(String[] args) throws ParseException {
        System.out.println(Utils.parseAnyStringToLocalDateTime("1/2/2024"));
//        public static void main(String[] args) {
//        String dateString = "1/4/2024";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
//        LocalDate parsedDate = LocalDate.parse(dateString, formatter);
//        System.out.println(parsedDate);
//        }
        String date = "01/06/2024";
        String time = "13:15:00.000000";
        LocalDateTime dateTime = concatenateDateTime(date, time);

        System.out.println(dateTime);
    }
}
