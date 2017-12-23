package kz.kcell.apps.spmot.mobile.vaadin.data;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Pavel.Terechshenkov@kcell.kz
 * @since 13 10 2014
 */
public class Row implements Serializable {

    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private Date date;

    @Getter @Setter
    private String serviceName;

    @Getter @Setter
    private Integer bonus;

    public Row(int id, Date date, String name, Integer bonus) {
        this.id = id;
        this.date = date;
        this.serviceName = name;
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;


        if (id != null ? !id.equals(row.id) : row.id != null) return false;
        if (bonus != null ? !bonus.equals(row.bonus) : row.bonus != null) return false;
        if (date != null ? !date.equals(row.date) : row.date != null) return false;
        if (serviceName != null ? !serviceName.equals(row.serviceName) : row.serviceName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (bonus != null ? bonus.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}