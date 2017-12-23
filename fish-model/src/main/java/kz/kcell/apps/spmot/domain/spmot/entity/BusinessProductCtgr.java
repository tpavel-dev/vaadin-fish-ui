package kz.kcell.apps.spmot.domain.spmot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="BUSINESS_PRODUCT_CTGR")
//@Table(name="APP_SPMOT_DB.BUSINESS_PRODUCT_CTGR")
public class BusinessProductCtgr implements Cloneable{
    // todo drop
    public static final long UNLIMITED_INTERNET_ID = 1l;
    public static final long MOBILE_INTERNET_ID = 2l;
    public static final long INTERNET_PLUS_ID = 3l;
    public static final long TRAFFIC = 4l;

    @Id
    @Getter @Setter private Long id;

    @Column(name="NAME_RU")
    @Getter @Setter private String nameRu;

    @Column(name="NAME_KZ")
    @Getter @Setter private String nameKz;

    @Column(name="NAME_EN")
    @Getter @Setter private String nameEn;

    @Column(name="PRODUCT_GROUP")
    @Getter @Setter private String productGroup;

    @Column(name="VIEW_ORDER")
    @Getter @Setter private Integer viewOrder;

    @Column(name="STATE") @Enumerated(EnumType.STRING)
    @Getter @Setter private BusinessProductStateValue state;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @JsonManagedReference("products")
    /*@Getter @Setter*/
    private List<BusinessProduct> products;

    @Override
    public String toString() {
//        return String.format("%s: %s", id.toString(), Objects.firstNonNull(nameRu, nameEn));
        return String.format("%s: %s", id.toString(), nameRu);
    }

    public List<BusinessProduct> getProducts() {
        return products;
    }

    public void setProducts(List<BusinessProduct> products) {
        this.products = products;
    }

}
