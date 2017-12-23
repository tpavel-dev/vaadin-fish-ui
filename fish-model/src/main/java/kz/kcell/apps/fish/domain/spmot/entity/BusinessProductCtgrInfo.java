package kz.kcell.apps.fish.domain.spmot.entity;

import kz.kcell.apps.common.Language;
import kz.kcell.apps.common.resource.ResourceBundle;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_CTGR_INFO")
public class BusinessProductCtgrInfo implements Cloneable, ResourceBundle {
    @Id
    @Column(name="HUMAN_CODE")
    @Getter @Setter private String humanCode;

    @Column(name="USSD_INFO_RU")
    @Getter @Setter private String ussdInfoRu;

    @Column(name="USSD_INFO_KZ")
    @Getter @Setter private String ussdInfoKz;

    @Column(name="USSD_INFO_EN")
    @Getter @Setter private String ussdInfoEn;

    @Column(name="PRODUCT_CTGR_ID")
    @Getter @Setter private Integer productCtgrId;

    @Override
    public String _ru() {
        return getUssdInfoRu();
    }

    @Override
    public String _kk() {
        return getUssdInfoKz();
    }

    @Override
    public String _en() {
        return getUssdInfoEn();
    }

    @Override
    public void setValue(Language language, String value) {

    }

    @Override
    public String name() {
        return "BusinessProductCtgrUssdInfo";
    }
}
