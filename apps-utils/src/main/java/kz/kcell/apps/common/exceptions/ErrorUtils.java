package kz.kcell.apps.common.exceptions;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */
public class ErrorUtils {
    public static java.lang.RuntimeException defaultSwitchCaseHandle(Object value) {
        return new IllegalStateException("No implemetation for "+value.getClass().getSimpleName()+"."+value.toString());
    }

    public static java.lang.RuntimeException defaultSwitchCaseHandle(Enum value) {
        return new IllegalStateException("No implemetation for "+value.getClass().getSimpleName()+"."+value.name());
    }

}
