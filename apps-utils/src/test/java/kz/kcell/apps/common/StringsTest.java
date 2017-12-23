package kz.kcell.apps.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
public class StringsTest {

    @Test
    public void onlyNumbers() {
        Assert.assertEquals("77012110207", Strings.onlyNumbers("  +7 701 211 02 07"));
    }
}
