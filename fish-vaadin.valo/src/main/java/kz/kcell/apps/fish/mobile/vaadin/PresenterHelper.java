package kz.kcell.apps.fish.mobile.vaadin;

import kz.kcell.apps.common.Format;
import kz.kcell.apps.fish.domain.spmot.entity.BusinessProduct;
import kz.kcell.apps.fish.mobile.vaadin.controller.SessionManager;
import lombok.extern.slf4j.Slf4j;

import static kz.kcell.apps.fish.domain.spmot.entity.SpmotResourceBundle.*;
import static kz.kcell.apps.fish.mobile.vaadin.SpmotMobileResourceManager.$;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 07 11 2014
 */
@Slf4j
public class PresenterHelper {
    public static String buildLongProductDescr(BusinessProduct bp) {
        String name = bp.getNameRu() ;
        switch (SessionManager.getAccount().getLang()) {
            case RU: name = bp.getNameRu() ; break;
            case KK: name = bp.getNameKz() ; break;
        }
        String add_info = "";
        try {
            if(bp.getDuration() != null) {
                if(bp.getProductCost() != null) {
                    if (bp.getDuration() == 1) {
                        add_info = $(helper_duration_day, Format.edFormat.format(bp.getProductCost()));
                    } else if (bp.getDuration() == 30) {
                        add_info = $(helper_duration_month,Format.edFormat.format(bp.getProductCost()));
                    } else {
//                : "<br /> "+bp.getProductCost()+" ед. на "+ bp.getDuration()+" дней";
                        add_info = $(helper_duration,Format.edFormat.format(bp.getProductCost()));
                    }
                }
            }
        }catch (Exception exc) {
            log.error(exc.getLocalizedMessage(), exc);
        }
        return name + add_info;
    };

    @Deprecated
    private String buildShortName(BusinessProduct q) {
        String name = "";
        float amount = q.getMbAmount();
        String unit = " Mb";
        if(amount >= 1024) {
            amount = amount / 1024;
            unit = " Gb";
        }

        int iamount = (int) amount;
        String samount = amount - iamount == 0 ? Integer.toString(iamount): Float.toString(amount);

        if(q.getCtgrBpId() == 1) {
            name = samount + " "+unit+" на " + q.getDuration() + " дней. ";
        } else {
            name =  samount + " "+unit;
        }
        return name;
    }

}
