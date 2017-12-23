package kz.kcell.apps.common;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 14 11 2014
 */
public class Format {
    public static NumberFormat edFormat = NumberFormat.getInstance();
    public static NumberFormat edFractionFormat = NumberFormat.getInstance();
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yy kk:mm:ss");
    public static DateTimeFormatter dayTimeFormat = DateTimeFormatter.ofPattern("dd/MM kk:mm:ss");
    static {
        edFormat.setGroupingUsed(true);
        edFormat.setMaximumFractionDigits(0);
        edFormat.setMinimumFractionDigits(0);

        edFractionFormat.setGroupingUsed(true);
        edFractionFormat.setMaximumFractionDigits(2);
        edFractionFormat.setMinimumFractionDigits(2);
    }
}
