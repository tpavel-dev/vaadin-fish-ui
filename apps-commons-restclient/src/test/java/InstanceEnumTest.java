import kz.kcell.apps.common.exceptions.CommonSystemExceptionCode;
import kz.kcell.apps.common.exceptions.code.ExceptionCodeEnum;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 31 10 2014
 */
public class InstanceEnumTest {
    @Test
    @SuppressWarnings("unchecked")
    public void test() throws ClassNotFoundException {
        CommonSystemExceptionCode code = CommonSystemExceptionCode.Ox0000;
        String className = code.getClass().getName();
        String valueName = code.name();
        System.out.println(className);
        Class clazz = Class.forName(className);
        System.out.println(clazz.getName());
        System.out.println("is enum: "+clazz.isEnum());
        ExceptionCodeEnum[] enumConstants = (ExceptionCodeEnum[]) clazz.getEnumConstants();
        Arrays.asList(enumConstants).forEach(a -> System.out.println(a.name()));
        System.out.println(Enum.valueOf(clazz, valueName));
    }
}
