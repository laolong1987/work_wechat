package com.web.controller;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.ConvertUtil;
import com.web.service.ws.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/10/27.
 */
@Controller
@RequestMapping("dept")
public class DeptmentController {

    @Autowired
    private ApprovalService approvalService;


    @RequestMapping("employee")
    @ResponseBody
    public JSONObject employeeTree() {

        String resultJson = approvalService.getDepartments();
        JSONObject departmentJson = JSONObject.parseObject(resultJson);
        JSONArray deptJsonArr =  ConvertUtil.JSONArraySort(departmentJson.getJSONArray("data"),"SortIndex",true);

        Map<Integer, JSONObject> deptMap = new LinkedHashMap<>();

        String empStr = approvalService.getEmployeeUsers();
        JSONObject empJson = JSONObject.parseObject(empStr);
        Map<Integer, JSONArray> userList = new LinkedHashMap<>();
        JSONArray userJsonArr = empJson.getJSONArray("data");
        for (int i = 0; i < userJsonArr.size(); i++) {
            JSONObject obj = userJsonArr.getJSONObject(i);
            obj.put("realName", obj.getString("RealName"));
            obj.put("id", i+1001);
            Integer key = obj.getInteger("DeptID");
            if (userList.containsKey(key)) {
                userList.get(key).add(obj);
            } else {
                JSONArray array = new JSONArray();
                array.add(obj);
                userList.put(key, array);
            }
        }

        for (int i = 0; i < deptJsonArr.size(); i++) {
            JSONObject dept = deptJsonArr.getJSONObject(i);
            JSONObject obj =new JSONObject();
            obj.put("id", dept.getInteger("DeptID"));
            obj.put("name", dept.getString("DeptName"));
            obj.put("SortIndex", dept.getString("SortIndex"));
            obj.put("parentId", dept.getInteger("UpDeptID"));
            obj.put("childList", new JSONArray());
            obj.put("userList", new JSONArray());
            deptMap.put(dept.getInteger("DeptID"), obj);

        }


        Map<Integer, JSONArray> childList = new LinkedHashMap<>();


        for (Integer key : userList.keySet()) {
            JSONObject dept = deptMap.get(key);
            dept.put("userList", userList.get(key));
        }

        for (Integer key : deptMap.keySet()) {
            JSONObject department = deptMap.get(key);
            Integer parentId =department.getInteger("parentId");
            if (parentId==0)
                continue;
            JSONArray childArray = deptMap.get(parentId).getJSONArray("childList");
            childArray.add(department);
            deptMap.get(parentId).put("childList",childArray);
        }

       return deptMap.get(5);
    }

    public Map<Integer, JSONArray> createJsonTree(Map<Integer, JSONArray> dataMap, Map<Integer, JSONObject> deptMap) {
        Map<Integer, JSONArray> childList = new HashMap<>();
        for (Integer key : dataMap.keySet()) {
            if(key==0){
                childList.put(0, dataMap.get(0));
                continue;
            }
            JSONObject dept = deptMap.get(key);
            JSONObject obj = new JSONObject();
            Integer parentId = null;
            String deptName = null;
            try {

                parentId = dept.getInteger("UpDeptID");
                deptName = dept.getString("DeptName");
            } catch (Exception e) {
                continue;
            }


            obj.put("id", key);
            obj.put("name", deptName);
            obj.put("parentId", parentId);
            obj.put("childList", dataMap.get(key));
            obj.put("userList", new ArrayList<>());
            if(childList.containsKey(0)){

            }else if (childList.containsKey(parentId)) {
                childList.get(parentId).add(obj);
            } else {
                JSONArray array = new JSONArray();
                array.add(obj);
                childList.put(parentId, array);
            }

        }
        if (childList.size() != 1)
            childList = createJsonTree(childList, deptMap);

        return childList;
    }

}
