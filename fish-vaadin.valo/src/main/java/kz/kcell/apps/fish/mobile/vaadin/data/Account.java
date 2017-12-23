package kz.kcell.apps.fish.mobile.vaadin.data;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.msisdn.Msisdn;
import kz.kcell.apps.fish.domain.spmot.entity.Dealer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 24 09 2014
 */

public class Account  {

    @Getter @Setter
    private Msisdn msisdn;

    @Getter @Setter
    private Double bonus;

    @Getter @Setter
    private boolean authorized = false;

    @Getter
    private boolean captchaBlocked = false;

    @Getter @Setter
    private Language lang = Language.RU;
//    private Language lang = Language.KZ;

    @Getter @Setter
    private Dealer dealer;

    public String getStateInfo() {
        return "isAuthorized: "+authorized+" ,isCaptchaBlocked"+ captchaBlocked;
    }

    public void blockCaptcha() {
        captchaBlocked = true;
    }

    public void unblockCaptcha() {
        captchaBlocked = false;
    }


}
