package kz.kcell.apps.bonus_cmdr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
//@Table(name="APP_SPMOT_DB.BUSINESS_PRODUCT")
@Table(name="BUSINESS_PRODUCT")
public class BusinessProductName {
    @Id
    @Getter @Setter private Long id;
    @Column(name="NAME_RU")
    @Getter @Setter private String nameRu;
    @Column(name="NAME_KZ")
    @Getter @Setter private String nameKz;
    @Column(name="NAME_EN")
    @Getter @Setter private String nameEn;

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
