package kz.kcell.apps.common.exceptions;

import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;

/**
 * function interface
 *
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 30 10 2014
 */
public interface CodeException<CODE extends ExceptionCodeEnum> {
    CODE getCode();
}
