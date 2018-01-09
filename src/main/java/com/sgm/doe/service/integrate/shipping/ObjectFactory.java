
package com.sgm.doe.service.integrate.shipping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sgm.doe.service.integrate.shipping package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetShipmentInfo_QNAME = new QName("http://shipping.integrate.service.doe.sgm.com/", "getShipmentInfo");
    private final static QName _GetShipmentInfoResponse_QNAME = new QName("http://shipping.integrate.service.doe.sgm.com/", "getShipmentInfoResponse");
    private final static QName _UpdateShipmentInfo_QNAME = new QName("http://shipping.integrate.service.doe.sgm.com/", "updateShipmentInfo");
    private final static QName _UpdateShipmentInfoResponse_QNAME = new QName("http://shipping.integrate.service.doe.sgm.com/", "updateShipmentInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sgm.doe.service.integrate.shipping
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetShipmentInfo }
     * 
     */
    public GetShipmentInfo createGetShipmentInfo() {
        return new GetShipmentInfo();
    }

    /**
     * Create an instance of {@link GetShipmentInfoResponse }
     * 
     */
    public GetShipmentInfoResponse createGetShipmentInfoResponse() {
        return new GetShipmentInfoResponse();
    }

    /**
     * Create an instance of {@link UpdateShipmentInfo }
     * 
     */
    public UpdateShipmentInfo createUpdateShipmentInfo() {
        return new UpdateShipmentInfo();
    }

    /**
     * Create an instance of {@link UpdateShipmentInfoResponse }
     * 
     */
    public UpdateShipmentInfoResponse createUpdateShipmentInfoResponse() {
        return new UpdateShipmentInfoResponse();
    }

    /**
     * Create an instance of {@link Shipment }
     * 
     */
    public Shipment createShipment() {
        return new Shipment();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShipmentInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shipping.integrate.service.doe.sgm.com/", name = "getShipmentInfo")
    public JAXBElement<GetShipmentInfo> createGetShipmentInfo(GetShipmentInfo value) {
        return new JAXBElement<GetShipmentInfo>(_GetShipmentInfo_QNAME, GetShipmentInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShipmentInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shipping.integrate.service.doe.sgm.com/", name = "getShipmentInfoResponse")
    public JAXBElement<GetShipmentInfoResponse> createGetShipmentInfoResponse(GetShipmentInfoResponse value) {
        return new JAXBElement<GetShipmentInfoResponse>(_GetShipmentInfoResponse_QNAME, GetShipmentInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateShipmentInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shipping.integrate.service.doe.sgm.com/", name = "updateShipmentInfo")
    public JAXBElement<UpdateShipmentInfo> createUpdateShipmentInfo(UpdateShipmentInfo value) {
        return new JAXBElement<UpdateShipmentInfo>(_UpdateShipmentInfo_QNAME, UpdateShipmentInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateShipmentInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shipping.integrate.service.doe.sgm.com/", name = "updateShipmentInfoResponse")
    public JAXBElement<UpdateShipmentInfoResponse> createUpdateShipmentInfoResponse(UpdateShipmentInfoResponse value) {
        return new JAXBElement<UpdateShipmentInfoResponse>(_UpdateShipmentInfoResponse_QNAME, UpdateShipmentInfoResponse.class, null, value);
    }

}
