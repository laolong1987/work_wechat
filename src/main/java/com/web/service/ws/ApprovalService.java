package com.web.service.ws;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.WSbean;
import com.common.WebServiceBase;
import com.web.component.WebServiceApiConfig;
import com.web.model.NoticeListModel;
import com.web.model.WaitProcessModel;
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

    public List<WaitProcessModel> getWaitProcessNotice(String username, String nStartPage, String nPageSize) {

        Set<Integer> standardTemple = new HashSet<>();
        // standardTemple.add(316);//工作督办单
        standardTemple.add(321);//用车申请单
        standardTemple.add(322);//档案借阅申请单
        standardTemple.add(323);//客饭申请单
        standardTemple.add(326);//印章申请单
        standardTemple.add(349);//员工请假单
        standardTemple.add(350);//法定节假日加班申请单
        standardTemple.add(358);//劳保总务用品领用单
        standardTemple.add(360);//旅行箱借用申请单
        standardTemple.add(391);//印刷申请单
        standardTemple.add(432);//员工补办证件申请表
        standardTemple.add(500);//资产申请
        standardTemple.add(329);//收文单
        standardTemple.add(374);//公差申请单
        standardTemple.add(334);//发文单


        String url = config.getBase() + config.getGetWaitProcessNotice();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("userName");
        wSbean.setParametervalue(username);
        wSbean.setXMLType(XMLType.XSD_STRING);
        wSbeans.add(wSbean);
        String response ="";
        if (nStartPage != null && nPageSize != null) {
            url = config.getBase() + config.getGetProcessedNotice();
            WSbean template = new WSbean();
            template.setParametername("nStartPage");
            template.setParametervalue(nStartPage);
            template.setXMLType(XMLType.XSD_INT);

            WSbean data = new WSbean();
            data.setParametername("nPageSize");
            data.setParametervalue(nPageSize);
            data.setXMLType(XMLType.XSD_INT);
            wSbeans.add(data);
            wSbeans.add(template);
            response = WebServiceBase.call(config.getGetProcessedNotice(), wSbeans, url);
        }else{
            response = WebServiceBase.call(config.getGetWaitProcessNotice(), wSbeans, url);
        }

        JSONObject jsonObject = JSONObject.parseObject(response);
        List<WaitProcessModel> waitProcessModelList = new ArrayList<>();
        if (jsonObject.getString("success").equals("true")) {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            if (dataArray.size() > 0) {
                for (int i = 0; i <dataArray.size() ; i++) {
                    JSONObject object = dataArray.getJSONObject(i);
                    WaitProcessModel model = new WaitProcessModel(object);
                    if (!standardTemple.contains(model.getTemplateId()))
                        continue;
                    //获取状态
                    String status = getMayProcessItems(model.getTemplateId(), model.getDataId(), username);
                    model.setStatus(status);

                    //获取单号
                    String orderNum = getFormInstanceID(model.getTemplateId(), model.getDataId());
                    model.setOrderNum(orderNum);
                    waitProcessModelList.add(model);
                }
            }
        }
        return waitProcessModelList;
    }


    public String getFormInstance(String templateId, String dataId) {
        String url = config.getBase() + config.getGetFormInstance();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean template = new WSbean();
        template.setParametername("templateid");
        template.setParametervalue(templateId);
        template.setXMLType(XMLType.XSD_INT);

        WSbean data = new WSbean();
        data.setParametername("dataid");
        data.setParametervalue(dataId);
        data.setXMLType(XMLType.XSD_INT);
        wSbeans.add(data);
        wSbeans.add(template);
        String response = WebServiceBase.call(config.getGetFormInstance(), wSbeans, url);
        return response;
    }

    public String getFormInstanceID(Integer templateId, Integer dataId) {
        String url = config.getBase() + config.getGetFormInstance();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean template = new WSbean();
        template.setParametername("templateid");
        template.setParametervalue(String.valueOf(templateId));
        template.setXMLType(XMLType.XSD_INT);

        WSbean data = new WSbean();
        data.setParametername("dataid");
        data.setParametervalue(String.valueOf(dataId));
        data.setXMLType(XMLType.XSD_INT);
        wSbeans.add(data);
        wSbeans.add(template);
        String response = WebServiceBase.call(config.getGetFormInstance(), wSbeans, url);
        JSONObject jsonObject = JSONObject.parseObject(response);
        String orderNum = "未知";
        if (jsonObject.containsKey("sucess") && jsonObject.getString("sucess").equals("true")) {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            JSONObject detailInfo = dataArray.getJSONObject(0);
            orderNum = detailInfo.getString("_FORMNO");
        } else if (jsonObject.containsKey("success") && jsonObject.getString("success").equals("true")) {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            JSONObject detailInfo = dataArray.getJSONObject(0);
            orderNum = detailInfo.getString("_FORMNO");
        }

        return orderNum;

    }

    public String getMayProcessItems(Integer templateId, Integer dataId, String userName) {
        String url = config.getBase() + config.getGetMayProcessItems();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean template = new WSbean();
        template.setParametername("templateid");
        template.setParametervalue(String.valueOf(templateId));
        template.setXMLType(XMLType.XSD_INT);

        WSbean data = new WSbean();
        data.setParametername("dataid");
        data.setParametervalue(String.valueOf(dataId));
        data.setXMLType(XMLType.XSD_INT);


        WSbean userId = new WSbean();
        userId.setParametername("userName");
        userId.setParametervalue(userName);
        userId.setXMLType(XMLType.XSD_STRING);

        wSbeans.add(template);
        wSbeans.add(data);
        wSbeans.add(userId);
        String response = WebServiceBase.call(config.getGetMayProcessItems(), wSbeans, url);

        JSONObject jsonObject = JSONObject.parseObject(response);
        String status = "未知";
        if (jsonObject.getString("success").equals("true")) {
            JSONObject statusJson = jsonObject.getJSONObject("data");
            status = statusJson.getString("procstatus");
        }

        return status;
    }

    public String getMayProcessItems(String templateId, String dataId, String userName) {
        String url = config.getBase() + config.getGetMayProcessItems();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean template = new WSbean();
        template.setParametername("templateid");
        template.setParametervalue(templateId);
        template.setXMLType(XMLType.XSD_INT);

        WSbean data = new WSbean();
        data.setParametername("dataid");
        data.setParametervalue(dataId);
        data.setXMLType(XMLType.XSD_INT);


        WSbean userId = new WSbean();
        userId.setParametername("userName");
        userId.setParametervalue(userName);
        userId.setXMLType(XMLType.XSD_STRING);

        wSbeans.add(template);
        wSbeans.add(data);
        wSbeans.add(userId);
        String response = WebServiceBase.call(config.getGetMayProcessItems(), wSbeans, url);
        return response;
    }

    public List<NoticeListModel> getNoticeList(String templateId, String dataId) {
        String url = config.getBase() + config.getGetNoticeList();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean template = new WSbean();
        template.setParametername("templateid");
        template.setParametervalue(templateId);
        template.setXMLType(XMLType.XSD_INT);

        WSbean data = new WSbean();
        data.setParametername("dataid");
        data.setParametervalue(dataId);
        data.setXMLType(XMLType.XSD_INT);

        wSbeans.add(template);
        wSbeans.add(data);
        String response = WebServiceBase.call(config.getGetNoticeList(), wSbeans, url);

        JSONObject jsonObject = JSONObject.parseObject(response);
        List<NoticeListModel> noticeList = new ArrayList<>();
        if (jsonObject.getString("success").equals("true")) {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            if (dataArray.size() > 0) {
                for (int i = dataArray.size() - 1; i >= 0; i--) {
                    JSONObject object = dataArray.getJSONObject(i);
                    NoticeListModel model = new NoticeListModel(object);
                    noticeList.add(model);
                }
            }
        }

        return noticeList;
    }

}
