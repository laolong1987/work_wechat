package com.web.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.model.*;
import com.web.service.WorkFromService;
import com.web.service.ws.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2017/9/3.
 */
@Controller
@RequestMapping("approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private WorkFromService workFromService;

    @RequestMapping("apply/{templateId}/{dataId}")
    public String workOvertime(@PathVariable("templateId") String templateId,
                               @PathVariable("dataId") String dataId, HttpServletRequest request, HttpServletResponse response) {
        String formInstanceRes = approvalService.getFormInstance(templateId, dataId);
        JSONObject formInstanceJson = JSONObject.parseObject(formInstanceRes);
        List<NoticeListModel> noticeList = approvalService.getNoticeList(templateId, dataId);
        String status="";
        if (noticeList.size() > 0) {
            for (NoticeListModel noticeListModel : noticeList) {
                status = noticeListModel.getAction();
                if (!StringUtils.isEmpty(status))
                    break;
            }
        }

        if(status.equals("同意")){
            request.setAttribute("aprovalSeal",1);
        }else if("不同意".equals(status)){
            request.setAttribute("aprovalSeal",2);
        }else{
            request.setAttribute("aprovalSeal",0);
        }

        String userId= String.valueOf(request.getSession().getAttribute("approvalUserId"));

        //获取审批事件列表
        MayProcessItemsModel mayProcessItemsModel = approvalService.getMayProcessItemsModel(templateId, dataId, userId);
        if (mayProcessItemsModel != null) {
            request.setAttribute("stateCaption", mayProcessItemsModel.getStateCaption());
            request.setAttribute("eventList", mayProcessItemsModel.getEventList());
            request.setAttribute("editfields", mayProcessItemsModel.getEditFields());
            request.setAttribute("approval", true);
        } else {
            request.setAttribute("approval", false);
        }
        request.setAttribute("templateId", templateId);
        request.setAttribute("dataId", dataId);
        request.setAttribute("sentBy", request.getParameter("sentby"));
        request.setAttribute("noticeList", noticeList);
        String page = "";
        if ("350".equals(templateId)) {//法定节假日加班申请单
            WorkOverTime workOverTime = new WorkOverTime(formInstanceJson);
            request.setAttribute("object", workOverTime);
            page = "/jsp/workOvertimeApply";
        } else if ("322".equals(templateId)) {//档案借阅申请单
            BookBorrowModel model = new BookBorrowModel(formInstanceJson);
            request.setAttribute("detailList", model.getDetailList());
            request.setAttribute("object", model);
            page = "/jsp/BookBorrowApply";
        } else if ("323".equals(templateId)) {//客饭申请单
            RepastApplyModel model = new RepastApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/RepastApply";
        } else if ("358".equals(templateId)) {//劳保总务用品领用单
            MaterialApplyModel model = new MaterialApplyModel(formInstanceJson);
            request.setAttribute("detailList", model.getDetailList());
            request.setAttribute("object", model);
            page = "/jsp/MaterialApply";
        } else if ("360".equals(templateId)) {//旅行箱借用申请
            SuitcaseBorrowModel model = new SuitcaseBorrowModel(formInstanceJson);
            request.setAttribute("detailList", model.getDetailList());
            request.setAttribute("object", model);
            page = "/jsp/SuitcaseBorrowApply";
        } else if ("349".equals(templateId)) {//请假申请
            LeaveApplyModel model = new LeaveApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/LeaveApply";
        } else if ("391".equals(templateId)) {//打印申请
            PrintApplyModel model = new PrintApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/PrintApply";
        } else if ("321".equals(templateId)) {//用车申请
            UseCarApplyModel model = new UseCarApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/UseCarApply";
        } else if ("432".equals(templateId)) {//员工补办证件申请表
            CredentialsApplyModel model = new CredentialsApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/CredentialsApply";
        } else if ("500".equals(templateId)) {//资产申请
            PropertyApplyModel model = new PropertyApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/PropertyApply";
        } else if ("326".equals(templateId)) {//印章申请
            SealApplyModel model = new SealApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/SealApply";
        } else if ("374".equals(templateId)) {//公差申请
            BusinessTripApplyModel model = new BusinessTripApplyModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/BusinessTripApply";
        } else if ("334".equals(templateId)) {//发文单
            SentDocModel model = new SentDocModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/SentDocument";
        } else if ("329".equals(templateId)) {//收文单
            ReceiveDocModel model = new ReceiveDocModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/RecevieDocument";
        }


        return page;
    }

    @RequestMapping("list")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        String userId= String.valueOf(request.getSession().getAttribute("approvalUserId"));

        List<WaitProcessModel> waitProcessList = approvalService.getWaitProcessNotice(userId, "0", "10", "waitProcess");
        request.setAttribute("waitProcessList", waitProcessList);

//        List<WaitProcessModel> processedList = approvalService.getWaitProcessNotice(userId, "0", "10", "processed");
//        request.setAttribute("processedList", processedList);
        return "jsp/ApproachList";
    }

    @RequestMapping("list/{type}/{page}")
    @ResponseBody
    public List<WaitProcessModel> nextPageList(@PathVariable("type") String type, @PathVariable("page") String page,HttpServletRequest request) {
        List<WaitProcessModel> list = new ArrayList<>();
        String userId= String.valueOf(request.getSession().getAttribute("approvalUserId"));

        if ("waitProcess".equalsIgnoreCase(type)) {
            list = approvalService.getWaitProcessNotice(userId, page, "10", "waitProcess");
        } else if ("processed".equalsIgnoreCase(type)) {
            list = approvalService.getWaitProcessNotice(userId, page, "10", "processed");

        }


        return list;
    }

    @RequestMapping("/self-list/{templateId}")
    public String selfList(@PathVariable("templateId") String templateId, HttpServletRequest request, HttpServletResponse response) {
        String userId= String.valueOf(request.getSession().getAttribute("approvalUserId"));

        List<WaitProcessModel> processedList = approvalService.getSelfProcessedNotice(userId, "0", "20", "2", templateId);
        request.setAttribute("processedList", processedList);
        request.setAttribute("templateId", templateId);
        return "jsp/SelfApplyList";
    }

    @RequestMapping("/self-list/{templateId}/{page}")
    @ResponseBody
    public List<WaitProcessModel> nextPageSelfList(@PathVariable("templateId") String templateId, @PathVariable("page") String page, HttpServletRequest request) {
        String userId= String.valueOf(request.getSession().getAttribute("approvalUserId"));

        List<WaitProcessModel> list = approvalService.getSelfProcessedNotice(userId, page, "10", "2", templateId);

        return list;
    }

    @RequestMapping(value = "doApproval", method = RequestMethod.POST)
    @ResponseBody
    public String raiseWorkflow(@RequestParam Map<String, String> parameterMap, HttpServletRequest request) {
        String processBy =  String.valueOf(request.getSession().getAttribute("approvalUserId"));


        String event = null;
        String templateId = null;
        String dataId = null;
        String stateCaption = null;
        String sendBy = null;
        String content = null;
        JSONObject data = new JSONObject();
        for (String key : parameterMap.keySet()) {
            if (key.equals("event")) {
                event = String.valueOf(parameterMap.get(key).toString());
            } else if (key.equals("templateId")) {
                templateId = parameterMap.get(key).toString();
            } else if (key.equals("content")) {
                content = parameterMap.get(key).toString();
            } else if (key.equals("dataId")) {
                dataId = parameterMap.get(key).toString();
            } else if (key.equals("stateCaption")) {
                stateCaption = parameterMap.get(key).toString();
            } else if (key.equals("sendBy")) {
                sendBy = parameterMap.get(key).toString();
            } else {
                data.put(key, parameterMap.get(key).toString());
            }

        }
        if (data.size() > 0) {
            JSONObject json = new JSONObject();
            json.put("FormType", Integer.parseInt(templateId));
            json.put("data", data);
            data.put("ID", Integer.parseInt(dataId));
            String FormConfigID = workFromService.CreateFormInstance(json.toJSONString());
            JSONObject resultJson = JSON.parseObject(FormConfigID);
            if (!resultJson.getBoolean("success"))
                return "501";

        }
        String res = approvalService.raiseWorkflow(event, templateId, dataId, stateCaption, sendBy, content, processBy);
        JSONObject resultJson = JSON.parseObject(res);
        if (!resultJson.getBoolean("success"))
            return "502";
        return "success";
    }

}
