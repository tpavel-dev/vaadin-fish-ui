
package kz.kcell.app.bonus_cmdr.ws.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bonusStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="bonusStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="STOPED"/&gt;
 *     &lt;enumeration value="RUNNING"/&gt;
 *     &lt;enumeration value="COMPLATED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "bonusStatus")
@XmlEnum
public enum BonusStatus {

    STOPED,
    RUNNING,
    COMPLATED;

    public String value() {
        return name();
    }

    public static BonusStatus fromValue(String v) {
        return valueOf(v);
    }

}
