
package kz.kcell.app.bonus_cmdr.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bonusAssigmentState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bonusAssigmentState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allCountSet" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="complatedCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="failedCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="finish" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bonusAssigmentState", propOrder = {
    "allCountSet",
    "complatedCount",
    "failedCount",
    "finish",
    "start"
})
public class BonusAssigmentState {

    protected Long allCountSet;
    protected Long complatedCount;
    protected Long failedCount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar finish;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar start;

    /**
     * Gets the value of the allCountSet property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAllCountSet() {
        return allCountSet;
    }

    /**
     * Sets the value of the allCountSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAllCountSet(Long value) {
        this.allCountSet = value;
    }

    /**
     * Gets the value of the complatedCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getComplatedCount() {
        return complatedCount;
    }

    /**
     * Sets the value of the complatedCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setComplatedCount(Long value) {
        this.complatedCount = value;
    }

    /**
     * Gets the value of the failedCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFailedCount() {
        return failedCount;
    }

    /**
     * Sets the value of the failedCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFailedCount(Long value) {
        this.failedCount = value;
    }

    /**
     * Gets the value of the finish property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFinish() {
        return finish;
    }

    /**
     * Sets the value of the finish property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFinish(XMLGregorianCalendar value) {
        this.finish = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStart(XMLGregorianCalendar value) {
        this.start = value;
    }

}
