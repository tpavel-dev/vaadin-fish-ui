package kz.kcell.apps.spmot.domain.spmot.entity;

import org.junit.Test;

import static kz.kcell.apps.spmot.domain.spmot.entity.AccessGroupUtils.checkAccess;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */

class AccessGroupUtilsTest  {

    @Test
    void testCheckAccess() {
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM") == true;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "AST") == false;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "BONUS_NOT_ALLOWED") == true;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "TRANSFER_BONUS_NOT_ALLOWED") == false;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM, AST") == true;
        assert checkAccess("ALM TEST BONUS_NOT_ALLOWED", "NOT, EXIST") == false;
        assert checkAccess("ALM AST", "NOT, EXIST") == false;
        assert checkAccess("TEST DEBUG ADMIN", "REGULAR TEST") == true;
        assert checkAccess("ALM AST", "BONUS_NOT_ALLOWED.name, AccessGroup.BONUS_NOT_ALLOWED") == true;
    }
}
