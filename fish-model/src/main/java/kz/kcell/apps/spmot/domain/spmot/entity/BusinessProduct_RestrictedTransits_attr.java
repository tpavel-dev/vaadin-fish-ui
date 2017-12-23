package kz.kcell.apps.spmot.domain.spmot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by x on 4/20/17.
 */
@Entity
@Table(name = "BUSINESS_PRODUCT")
public class BusinessProduct_RestrictedTransits_attr {
    @Id
    @Getter
    @Setter
    private Long id;


    @Column(name = "RESTRICTED_TRANSITS")
    @Getter
    @Setter
    private String transits;

}
