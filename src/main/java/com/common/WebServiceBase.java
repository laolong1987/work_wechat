package com.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 17/8/16.
 */
public class WebServiceBase {


    public static String call(String function,List<WSbean> wSbeans){
        String result="";
        String serviceEpr = "http://d.bm21.com.cn:20003/Services/CoreService.asmx?WSDL";
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(serviceEpr));
            // 服务名
            call.setOperationName(new QName("http://tempuri.org/",function));
            // 定义入口参数和参数类型
            Object[] params= new Object[wSbeans.size()];
            int i=0;
            for (WSbean wSbean : wSbeans ) {
                call.addParameter(new QName("http://tempuri.org/", wSbean.getParametername()), wSbean.getXMLType(), ParameterMode.IN);
                params[i]=wSbean.getParametervalue();
                i++;
            }
            call.setUseSOAPAction(true);
            // Action地址
            call.setSOAPActionURI("http://tempuri.org/"+function);
            // 定义返回值类型
            call.setReturnType(XMLType.XSD_STRING);
            // 调用服务获取返回值
            result = String.valueOf(call.invoke(params));

        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
//		调用 demo
        List<WSbean> list = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("userName");
        wSbean.setParametervalue("220309");
        wSbean.setXMLType(XMLType.XSD_STRING);
        list.add(wSbean);
        String r1 = WebServiceBase.call("GetWaitProcessNotice", list);
        JSONObject resultJson = JSON.parseObject(r1);
        JSONArray jsonArray1 = JSON.parseArray(resultJson.getString("data"));
        for (int i = 0; i < jsonArray1.size(); i++) {
            System.out.println("---------审批明细数据---开始-----------------");
            JSONObject job = jsonArray1.getJSONObject(i);
//			System.out.println("templateid:"+job.getString("templateid")) ;
//			System.out.println("dataid:"+job.getString("dataid")) ;
            for (Map.Entry<String, Object> entry : job.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }

            list = new ArrayList<>();
            wSbean = new WSbean();
            wSbean.setParametername("templateid");
            wSbean.setParametervalue(job.getString("templateid"));
            wSbean.setXMLType(XMLType.XSD_INT);
            list.add(wSbean);
            wSbean = new WSbean();
            wSbean.setParametername("dataid");
            wSbean.setParametervalue(job.getString("dataid"));
            wSbean.setXMLType(XMLType.XSD_INT);
            list.add(wSbean);
            String r2 = WebServiceBase.call("GetFormInstance", list);
            JSONObject resultJson2 = JSON.parseObject(r2);
            for (Map.Entry<String, Object> entry : resultJson2.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            System.out.println("-----------------------------------------");
        }
    }
    }
