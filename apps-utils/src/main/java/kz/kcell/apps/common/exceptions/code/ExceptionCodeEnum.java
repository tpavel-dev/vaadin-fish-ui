package kz.kcell.apps.common.exceptions.code;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public interface ExceptionCodeEnum {
    String name();

    // add all method from enum, but this optionality
    int ordinal();

}
