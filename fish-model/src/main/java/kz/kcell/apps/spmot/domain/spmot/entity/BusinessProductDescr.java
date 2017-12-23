package kz.kcell.apps.spmot.domain.spmot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
//@Table(name="APP_SPMOT_DB.BUSINESS_PRODUCT")
@Table(name="BUSINESS_PRODUCT")
public class BusinessProductDescr {
    @Id
    @Getter @Setter private Long id;
    @Column(name="NAME_RU")
    @Getter @Setter private String nameRu;
    @Column(name="NAME_KZ")
    @Getter @Setter private String nameKz;
    @Column(name="NAME_EN")
    @Getter @Setter private String nameEn;

    // TODO вытащить в отдельный класс, тяжеловестные поля
    @Column(name="DESC_RU")
    @Getter @Setter private String descRu;
    @Column(name="DESC_KZ")
    @Getter @Setter private String descKz;
    @Column(name="DESC_EN")
    @Getter @Setter private String descEn;

    @Column(name="STATUS")
    @Enumerated(EnumType.STRING)
    @Getter @Setter private BusinessProductStatusValue status;

    @Column(name="STATE")
    @Enumerated(EnumType.STRING)
    @Getter @Setter private BusinessProductStateValue state;

    @Column(name="BONUS")
    @Getter @Setter private Integer bonus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CTGR_ID")
    @JsonBackReference("products")
    /*@Getter @Setter*/
    @JsonIgnore
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
}
