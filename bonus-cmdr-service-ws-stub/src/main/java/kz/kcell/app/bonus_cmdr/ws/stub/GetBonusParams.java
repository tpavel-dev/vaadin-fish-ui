
package kz.kcell.app.bonus_cmdr.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBonusParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBonusParams"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBonusParams", propOrder = {
    "cid"
})
public class GetBonusParams {

    protected Long cid;

    /**
     * Gets the value of the cid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCid() {
        return cid;
    }

    /**
     * Sets the value of the cid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCid(Long value) {
        this.cid = value;
    }

}
