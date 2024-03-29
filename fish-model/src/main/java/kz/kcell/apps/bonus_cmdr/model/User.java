package kz.kcell.apps.bonus_cmdr.model;

import kz.kcell.apps.common.Language;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 11 11 2014
 */
@Entity
//@Table(name="APP_SPMOT_DB.DEALER")
@Table(name="DEALER")
public class User implements Serializable {
    @Id @Column(name = "MSISDN", columnDefinition = "char")
    @Getter @Setter private String msisdn;

    @Id @Column(name = "ACCESS_GROUP")
    @Getter @Setter private String access_group;

    @Id @Column(name = "bdd_code")
    @Getter @Setter private BigDecimal bdd_code;

    @Transient
    @Getter @Setter
    private Language language;

    public User() {
    }

    public User(String msisdn) {
        this.msisdn = msisdn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (msisdn != null ? !msisdn.equals(user.msisdn) : user.msisdn != null) return false;
        if (access_group != null ? !access_group.equals(user.access_group) : user.access_group != null)
            return false;
        return !(bdd_code != null ? !bdd_code.equals(user.bdd_code) : user.bdd_code != null);

    }

    @Override
    public int hashCode() {
        int result = msisdn != null ? msisdn.hashCode() : 0;
        result = 31 * result + (access_group != null ? access_group.hashCode() : 0);
        result = 31 * result + (bdd_code != null ? bdd_code.hashCode() : 0);
        return result;
    }
}
