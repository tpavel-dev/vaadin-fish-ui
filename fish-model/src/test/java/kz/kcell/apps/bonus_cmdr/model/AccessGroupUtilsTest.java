package kz.kcell.apps.bonus_cmdr.model;

import org.junit.Test;

import static kz.kcell.apps.bonus_cmdr.model.AccessGroupUtils.checkAccess;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 06 04 2016
 */

class AccessGroupUtilsTest  {

    @Test
    void testCheckAccess() {
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM") == true;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "AST") == false;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "BONUS_NOT_ALLOWED") == true;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "TRANSFER_BONUS_NOT_ALLOWED") == false;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "ALM, AST") == true;
        assert AccessGroupUtils.checkAccess("ALM TEST BONUS_NOT_ALLOWED", "NOT, EXIST") == false;
        assert AccessGroupUtils.checkAccess("ALM AST", "NOT, EXIST") == false;
        assert AccessGroupUtils.checkAccess("TEST DEBUG ADMIN", "REGULAR TEST") == true;
        assert AccessGroupUtils.checkAccess("ALM AST", "BONUS_NOT_ALLOWED.name, AccessGroup.BONUS_NOT_ALLOWED") == true;
    }
}
