package kz.kcell.apps.spmot.domain.spmot.entity;

import org.junit.Test;

import static java.lang.System.out;
import static kz.kcell.apps.spmot.domain.spmot.entity.AccessGroupUtils.checkAccess;
import static kz.kcell.apps.spmot.domain.spmot.entity.AccessGroupUtils.parse;

public class AccessGroupTest {
    @Test
    public void test() {

        assert checkAccess("TEST DEBUG ADMIN", "REGULAR TEST") == true;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM") == true;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "AST") == false;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "BONUS_NOT_ALLOWED") == true;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "TRANSFER_BONUS_NOT_ALLOWED") == false;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM, AST") == true;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "NOT, EXIST") == false;
        assert checkAccess("ALM AST", "NOT, EXIST") == false;
    }

    @Test
    public void testPars() {
        parse("ALM TEST BONUS_NOT_ALLOWED").stream().map(t -> "'"+t+"'").forEach(out::println);
    }

    @Test
    public void testParsDoubleSpace() {
        parse("ALM  TEST BONUS_NOT_ALLOWED;AST   DEBUG   ADMIN").stream().map(t -> "'"+t+"'").forEach(out::println);
    }

    @Test
    public void testParsReplaceSpaceSymbol() {
        parse("ALM  ,TEST:BONUS_NOT_ALLOWED;AST\tDEBUG\nADMIN").stream().map(t -> "'"+t+"'").forEach(out::println);
        // todo add assert
    }

}