package com.sgm.doe.service.integrate.shipping;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import com.common.WSbean;
import com.common.WebServiceBase;
import com.utils.SettingUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Created by admin on 2018/1/2.
 */
public class ASNService {

    public static void main(String[] args) {
//
//        String function = "getShipmentInfo";
//
//        String asnCode = "SHIP-712564";
//
//        String result = "";
//        String serviceEpr = "http://wwwdev.saic-gm.com/sgms/ppoe/services/public/ShipmentService?wsdl";
//        try {
//            Service service = new Service();
//
//            Call call = (Call) service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(serviceEpr));
//            // 服务名
////            call.setOperationName(new QName("http://shipping.integrate.service.doe.sgm.com", function));
//            call.setOperationName("getShipmentInfo");//WSDL里面描述的接口名称
//            // 定义入口参数和参数类型
//            call.addParameter("ASNNumber", XMLType.XSD_STRING, ParameterMode.IN);
//
//            // 定义返回值类型
//            call.setReturnType(XMLType.XSD_STRING);
//            // 调用服务获取返回值
//            result = String.valueOf(call.invoke(new Object[] {""}));
//
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);

        testApi();
//        testApi2();
    }


    public static void testApi() {
        ;
        try {
            ServiceClient sc = new ServiceClient();
            Options opts = sc.getOptions();
            opts.setTo(new EndpointReference("http://wwwdev.saic-gm.com/sgms/ppoe/services/public/ShipmentService?wsdl"));
            opts.setAction("getShipmentInfo");
            HttpTransportProperties.Authenticator basicAuth =
                new HttpTransportProperties.Authenticator();
            basicAuth.setUsername("apptest10");
            basicAuth.setPassword("Pass1234");
            basicAuth.setHost("http://wwwdev.saic-gm.com/");
            basicAuth.setDomain("http://wwwdev.saic-gm.com/sgms/ppoe/services/public/ShipmentService?wsdl");
            opts.setProperty(HTTPConstants.AUTHENTICATE, basicAuth);
            OMElement res = sc.sendReceive(createPayLoad());
            System.out.println(res);
        } catch (AxisFault e) {
            e.printStackTrace();
        }
    }

    public static OMElement createPayLoad() {
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace("http://wwwdev.saic-gm.com/sgms/ppoe/services/public/ShipmentService", "getShipmentInfo");
        OMElement method = fac.createOMElement("getShipmentInfo", omNs);
        OMElement value = fac.createOMElement("ASNNumber", omNs);
        value.setText("SHIP-712564");
        method.addChild(value);
        System.out.println(method);
        return method;
    }

    public static void testApi2() {
           JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
           factory.setServiceClass(ShipmentService.class);
           factory.setAddress("http://wwwdev.saic-gm.com/sgms/ppoe/services/public/ShipmentService?wsdl");

           factory.setUsername("apptest10");
           factory.setPassword("Pass1234");

           ShipmentService s = (ShipmentService) factory.create();
           System.out.println("connection result:" + s.getShipmentInfo("712564"));
       }

}
