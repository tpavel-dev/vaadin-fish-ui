package kz.kcell.apps.common.msisdn;

//import org.jboss.weld.util.collections.ArraySet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 10 2014
 */
public class FastMsisdnTest {
    Set<FastMsisdn> msisdnList;
    @Before
    public void init() {
//        msisdnList = new ArraySet<>(10);
    }

    @Test
    public void testPasingSuccess() {
        assertTestParsing(new FastMsisdn("+7 701 211 02 07"));
        assertTestParsing(new FastMsisdn("8 701 211 02 07"));
        assertTestParsing(new FastMsisdn("701 211 02 07"));
        assertTestParsing(new FastMsisdn("8 (701) 211 02 07"));
        assertTestParsing(new FastMsisdn("87012110207"));
    }

    @Test(expected = IllegalFormatMsisdnException.class)
    public void testPasingFailedLeadNum() {
        new FastMsisdn("67012110207");
    }


    protected void assertTestParsing(FastMsisdn msisdn) {
        Assert.assertEquals("7", msisdn.getCC());
        Assert.assertEquals("701", msisdn.getNDC());
        Assert.assertEquals("2110207", msisdn.getSN());
    }

    @Test
    public void testFormat() {
        Assert.assertEquals( "77012110207"      ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.SOLID         ));
        Assert.assertEquals("+77012110207"      ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.SOLID_PLUS    ));
        Assert.assertEquals( "7"                ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.CC            ));
        Assert.assertEquals(   "701"            ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.NDC           ));
        Assert.assertEquals(       "2110207"    ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.SN            ));
        Assert.assertEquals(       "211 0207"   ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.SN_3          ));
        Assert.assertEquals(       "211 02 07"  ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.SN_35         ));
        Assert.assertEquals( "7 (701) 211 02 07",new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.CANONICAL     ));
        Assert.assertEquals("+7 (701) 211 02 07",new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.CANONICAL_PLUS));
        Assert.assertEquals( "7 (701) 211 0207" ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.NORMAL        ));
        Assert.assertEquals("+7 (701) 211 0207" ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.NORMAL_PLUS   ));
        Assert.assertEquals( "7 701 2110207"    ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.COMPACT       ));
        Assert.assertEquals("+7 701 2110207"    ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.COMPACT_PLUS  ));
        Assert.assertEquals(   "701 2110207"    ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.NDC_SN        ));
        Assert.assertEquals(   "701 211 0207"   ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.NDC_SN_3      ));
        Assert.assertEquals(   "701 211 02 07"  ,new FastMsisdn("+7 701 211 0207").format(Msisdn.Format.NDC_SN_35     ));
    }
}
