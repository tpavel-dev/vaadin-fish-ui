package kz.kcell.apps.spmot.domain.spmot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 15 09 2014
 */
@Entity
@Table(name="WEBSFA.VIEW_VALID_MOBILE_NUMBERS")
public class AvailableMsisdn {
    @Id @Getter @Setter
    @Column(name="mob_num")
    private String msisdn;
}
