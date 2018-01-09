package com.sgm.doe.service.integrate.shipping;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2017-12-20T09:55:46.114+08:00
 * Generated source version: 3.1.2
 * 
 */
@WebServiceClient(name = "ShipmentServiceImplService", 
                  wsdlLocation = "http://10.203.103.118:7003/PPOE/services/public/ShipmentService?wsdl",
                  targetNamespace = "http://shipping.integrate.service.doe.sgm.com/") 
public class ShipmentServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://shipping.integrate.service.doe.sgm.com/", "ShipmentServiceImplService");
    public final static QName ShipmentServiceImplPort = new QName("http://shipping.integrate.service.doe.sgm.com/", "ShipmentServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.203.103.118:7003/PPOE/services/public/ShipmentService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ShipmentServiceImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.203.103.118:7003/PPOE/services/public/ShipmentService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ShipmentServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ShipmentServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShipmentServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ShipmentServiceImplService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ShipmentServiceImplService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ShipmentServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ShipmentService
     */
    @WebEndpoint(name = "ShipmentServiceImplPort")
    public ShipmentService getShipmentServiceImplPort() {
        return super.getPort(ShipmentServiceImplPort, ShipmentService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShipmentService
     */
    @WebEndpoint(name = "ShipmentServiceImplPort")
    public ShipmentService getShipmentServiceImplPort(WebServiceFeature... features) {
        return super.getPort(ShipmentServiceImplPort, ShipmentService.class, features);
    }

}