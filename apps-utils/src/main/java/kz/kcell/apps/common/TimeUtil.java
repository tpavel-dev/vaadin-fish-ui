package kz.kcell.apps.common;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 21 11 2014
 */
public class TimeUtil {
    public static Date localToDate(LocalDate localDate) {
        return  Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocal(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static boolean less24hourse(LocalDate from, LocalDate to ) {
        return Math.abs(Period.between(from, to).get(ChronoUnit.DAYS)) < 1;
    }

    public static boolean less24hourse(LocalDateTime from, LocalDateTime to ) {
        return ChronoUnit.HOURS.between(from, to) < 24;
    }

    public static long hoursElapsed(LocalDateTime from) {
        return ChronoUnit.HOURS.between(from, LocalDateTime.now());
    }

    public static long hoursElapsed(LocalDateTime from, LocalDateTime to) {
        return ChronoUnit.HOURS.between(from, to);
    }

    public static long hoursElapsed(Timestamp from) {
        return hoursElapsed(from.toLocalDateTime());
    }

}
