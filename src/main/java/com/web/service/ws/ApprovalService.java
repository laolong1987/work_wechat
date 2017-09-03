package com.web.service.ws;

import java.util.ArrayList;
import java.util.List;

import com.common.WSbean;
import com.common.WebServiceBase;
import com.web.component.WebServiceApiConfig;
import org.apache.axis.encoding.XMLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/8/28.
 */
@Service
public class ApprovalService {

    @Autowired
    private WebServiceApiConfig config;

    public String getWaitProcessNotice(String username) {
        String url = config.getBase() + config.getGetWaitProcessNotice();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("userName");
        wSbean.setParametervalue(username);
        wSbean.setXMLType(XMLType.XSD_STRING);
        wSbeans.add(wSbean);
        String response=   WebServiceBase.call(config.getGetWaitProcessNotice(), wSbeans, url);
        return response;
    }

}
