import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;

public class DateUtils {
    public static LocalDate localDateFromDate(Date date) {
        return LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
    }

    public static Date dateFromLocalDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }
}
