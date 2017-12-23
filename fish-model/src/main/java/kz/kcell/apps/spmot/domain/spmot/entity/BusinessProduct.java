package kz.kcell.apps.spmot.domain.spmot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "BUSINESS_PRODUCT")
//@Table(name="APP_SPMOT_DB.BUSINESS_PRODUCT")
//@Table(name="APP_SPMOT_DB.PREPROD_BUSINESS_PRODUCT")
public class BusinessProduct implements Comparable<BusinessProduct> {
    @Id
    @Getter
    @Setter
    private Long id;

    @Column(name = "NAME_RU")
    @Getter
    @Setter
    private String nameRu;

    @Column(name = "NAME_KZ")
    @Getter
    @Setter
    private String nameKz;

    @Column(name = "NAME_EN")
    @Getter
    @Setter
    private String nameEn;

    // TODO вытащить в отдельный класс, тяжеловестные поля
    @Column(name = "DESC_RU")
    @Getter
    @Setter
    private String descRu;
    @Column(name = "DESC_KZ")
    @Getter
    @Setter
    private String descKz;
    @Column(name = "DESC_EN")
    @Getter
    @Setter
    private String descEn;

    // TODO вытащить в отдельный класс, тяжеловестные поля
    @Column(name = "USSD_INFO_RU")
    @Getter
    @Setter
    private String ussdInfoRu;
    @Column(name = "USSD_INFO_KZ")
    @Getter
    @Setter
    private String ussdInfoKz;
    @Column(name = "USSD_INFO_EN")
    @Getter
    @Setter
    private String ussdInfoEn;


    @Column(name = "MB_AMOUNT")
    @Getter
    @Setter
    private Integer mbAmount;

    @Column(name = "PRODUCT_COST")
    @Getter
    @Setter
    private Double productCost;
//    @Getter @Setter private BigDecimal productCost;

    @Column(name = "MB_PLUS_COST")
    @Getter
    @Setter
    private Double mbPlusCost;
//    @Getter @Setter private BigDecimal mbPlusCost;

    @Column(name = "BONUS")
    @Getter
    @Setter
    private Double bonus;
//    @Getter @Setter private BigDecimal bonus;


    @Column(name = "DURATION")
    @Getter
    @Setter
    private Integer duration;

    @Column(name = "MB_PER_MONTH")
    @Getter
    @Setter
    private Integer mbPerMonth;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private BusinessProductStatusValue status;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private BusinessProductStateValue state;

    @Column(name = "VIEW_ORDER")
    @Getter
    @Setter
    private Integer viewOrder;

    @Column(name = "CTGR_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private Long ctgrBpId;

    @Column(name = "ACCESS_GROUP")
    @Getter
    @Setter
    private String accessGroup;

    @Column(name = "PRODUCT_GROUP")
    @Getter
    @Setter
    private String productGroup;

    @Column(name = "DETAIL_INFO_RU")
    @Getter
    @Setter
    private String detailInfoRu;

    @Column(name = "DETAIL_INFO_KK")
    @Getter
    @Setter
    private String detailInfoKk;

    @Column(name = "HUMAN_CODE")
    @Getter
    @Setter
    private String humanCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CTGR_ID")
    @JsonBackReference("products")
    /*@Getter @Setter*/
//    @JsonIgnore
    private BusinessProductCtgr category;

    @Override
    public String toString() {
//        return String.format("%s: %s", id.toString(), Objects.firstNonNull(nameRu, nameEn));
        return String.format("%s: %s", id.toString(), nameRu);
    }

    public BusinessProductCtgr getCategory() {
        return category;
    }

    public void setCategory(BusinessProductCtgr category) {
        this.category = category;
    }

    @Override
    public int compareTo(BusinessProduct o) {
        return getId().compareTo(o.getId());
    }


}
