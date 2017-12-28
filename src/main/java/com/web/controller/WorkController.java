package com.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.utils.StringUtil;
import com.web.entity.Dept;
import com.web.entity.Employee;
import com.web.entity.News;
import com.web.entity.Newsflag;
import com.web.service.NewsService;
import com.web.service.OrgService;
import com.web.service.WorkFromService;
import com.web.service.ws.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 16/3/1.
 */

@Controller
@RequestMapping("/work")
public class WorkController {

    @Autowired
    WorkFromService workFromService;
    @Autowired
    OrgService orgService;

    @Autowired
    private ApprovalService approvalService;

    @RequestMapping(value = "/addleave", method = RequestMethod.GET)
    public String addleave(HttpServletRequest request,HttpServletResponse response) {
        
        String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");

        if("".equals(userid)){
            return "/jsp/app/noquanxian";
        }
        Employee employee= orgService.findEmployee(userid);
        if(null==employee){
            return "/jsp/app/noquanxian";
        }
        Dept dept=orgService.findDept(employee.getZzdwbm());
        request.setAttribute("name",employee.getYgxm());
        request.setAttribute("dept",dept.getDeptName());



        return "/jsp/app/addleave";
    }


    @RequestMapping(value = "/createleave", method = RequestMethod.POST)
    public String createleave(HttpServletRequest request,HttpServletResponse response) {
        String type=ConvertUtil.safeToString(request.getParameter("type"),"");
        String day=ConvertUtil.safeToString(request.getParameter("day"),"");
        String date1=ConvertUtil.safeToString(request.getParameter("date1"),"");
        String date2=ConvertUtil.safeToString(request.getParameter("date2"),"");
        String desc=ConvertUtil.safeToString(request.getParameter("desc"),"");


        String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");

        Employee employee= orgService.findEmployee(userid);
        Dept dept=orgService.findDept(employee.getZzdwbm());
        JSONObject data=new JSONObject();
        data.put("Depart",dept.getDeptName());
//        data.put("Depart","总经理工作部");
//        data.put("Position","部门经理");
        data.put("Position",employee.getPositionName());
        data.put("QjDays",day+".0");
        data.put("QjEnd",date2);
        data.put("QjStart",date1);
        data.put("QjType",type);
        data.put("Qjly",desc);
        data.put("Ygxm",employee.getYgxm());
//        data.put("Ygxm","华安");
        data.put("Writer",userid);
        data.put("_SUBJECT",employee.getYgxm()+"请假单");
        data.put("Qjbh","_FORMNO");

        JSONObject json=new JSONObject();
        json.put("FormType","349");
        json.put("data",data);

        System.out.println(DateUtil.getCurrentTime()+"--- createleave start --- ");
        String FormConfigID= workFromService.CreateFormInstance(json.toJSONString());
        JSONObject resultJson = JSON.parseObject(FormConfigID);
        System.out.println(FormConfigID);
        String result= workFromService.StartFormWorkflow(resultJson.getString("FormConfigID"));
        System.out.println(result);
        String result2= approvalService.getMayProcessItems(resultJson.getString("TemplateID"),resultJson.getString("DataID"),userid);
        System.out.println(result2);

        JSONObject jsonObject2=JSON.parseObject(result2);
        JSONObject jsonObject3= (JSONObject) jsonObject2.get("data");
        JSONArray jsonArray=jsonObject3.getJSONArray("eventlist");
        Map<String,String> map=new HashMap<>();
        map.put("formevent",jsonArray.getJSONObject(0).get("event").toString());
//        map.put("configid",resultJson.getString("FormConfigID"));
        map.put("templateid",resultJson.getString("TemplateID"));
        map.put("dataid",resultJson.getString("DataID"));
        map.put("statecaption",jsonObject3.get("statecaption").toString());
        map.put("sendby",userid);
        map.put("content","");
        map.put("processby",userid);
        String result3=workFromService.RaiseWorkflow(map);
        System.out.println(result3);
        System.out.println(DateUtil.getCurrentTime()+"--- createleave end --- ");
        return "redirect:../approval/self-list/349";

    }


    @RequestMapping(value = "/addcar", method = RequestMethod.GET)
    public String addcar(HttpServletRequest request,HttpServletResponse response) {
        String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");
        if("".equals(userid)){
            return "/jsp/app/noquanxian";
        }
        Employee employee= orgService.findEmployee(userid);
        if(null==employee){
            return "/jsp/app/noquanxian";
        }
        Dept dept=orgService.findDept(employee.getZzdwbm());
        request.setAttribute("name",employee.getYgxm());
        request.setAttribute("dept",dept.getDeptName());
        return "/jsp/app/addcar";
    }


    @RequestMapping(value = "/createcar", method = RequestMethod.POST)
    public String createcar(HttpServletRequest request,HttpServletResponse response) {
        String ydrphone=ConvertUtil.safeToString(request.getParameter("ydrphone"),"");
        String starttime=ConvertUtil.safeToString(request.getParameter("starttime"),"");
        String endtime=ConvertUtil.safeToString(request.getParameter("endtime"),"");
        String zrs=ConvertUtil.safeToString(request.getParameter("zrs"),"");
        String pickupaddress=ConvertUtil.safeToString(request.getParameter("pickupaddress"),"");
        String Sfwd=ConvertUtil.safeToString(request.getParameter("Sfwd"),"");
        String destination=ConvertUtil.safeToString(request.getParameter("destination"),"");
        String clyt=ConvertUtil.safeToString(request.getParameter("clyt"),"");
        String remark=ConvertUtil.safeToString(request.getParameter("remark"),"");
        String ccry=ConvertUtil.safeToString(request.getParameter("usernames"),"");

        String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");

        Employee employee= orgService.findEmployee(userid);
        Dept dept=orgService.findDept(employee.getZzdwbm());
        JSONObject data=new JSONObject();
//        data.put("Depart",employee.getZzdwmc());
//        data.put("Position","普通职员");
        data.put("ydbm",dept.getDeptName());
        data.put("Position",employee.getPositionName());
        data.put("ydr",employee.getYgxm());

        data.put("ydrphone",ydrphone);
        data.put("starttime",starttime);
        data.put("endtime",endtime);
        data.put("zrs",zrs);
        data.put("Sfwd",Sfwd);
        data.put("pickupaddress",pickupaddress);
        data.put("destination",destination);
        data.put("clyt",clyt);
        data.put("remark",remark);
        data.put("ccry",ccry);
        data.put("ydrq",DateUtil.getCurrentTime("yyyy-MM-dd HH:mm"));

        data.put("_SUBJECT",employee.getYgxm()+"用车申请单");
        data.put("Writer",userid);
        JSONObject json=new JSONObject();
        json.put("FormType","321");
        json.put("data",data);

        System.out.println(DateUtil.getCurrentTime()+"--- createcar start --- ");

        String FormConfigID= workFromService.CreateFormInstance(json.toJSONString());
        JSONObject resultJson = JSON.parseObject(FormConfigID);
        String result= workFromService.StartFormWorkflow(resultJson.getString("FormConfigID"));
        System.out.println(result);


//        {"fields":[{"name":"ID","type":"Int32"},{"name":"_SUBJECT","type":"String"},
// {"name":"_FORMNO","type":"String"},{"name":"clyt","type":"String"},{"name":"ydbm","type":"String"},
// {"name":"pickupaddress","type":"String"},{"name":"ydrphone","type":"String"},{"name":"ydr","type":"String"},
// {"name":"driverphone","type":"String"},{"name":"ccry","type":"String"},{"name":"endtime","type":"String"},
// {"name":"remark","type":"String"},{"name":"selectautomobile","type":"String"},{"name":"starttime","type":"String"},
// {"name":"destination","type":"String"},{"name":"driver","type":"String"},{"name":"ydrq","type":"String"},
// {"name":"Sfwd","type":"String"},{"name":"zrs","type":"String"}],"FormType":321,"ShortTitle":"用车申请单"}

        String result2= approvalService.getMayProcessItems(resultJson.getString("TemplateID"),resultJson.getString("DataID"),userid);
        System.out.println(result2);
        JSONObject jsonObject2=JSON.parseObject(result2);
        JSONObject jsonObject3= (JSONObject) jsonObject2.get("data");
        JSONArray jsonArray=jsonObject3.getJSONArray("eventlist");
        Map<String,String> map=new HashMap<>();
        map.put("formevent",jsonArray.getJSONObject(0).get("event").toString());
//        map.put("configid",resultJson.getString("FormConfigID"));
        map.put("templateid",resultJson.getString("TemplateID"));
        map.put("dataid",resultJson.getString("DataID"));
        map.put("statecaption",jsonObject3.get("statecaption").toString());
        map.put("sendby",userid);
        map.put("content","");
        map.put("processby",userid);
        String result3=workFromService.RaiseWorkflow(map);
        System.out.println(result3);
        System.out.println(DateUtil.getCurrentTime()+"--- createcar end --- ");
        return "redirect:../approval/self-list/321";
    }



    @RequestMapping(value = "/addguestmeal", method = RequestMethod.GET)
    public String addguestmeal(HttpServletRequest request,HttpServletResponse response) {
        String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");
        if("".equals(userid)){
            return "/jsp/app/noquanxian";
        }
        Employee employee= orgService.findEmployee(userid);
        if(null==employee){
            return "/jsp/app/noquanxian";
        }
        Dept dept=orgService.findDept(employee.getZzdwbm());
        request.setAttribute("name",employee.getYgxm());
        request.setAttribute("dept",dept.getDeptName());

        return "/jsp/app/addguestmeal";
    }

    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public String department(HttpServletRequest request,HttpServletResponse response) {

        return "/jsp/app/department";
    }


    @RequestMapping(value = "/createguestmeal", method = RequestMethod.POST)
    public String createguestmeal(HttpServletRequest request,HttpServletResponse response) {
        String lfdwjry=ConvertUtil.safeToString(request.getParameter("lfdwjry"),"");
        String ycrq=ConvertUtil.safeToString(request.getParameter("ycrq"),"");
        String yctype=ConvertUtil.safeToString(request.getParameter("yctype"),"");
        String yczl=ConvertUtil.safeToString(request.getParameter("yczl"),"");
        String ycsl=ConvertUtil.safeToString(request.getParameter("ycsl"),"");
        String ycremark=ConvertUtil.safeToString(request.getParameter("ycremark"),"");
        String ycbz=ConvertUtil.safeToString(request.getParameter("ycbz"),"");
        String ycdd=ConvertUtil.safeToString(request.getParameter("ycdd"),"");


        String userid=ConvertUtil.safeToString(request.getSession().getAttribute("newsUserId"),"");

        Employee employee= orgService.findEmployee(userid);
        Dept dept=orgService.findDept(employee.getZzdwbm());

        JSONObject data=new JSONObject();
        data.put("Depart",dept.getDeptName());
//        data.put("Position","普通职员");
//        data.put("Depart","总经理工作部");
        data.put("Position",employee.getPositionName());
        data.put("sqbm",employee.getPositionName());
        data.put("lfdwjry",lfdwjry);
        data.put("ycrq",ycrq);
        data.put("yctype",yctype);
        data.put("yczl",yczl);
        data.put("ycsl",ycsl);
        data.put("ycremark",ycremark);
        data.put("ycbz",ycbz);
        data.put("ycbz1",ycbz);
        data.put("ycdd",ycdd);
        data.put("ycdd1",ycdd);
        data.put("ycsj",yczl);
        data.put("sjycsl",ycsl);
//        data.put("Ygxm",employee.getYgxm().replace(" ",""));
        data.put("_SUBJECT",employee.getYgxm()+"客饭申请");

        data.put("sqr",employee.getYgxm());
        data.put("Writer",userid);
        JSONObject json=new JSONObject();
        json.put("FormType","323");
        json.put("data",data);

//        {"fields":[{"name":"ID","type":"Int32"},{"name":"_SUBJECT","type":"String"},
// {"name":"_FORMNO","type":"String"},{"name":"ycremark","type":"String"},{"name":"sqbm","type":"String"},
// {"name":"ycdd1","type":"String"},{"name":"ycbz1","type":"String"},{"name":"sqr","type":"String"},
// {"name":"yczl","type":"String"},{"name":"lfdwjry","type":"String"},{"name":"ycbz","type":"String"},
// {"name":"bmsh","type":"String"},{"name":"ycsj","type":"String"},{"name":"ycrq","type":"String"},
// {"name":"ycdd","type":"String"},{"name":"bmshid","type":"String"},{"name":"ycsl","type":"Int32"},
// {"name":"lcsy","type":"String"},{"name":"sjycbz","type":"Decimal"},{"name":"ycsj1","type":"String"},
// {"name":"sjycsl","type":"String"},{"name":"xfje","type":"Decimal"},{"name":"yctype","type":"String"}],"FormType":323,"ShortTitle":"客饭申请单"}


        System.out.println(DateUtil.getCurrentTime()+"--- createguestmeal start --- ");
        String FormConfigID= workFromService.CreateFormInstance(json.toJSONString());
        JSONObject resultJson = JSON.parseObject(FormConfigID);
        String result= workFromService.StartFormWorkflow(resultJson.getString("FormConfigID"));
        System.out.println(result);
        String result2= approvalService.getMayProcessItems(resultJson.getString("TemplateID"),resultJson.getString("DataID"),userid);
        System.out.println(result2);
        JSONObject jsonObject2=JSON.parseObject(result2);
        JSONObject jsonObject3= (JSONObject) jsonObject2.get("data");
        JSONArray jsonArray=jsonObject3.getJSONArray("eventlist");
        Map<String,String> map=new HashMap<>();
        map.put("formevent",jsonArray.getJSONObject(0).get("event").toString());
//        map.put("configid",resultJson.getString("FormConfigID"));
        map.put("templateid",resultJson.getString("TemplateID"));
        map.put("dataid",resultJson.getString("DataID"));
        map.put("statecaption",jsonObject3.get("statecaption").toString());
        map.put("sendby",userid);
        map.put("content","");
        map.put("processby",userid);
        String result3=workFromService.RaiseWorkflow(map);
        System.out.println(result3);
        System.out.println(DateUtil.getCurrentTime()+"--- createguestmeal end --- ");

        return "redirect:../approval/self-list/323";
    }
}
