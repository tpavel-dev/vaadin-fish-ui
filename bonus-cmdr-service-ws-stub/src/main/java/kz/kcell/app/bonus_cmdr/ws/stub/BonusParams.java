
package kz.kcell.app.bonus_cmdr.ws.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for bonusParams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bonusParams"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allowanceDescr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="allowanceEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="allowanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="allowanceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="allowanceQuota" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="allowanceStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="bid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="cid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="exeOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="orgaAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="orgaBalanceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgaComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgaExpDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="orgaPocketName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="orgaStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://ws_api.bonus_cmdr.app.kcell.kz/}bonusStatus" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bonusParams", propOrder = {
    "allowanceDescr",
    "allowanceEndDate",
    "allowanceId",
    "allowanceName",
    "allowanceQuota",
    "allowanceStartDate",
    "bid",
    "cid",
    "exeOrder",
    "orgaAmount",
    "orgaBalanceName",
    "orgaComment",
    "orgaExpDate",
    "orgaPocketName",
    "orgaStartDate",
    "status"
})
public class BonusParams {

    protected String allowanceDescr;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar allowanceEndDate;
    protected String allowanceId;
    protected String allowanceName;
    protected Double allowanceQuota;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar allowanceStartDate;
    protected Long bid;
    protected Long cid;
    protected Integer exeOrder;
    protected Double orgaAmount;
    protected String orgaBalanceName;
    protected String orgaComment;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orgaExpDate;
    protected String orgaPocketName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orgaStartDate;
    @XmlSchemaType(name = "string")
    protected BonusStatus status;

    /**
     * Gets the value of the allowanceDescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceDescr() {
        return allowanceDescr;
    }

    /**
     * Sets the value of the allowanceDescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceDescr(String value) {
        this.allowanceDescr = value;
    }

    /**
     * Gets the value of the allowanceEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAllowanceEndDate() {
        return allowanceEndDate;
    }

    /**
     * Sets the value of the allowanceEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAllowanceEndDate(XMLGregorianCalendar value) {
        this.allowanceEndDate = value;
    }

    /**
     * Gets the value of the allowanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceId() {
        return allowanceId;
    }

    /**
     * Sets the value of the allowanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceId(String value) {
        this.allowanceId = value;
    }

    /**
     * Gets the value of the allowanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowanceName() {
        return allowanceName;
    }

    /**
     * Sets the value of the allowanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowanceName(String value) {
        this.allowanceName = value;
    }

    /**
     * Gets the value of the allowanceQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAllowanceQuota() {
        return allowanceQuota;
    }

    /**
     * Sets the value of the allowanceQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAllowanceQuota(Double value) {
        this.allowanceQuota = value;
    }

    /**
     * Gets the value of the allowanceStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAllowanceStartDate() {
        return allowanceStartDate;
    }

    /**
     * Sets the value of the allowanceStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAllowanceStartDate(XMLGregorianCalendar value) {
        this.allowanceStartDate = value;
    }

    /**
     * Gets the value of the bid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBid() {
        return bid;
    }

    /**
     * Sets the value of the bid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBid(Long value) {
        this.bid = value;
    }

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

    /**
     * Gets the value of the exeOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExeOrder() {
        return exeOrder;
    }

    /**
     * Sets the value of the exeOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExeOrder(Integer value) {
        this.exeOrder = value;
    }

    /**
     * Gets the value of the orgaAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOrgaAmount() {
        return orgaAmount;
    }

    /**
     * Sets the value of the orgaAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOrgaAmount(Double value) {
        this.orgaAmount = value;
    }

    /**
     * Gets the value of the orgaBalanceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgaBalanceName() {
        return orgaBalanceName;
    }

    /**
     * Sets the value of the orgaBalanceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgaBalanceName(String value) {
        this.orgaBalanceName = value;
    }

    /**
     * Gets the value of the orgaComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgaComment() {
        return orgaComment;
    }

    /**
     * Sets the value of the orgaComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgaComment(String value) {
        this.orgaComment = value;
    }

    /**
     * Gets the value of the orgaExpDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrgaExpDate() {
        return orgaExpDate;
    }

    /**
     * Sets the value of the orgaExpDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrgaExpDate(XMLGregorianCalendar value) {
        this.orgaExpDate = value;
    }

    /**
     * Gets the value of the orgaPocketName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgaPocketName() {
        return orgaPocketName;
    }

    /**
     * Sets the value of the orgaPocketName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgaPocketName(String value) {
        this.orgaPocketName = value;
    }

    /**
     * Gets the value of the orgaStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrgaStartDate() {
        return orgaStartDate;
    }

    /**
     * Sets the value of the orgaStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrgaStartDate(XMLGregorianCalendar value) {
        this.orgaStartDate = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link BonusStatus }
     *     
     */
    public BonusStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link BonusStatus }
     *     
     */
    public void setStatus(BonusStatus value) {
        this.status = value;
    }

}
