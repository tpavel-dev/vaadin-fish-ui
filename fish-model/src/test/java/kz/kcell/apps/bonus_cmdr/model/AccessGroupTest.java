package kz.kcell.apps.bonus_cmdr.model;

import org.junit.Test;

import static java.lang.System.out;
import static kz.kcell.apps.bonus_cmdr.model.AccessGroupUtils.checkAccess;

public class AccessGroupTest {
    @Test
    public void test() {

        assert AccessGroupUtils.checkAccess("TEST DEBUG ADMIN", "REGULAR TEST") == true;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM") == true;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "AST") == false;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "BONUS_NOT_ALLOWED") == true;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "TRANSFER_BONUS_NOT_ALLOWED") == false;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM, AST") == true;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "NOT, EXIST") == false;
        assert AccessGroupUtils.checkAccess("ALM AST", "NOT, EXIST") == false;
    }

    @Test
    public void testPars() {
        AccessGroupUtils.parse("ALM TEST BONUS_NOT_ALLOWED").stream().map(t -> "'"+t+"'").forEach(out::println);
    }

    @Test
    public void testParsDoubleSpace() {
        AccessGroupUtils.parse("ALM  TEST BONUS_NOT_ALLOWED;AST   DEBUG   ADMIN").stream().map(t -> "'"+t+"'").forEach(out::println);
    }

    @Test
    public void testParsReplaceSpaceSymbol() {
        AccessGroupUtils.parse("ALM  ,TEST:BONUS_NOT_ALLOWED;AST\tDEBUG\nADMIN").stream().map(t -> "'"+t+"'").forEach(out::println);
        // todo add assert
    }

}