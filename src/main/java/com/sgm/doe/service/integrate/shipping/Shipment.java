
package com.sgm.doe.service.integrate.shipping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Shipment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Shipment"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ASNNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="supplier_id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="supplier_name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="delivery_date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ship_to_id" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ship_to_address" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Shipment", propOrder = {
    "asnNumber",
    "state",
    "supplierId",
    "supplierName",
    "deliveryDate",
    "shipToId",
    "shipToAddress"
})
public class Shipment {

    @XmlElement(name = "ASNNumber", required = true, nillable = true)
    protected String asnNumber;
    @XmlElement(required = true, nillable = true)
    protected String state;
    @XmlElement(name = "supplier_id", required = true, nillable = true)
    protected String supplierId;
    @XmlElement(name = "supplier_name", required = true, nillable = true)
    protected String supplierName;
    @XmlElement(name = "delivery_date", required = true, nillable = true)
    protected String deliveryDate;
    @XmlElement(name = "ship_to_id", required = true, nillable = true)
    protected String shipToId;
    @XmlElement(name = "ship_to_address", required = true, nillable = true)
    protected String shipToAddress;

    /**
     * 获取asnNumber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getASNNumber() {
        return asnNumber;
    }

    /**
     * 设置asnNumber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setASNNumber(String value) {
        this.asnNumber = value;
    }

    /**
     * 获取state属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * 设置state属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * 获取supplierId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * 设置supplierId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierId(String value) {
        this.supplierId = value;
    }

    /**
     * 获取supplierName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * 设置supplierName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierName(String value) {
        this.supplierName = value;
    }

    /**
     * 获取deliveryDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 设置deliveryDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryDate(String value) {
        this.deliveryDate = value;
    }

    /**
     * 获取shipToId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToId() {
        return shipToId;
    }

    /**
     * 设置shipToId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToId(String value) {
        this.shipToId = value;
    }

    /**
     * 获取shipToAddress属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShipToAddress() {
        return shipToAddress;
    }

    /**
     * 设置shipToAddress属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShipToAddress(String value) {
        this.shipToAddress = value;
    }

}
