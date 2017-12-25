package kz.kcell.apps.bonus_cmdr.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Предпологалось загружать строки из базы данных во временя JEE
 * С приходом SPING надобность отпала
 *
 *
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 11 11 2014
 */
@Entity
//@Table(name="APP_SPMOT_DB.STRING_RESOURCES")
@Table(name="STRING_RESOURCES")
@Deprecated
public class StringResources implements Serializable {
    @Id @Column(name = "LANG")
    @Getter @Setter private String lang;

    @Id @Column(name = "KEY")
    @Getter @Setter private String key;

    @Column(name = "VALUE")
    @Getter @Setter private String value;

    public StringResources() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringResources that = (StringResources) o;

        if (lang != null ? !lang.equals(that.lang) : that.lang != null) return false;
        return !(key != null ? !key.equals(that.key) : that.key != null);

    }

    @Override
    public int hashCode() {
        int result = lang != null ? lang.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }
}
