
package kz.kcell.app.bonus_cmdr.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addBonus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addBonus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bonus" type="{http://ws_api.bonus_cmdr.app.kcell.kz/}bonusParams" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addBonus", propOrder = {
    "bonus"
})
public class AddBonus {

    protected BonusParams bonus;

    /**
     * Gets the value of the bonus property.
     * 
     * @return
     *     possible object is
     *     {@link BonusParams }
     *     
     */
    public BonusParams getBonus() {
        return bonus;
    }

    /**
     * Sets the value of the bonus property.
     * 
     * @param value
     *     allowed object is
     *     {@link BonusParams }
     *     
     */
    public void setBonus(BonusParams value) {
        this.bonus = value;
    }

}
