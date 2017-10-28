package com.web.service.ws;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.WSbean;
import com.common.WebServiceBase;
import com.web.component.WebServiceApiConfig;
import com.web.model.*;
import org.apache.axis.encoding.XMLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by admin on 2017/8/28.
 */
@Service
public class ApprovalService {

    @Autowired
    private WebServiceApiConfig config;

    public List<WaitProcessModel> getWaitProcessNotice(String username, String nStartPage, String nPageSize, String apiType) {
        if (StringUtils.isEmpty(nPageSize))
            nPageSize = "10";
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

        WSbean page = new WSbean();
        page.setParametername("nStartPage");
        page.setParametervalue(nStartPage);
        page.setXMLType(XMLType.XSD_INT);

        WSbean pageSize = new WSbean();
        pageSize.setParametername("nPageSize");
        pageSize.setParametervalue(nPageSize);
        pageSize.setXMLType(XMLType.XSD_INT);
        wSbeans.add(page);
        wSbeans.add(pageSize);
        wSbeans.add(wSbean);
        String response = "";
        if ("waitProcess".equalsIgnoreCase(apiType)) {
            WSbean type = new WSbean();
            type.setParametername("nType");
            type.setParametervalue("收件");
            type.setXMLType(XMLType.XSD_STRING);
            wSbeans.add(type);
            response = WebServiceBase.call(config.getGetWaitProcessNotice(), wSbeans, url);
        } else if ("processed".equalsIgnoreCase(apiType)) {

            url = config.getBase() + config.getGetProcessedNotice();
            WSbean type = new WSbean();
            type.setParametername("nType");
            type.setParametervalue("1");
            type.setXMLType(XMLType.XSD_INT);

            wSbeans.add(type);

            response = WebServiceBase.call(config.getGetProcessedNotice(), wSbeans, url);
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        List<WaitProcessModel> waitProcessModelList = new ArrayList<>();
        if (jsonObject.getString("success").equals("true")) {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            if (dataArray.size() > 0) {
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject object = dataArray.getJSONObject(i);
                    WaitProcessModel model = new WaitProcessModel(object);
                    if (!standardTemple.contains(model.getTemplateId()))
                        continue;

                    if ("processed".equalsIgnoreCase(apiType)) {//获取状态
                        String status = "";
                        List<NoticeListModel> noticeList = getNoticeList(String.valueOf(model.getTemplateId()), String.valueOf(model.getDataId()));
                        if (noticeList.size() > 0) {
                            for (NoticeListModel noticeListModel : noticeList) {

                                status = noticeListModel.getAction();
                                if (!StringUtils.isEmpty(status))
                                    break;
                            }

                        }

                        model.setStatus(status);

                    }

                    waitProcessModelList.add(model);
                }
            }
        }
        return waitProcessModelList;
    }

    public List<WaitProcessModel> getSelfProcessedNotice(String username, String nStartPage, String nPageSize, String nType, String templateId) {
        if (StringUtils.isEmpty(nPageSize))
            nPageSize = "10";
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


        String url = url = config.getBase() + config.getGetProcessedNotice();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean wSbean = new WSbean();
        wSbean.setParametername("userName");
        wSbean.setParametervalue(username);
        wSbean.setXMLType(XMLType.XSD_STRING);

        WSbean page = new WSbean();
        page.setParametername("nStartPage");
        page.setParametervalue(nStartPage);
        page.setXMLType(XMLType.XSD_INT);

        WSbean template = new WSbean();
        template.setParametername("templateId");
        template.setParametervalue(templateId);
        template.setXMLType(XMLType.XSD_INT);

        WSbean pageSize = new WSbean();
        pageSize.setParametername("nPageSize");
        pageSize.setParametervalue(nPageSize);
        pageSize.setXMLType(XMLType.XSD_INT);

        WSbean type = new WSbean();
        type.setParametername("nType");
        type.setParametervalue(nType);
        type.setXMLType(XMLType.XSD_INT);

        wSbeans.add(page);
        wSbeans.add(pageSize);
        wSbeans.add(wSbean);
        wSbeans.add(template);
        wSbeans.add(type);

        String response = WebServiceBase.call(config.getGetProcessedNotice(), wSbeans, url);

        JSONObject jsonObject = JSONObject.parseObject(response);
        List<WaitProcessModel> waitProcessModelList = new ArrayList<>();
        if (jsonObject.getString("success").equals("true")) {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            if (dataArray.size() > 0) {
                for (int i = 0; i < dataArray.size(); i++) {
                    JSONObject object = dataArray.getJSONObject(i);
                    WaitProcessModel model = new WaitProcessModel(object);
                    if (!standardTemple.contains(model.getTemplateId()))
                        continue;

                    //获取状态
                    String status = "";
                    List<NoticeListModel> noticeList = getNoticeList(String.valueOf(model.getTemplateId()), String.valueOf(model.getDataId()));
                    if (noticeList.size() > 0) {
                        for (NoticeListModel noticeListModel : noticeList) {

                            status = noticeListModel.getAction();
                            if (!StringUtils.isEmpty(status))
                                break;
                        }

                    }
                    model.setStatus(status);


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

    public MayProcessItemsModel getMayProcessItemsModel(String templateId, String dataId, String userName) {
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
        MayProcessItemsModel mayProcessItemsModel = null;
        if (jsonObject.getString("success").equals("true")) {
            mayProcessItemsModel = new MayProcessItemsModel(jsonObject.getJSONObject("data"));
        }

        return mayProcessItemsModel;
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

    public String getFormSchema(String formType) {
        String url = config.getBase() + config.getGetFormSchema();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean template = new WSbean();
        template.setParametername("FormType");
        template.setParametervalue(formType);
        template.setXMLType(XMLType.XSD_INT);


        wSbeans.add(template);
        String response = WebServiceBase.call(config.getGetFormSchema(), wSbeans, url);


        return response;
    }

    public String raiseWorkflow(String event, String templateId, String dataId, String stateCaption, String sendBy, String content, String processBy) {
        String url = config.getBase() + config.getRaiseWorkflow();
        List<WSbean> wSbeans = new ArrayList<>();
        WSbean formEvent = new WSbean();
        formEvent.setParametername("formevent");
        formEvent.setParametervalue(event);
        formEvent.setXMLType(XMLType.XSD_STRING);

        WSbean template = new WSbean();
        template.setParametername("templateid");
        template.setParametervalue(templateId);
        template.setXMLType(XMLType.XSD_INT);

        WSbean dataID = new WSbean();
        dataID.setParametername("dataid");
        dataID.setParametervalue(dataId);
        dataID.setXMLType(XMLType.XSD_INT);

        WSbean caption = new WSbean();
        caption.setParametername("statecaption");
        caption.setParametervalue(stateCaption);
        caption.setXMLType(XMLType.XSD_STRING);

        WSbean sendBY = new WSbean();
        sendBY.setParametername("sendby");
        sendBY.setParametervalue(sendBy);
        sendBY.setXMLType(XMLType.XSD_STRING);

        WSbean contentWSbean = new WSbean();
        contentWSbean.setParametername("content");
        contentWSbean.setParametervalue(content);
        contentWSbean.setXMLType(XMLType.XSD_STRING);

        WSbean processByWSbean = new WSbean();
        processByWSbean.setParametername("processby");
        processByWSbean.setParametervalue(processBy);
        processByWSbean.setXMLType(XMLType.XSD_STRING);


        wSbeans.add(template);
        wSbeans.add(dataID);
        wSbeans.add(formEvent);
        wSbeans.add(caption);
        wSbeans.add(sendBY);
        wSbeans.add(contentWSbean);
        wSbeans.add(processByWSbean);
        String response = WebServiceBase.call(config.getRaiseWorkflow(), wSbeans, url);


        return response;
    }

    public String getDepartments() {
        String url = config.getBase() + config.getGetDepartments();
        List<WSbean> wSbeans = new ArrayList<>();
        String response = WebServiceBase.call(config.getGetDepartments(), wSbeans, url);
        return response;
    }

    public String getEmployeeUsers() {
        String url = config.getBase() + config.getGetEmployeeUsers();
        List<WSbean> wSbeans = new ArrayList<>();
        String response = WebServiceBase.call(config.getGetEmployeeUsers(), wSbeans, url);
        return response;
    }

}
