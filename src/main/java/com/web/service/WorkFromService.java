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
