package com.web.service;

import com.alibaba.fastjson.JSONObject;
import com.common.WSbean;
import com.common.WebServiceBase;
import com.web.dao.DemoDao;
import com.web.entity.Demo;
import org.apache.axis.encoding.XMLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by gaoyang on 16/2/29.
 */
@Service("workFromService")
public class WorkFromService {

    public String getleavefrom(){

        return "";
    }

    public String CreateFormInstance(String json){
        List<WSbean> list = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("instance");
        wSbean.setParametervalue(json);
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);
        String r1 = WebServiceBase.call("CreateFormInstance", list);
        return r1;
    }

    public String StartFormWorkflow(String json){
        List<WSbean> list = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("FormConfigID");
        wSbean.setParametervalue(json);
        wSbean.setXMLType(XMLType.XSD_INT);
        list.add(wSbean);
        String r1 = WebServiceBase.call("StartFormWorkflow", list);
        return r1;
    }

    public String RaiseWorkflowb(Map<String,String> map){
        List<WSbean> list = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("formevent");
        wSbean.setParametervalue(map.get("formevent"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("configid");
        wSbean.setParametervalue(map.get("configid"));
        wSbean.setXMLType(XMLType.XSD_INT);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("statecaption");
        wSbean.setParametervalue(map.get("statecaption"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("sendby");
        wSbean.setParametervalue(map.get("sendby"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("content");
        wSbean.setParametervalue(map.get("content"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("processby");
        wSbean.setParametervalue(map.get("processby"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        String r1 = WebServiceBase.call("RaiseWorkflowb", list);
        return r1;
    }

    public String RaiseWorkflow(Map<String,String> map){
        List<WSbean> list = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("formevent");
        wSbean.setParametervalue(map.get("formevent"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("templateid");
        wSbean.setParametervalue(map.get("templateid"));
        wSbean.setXMLType(XMLType.XSD_INT);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("dataid");
        wSbean.setParametervalue(map.get("dataid"));
        wSbean.setXMLType(XMLType.XSD_INT);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("statecaption");
        wSbean.setParametervalue(map.get("statecaption"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("sendby");
        wSbean.setParametervalue(map.get("sendby"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("content");
        wSbean.setParametervalue(map.get("content"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        wSbean = new WSbean();
        wSbean.setParametername("processby");
        wSbean.setParametervalue(map.get("processby"));
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);

        String r1 = WebServiceBase.call("RaiseWorkflow", list);
        return r1;
    }


    public static void main(String[] args) {
        //组装JSON
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("QjType","年休假");
        jsonObject.put("QjStart","2017-09-01 13:54");
        jsonObject.put("QjEnd","2017-09-08 13:54");
        jsonObject.put("Ygxm","戴欣欣");
        jsonObject.put("QjDays","7.0");
        jsonObject.put("Depart","总经理工作部");
        jsonObject.put("Position","普通职员");
        jsonObject.put("Qjly","理由");
        jsonObject.put("FormType","349");

        System.out.println(jsonObject.toJSONString());

        List<WSbean> list = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("instance");
        wSbean.setParametervalue(jsonObject.toJSONString());
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);
        String r1 = WebServiceBase.call("CreateFormInstance", list);
        System.out.println(r1);

    }
}
