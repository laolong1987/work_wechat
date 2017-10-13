package com.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.web.model.*;
import com.web.service.ws.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by admin on 2017/9/3.
 */
@Controller
@RequestMapping("approval")
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @RequestMapping("apply/{templateId}/{dataId}")
    public String workOvertime(@PathVariable("templateId") String templateId,
                               @PathVariable("dataId") String dataId, HttpServletRequest request, HttpServletResponse response) {
        String formInstanceRes = approvalService.getFormInstance(templateId, dataId);
        JSONObject formInstanceJson = JSONObject.parseObject(formInstanceRes);
        List<NoticeListModel> noticeList = approvalService.getNoticeList(templateId, dataId);

        //获取审批事件列表
        MayProcessItemsModel mayProcessItemsModel =approvalService.getMayProcessItemsModel(templateId, dataId,"220238");
        request.setAttribute("eventList", mayProcessItemsModel.getEventList());
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
            page = "/jsp/BusinessTripApplyModel";
        } else if ("334".equals(templateId)) {//公差申请
            SentDocModel model = new SentDocModel(formInstanceJson);
            request.setAttribute("object", model);
            page = "/jsp/SentDocument";
        }


        return page;
    }

    @RequestMapping("list")
    public String list(HttpServletRequest request, HttpServletResponse response) {

        List<WaitProcessModel> waitProcessList = approvalService.getWaitProcessNotice("220238", "0", "10","waitProcess");
        request.setAttribute("waitProcessList", waitProcessList);

        List<WaitProcessModel> processedList = approvalService.getWaitProcessNotice("220238", "0", "10","processed");
        request.setAttribute("processedList", processedList);
        return "jsp/ApproachList";
    }

    @RequestMapping("list/{type}/{page}")
    @ResponseBody
    public List<WaitProcessModel> nextPageList(@PathVariable("type") String type, @PathVariable("page") String page) {
        List<WaitProcessModel> list = new ArrayList<>();
        if ("waitProcess".equalsIgnoreCase(type)) {
            list = approvalService.getWaitProcessNotice("220238", page, "10","waitProcess");
        } else if ("processed".equalsIgnoreCase(type)) {
            list = approvalService.getWaitProcessNotice("220238", page, "10","processed");

        }


        return list;
    }

    @RequestMapping("/self-list/{templateId}")
      public String selfList(@PathVariable("templateId") String templateId,HttpServletRequest request, HttpServletResponse response) {

          List<WaitProcessModel> processedList = approvalService.getSelfProcessedNotice("220238", "0", "10","2",templateId);
          request.setAttribute("processedList", processedList);
          request.setAttribute("templateId",templateId);
          return "jsp/SelfApplyList";
      }

      @RequestMapping("/self-list/{templateId}/{page}")
      @ResponseBody
      public List<WaitProcessModel> nextPageSelfList(@PathVariable("templateId") String templateId, @PathVariable("page") String page) {
          List<WaitProcessModel> list =approvalService.getSelfProcessedNotice("220238", page, "10","2",templateId);

          return list;
      }

}
