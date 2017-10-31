package com.web.service;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.WSbean;
import com.common.WebServiceBase;
import com.web.component.WebServiceApiConfig;
import com.web.component.wechat.api.WechatComponent;
import com.web.dao.OrgDao;
import com.web.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/10/30.
 */
@Service
public class UserToWechatService {

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private WebServiceApiConfig config;

    @Autowired
    private WechatComponent wechatComponent;

    public boolean employeeToWechat() {
        List<Employee> employeeList = orgDao.findEmployee();
        for (Employee employee : employeeList) {
            if (employee.getType() == 1) {

                List<Integer> department = new ArrayList<>();
                department.add(Integer.parseInt(employee.getZzdwbm()));
                JSONObject data = new JSONObject();
                data.put("userid", employee.getYgbh());
                data.put("name", employee.getYgxm());
                data.put("department", department);
                data.put("mobile", employee.getSjhm());
                data.put("position", employee.getPositionName());
                boolean createRes = wechatComponent.createUser(data);
                if(createRes){
                    employee.setType(2);
                }else{
                    employee.setType(3);
                }

                orgDao.saveEmployee(employee);
            }
        }
        return true;

    }

    public String createDepartment() {
        String url = config.getBase() + config.getGetDepartments();
        List<WSbean> wSbeans = new ArrayList<>();
        String resultJson = WebServiceBase.call(config.getGetDepartments(), wSbeans, url);

        JSONObject departmentJson = JSONObject.parseObject(resultJson);
        JSONArray deptJsonArr = departmentJson.getJSONArray("data");

        String createRes = null;
        for (int i = 0; i < deptJsonArr.size(); i++) {
            JSONObject obj = deptJsonArr.getJSONObject(i);
            JSONObject deptObj = new JSONObject();
            deptObj.put("name", obj.getString("DeptName"));
            deptObj.put("id", obj.getInteger("DeptID"));
            if (obj.getInteger("UpDeptID") == 0) {
                deptObj.put("parentid", 1);
            } else {
                deptObj.put("parentid", obj.getInteger("UpDeptID"));
            }


            deptObj.put("order", obj.getInteger("SortIndex"));
            createRes = wechatComponent.createDepartment(deptObj);
            System.out.println(createRes);
        }
        return createRes;

    }

}
