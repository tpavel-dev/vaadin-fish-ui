package kz.kcell.apps.bonus_cmdr.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 03 10 2014
 */
@Entity
//@Table(name="APP_SPMOT_DB.SUBSCRIBE")
@Table(name="SUBSCRIBE")
@Data
@Slf4j
public class Subscribe implements Serializable{

    @Column(name = "MSISDN", columnDefinition = "char")
    @Getter @Setter private String msisdn;

    @Column(name = "BP_ID")
    @Getter @Setter private Long bpId;

    @Column(name = "IVR_CODE")
    @Getter @Setter private int ivrCode;

    @Id @Column(name = "START_DATE")
    @Getter @Setter private Timestamp startDate;

    @Column(name = "END_DATE")
    @Getter @Setter private Timestamp endDate;

    @Column(name = "UPDATE_STATUS")
    @Getter @Setter private Timestamp updateStatus;

    @Column(name = "BONUS")
    @Getter @Setter private Double bonus;
//    @Getter @Setter private BigDecimal bonus;

    @Column(name = "DEALER", columnDefinition = "char")
    @Getter @Setter private String dealer;

    @Column(name = "bdd_code")
    @Getter @Setter private BigDecimal bdd_code;

    @Column(name = "ACCESS_GROUP")
    @Getter @Setter private String accessGroup;

    @Column(name = "BP_NAME")
    @Getter @Setter private String productName;

    @Column(name = "CTGR_BP_NAME")
    @Getter @Setter private String ctgrBpName;

    @Column(name = "CTGR_BP_ID")
    @Getter @Setter private Long ctgrBpId;

    @Column(name = "STATUS") @Enumerated(EnumType.STRING)
    @Getter @Setter private SubscribeStatus status;

    @Column(name = "SUBSTATUS") @Enumerated(EnumType.STRING)
    @Getter @Setter private SubscribeSubstatus substatus;

    @Column(name = "LANG") @Enumerated(EnumType.STRING)
    @Getter @Setter private kz.kcell.apps.common.Language lang;

    @Column(name = "BILLING_TEST_RESULT")
    @Getter @Setter private String billingTestResult;

    @Column(name = "NOTE")
    @Getter @Setter private String note;

    @Column(name = "SHARE_BONUS")
    @Getter @Setter private Long shareBonus;

    @Column(name = "CHANNEL")
    @Getter @Setter private String channel;

/*
    @Getter @Setter
    @Column(name = "MB_AMOUNT")
    private Integer mbAmount;

    @Getter @Setter
    @Column(name = "PRODUCT_COST")
    private Integer productCost;

    @Getter @Setter
    @Column(name = "DURATION")
    private Integer duration;

    @Getter @Setter
    @Column(name = "MB_PER_MONTH")
    private Integer mbPerMonth;
*/

/*
    @Getter @Setter
    @Column(name = "ACTIVATED")
    private Boolean activated = false;
*/


    @Getter @Setter @Transient
    private BusinessProductName businessProductName;

    public void zerroingBonus(String cauth) {
        setBonus(0.0);
        log.info(CommonLogsMarkers.ZEROING_OF_BONUSES.name() + ": Set bonus to zerro. Cause:"+cauth);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscribe subscribe = (Subscribe) o;

        if (!startDate.equals(subscribe.startDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return startDate.hashCode();
    }

    /*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscribe subscribe = (Subscribe) o;

        if (ivrCode != subscribe.ivrCode) return false;
        if (!bpId.equals(subscribe.bpId)) return false;
        if (!msisdn.equals(subscribe.msisdn)) return false;
        if (!startDate.equals(subscribe.startDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = msisdn.hashCode();
        result = 31 * result + bpId.hashCode();
        result = 31 * result + ivrCode;
        result = 31 * result + startDate.hashCode();
        return result;
    }*/

/*
    @Override
    public String toString() {
        return "Subscribe{" +
                " msisdn = '" + msisdn + '\'' +
                ", bpId = " + bpId +
                ", ivrCode = " + ivrCode +
                ", startDate = " + startDate +
                ", bonus = " + bonus +
                ", dealer = '" + dealer + '\'' +
                ", ctgrBpId = " + ctgrBpId +
                ", status = " + status +
                ", bp_name = " + productName +
                ", accessGroup = " + accessGroup +
                ", ctgrBpId = " + ctgrBpId +
                ", ctgrBpName = " + ctgrBpName +
                '}';
    }
*/

    public String dump() {
        return "Subscribe{" +
                " msisdn = '" + msisdn + '\'' +
                ", bpId = " + bpId +
                ", ivrCode = " + ivrCode +
                ", startDate = " + startDate +
                ", bonus = " + bonus +
                ", dealer = '" + dealer + '\'' +
                ", ctgrBpId = " + ctgrBpId +
                ", status = " + status +
                ", bp_name = " + productName +
                ", accessGroup = " + accessGroup +
                ", ctgrBpId = " + ctgrBpId +
                ", ctgrBpName = " + ctgrBpName +
                '}';
    }

}
