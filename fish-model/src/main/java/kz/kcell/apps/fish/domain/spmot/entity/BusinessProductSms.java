package kz.kcell.apps.fish.domain.spmot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 13 11 2014
 */
@Entity
//@Table(name="APP_SPMOT_DB.BP_SMS")
@Table(name="BP_SMS")
public class BusinessProductSms {
    @Getter @Setter @Id                      private Long   id;
    @Getter @Setter @Column(name="SMS_2_RU") private String sms2_ru;
    @Getter @Setter @Column(name="SMS_2_KK") private String sms2_kk;
//    @Getter @Setter /*@Column(name="SMS_2_EN")*/ private String sms2_en;
    @Getter @Setter @Column(name="SMS_3_RU") private String sms3_ru;
    @Getter @Setter @Column(name="SMS_3_KK") private String sms3_kk;
//    @Getter @Setter /*@Column(name="SMS_3_EN")*/ private String sms3_en;
}
