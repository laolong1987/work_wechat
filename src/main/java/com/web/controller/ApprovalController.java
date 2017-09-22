package com.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.web.model.*;
import com.web.service.ws.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        request.setAttribute("noticeList", noticeList);
        String page = "";
        if ("350".equals(templateId)) {
            WorkOverTime workOverTime = new WorkOverTime(formInstanceJson);
            request.setAttribute("object", workOverTime);
            page = "/jsp/workOvertimeApply";
        } else if ("322".equals(templateId)) {
            BookBorrowModel model = new BookBorrowModel(formInstanceJson);
            request.setAttribute("detailList", model.getDetailList());
            request.setAttribute("object", model);
            page = "/jsp/BookBorrowApply";
        }

        return page;
    }

    @RequestMapping("list")
    public String list(HttpServletRequest request, HttpServletResponse response) {

        List<WaitProcessModel> waitProcessList = approvalService.getWaitProcessNotice("220238", null, null);
        request.setAttribute("waitProcessList", waitProcessList);

        List<WaitProcessModel> processedList = approvalService.getWaitProcessNotice("220238", "1", "150");
        request.setAttribute("processedList", processedList);
        return "jsp/ApproachList";
    }

}
