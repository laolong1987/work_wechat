package com.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.HttpHelper;
import com.sun.tools.hat.internal.model.JavaObject;
import com.web.dao.OrgDao;
import com.web.dao.UserDao;
import com.web.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orgService")
public class OrgService {

    @Autowired
    OrgDao orgDao;

    private static String APPID="0170822001";

    public void updateEmp(){
        List<Employee> list=new ArrayList<>();
        //查询
        String tokenurl="http://d.bm21.com.cn:20006/token";
        String listurl = "http://d.bm21.com.cn:20006/employee/list";
        Map params = new HashMap();
        params.put("Appid",APPID);
        params.put("Username","220342");
        params.put("Passwd","123456");
        String ret = HttpHelper.fetchUTF8StringByPost(tokenurl, params, null, 0, 0);
        JSONObject jo1= JSON.parseObject(ret);
        if(0==jo1.getInteger("state")){
            params.clear();
            params.put("Appid",APPID);
            params.put("token",jo1.getString("token"));
            String ret2 = HttpHelper.fetchUTF8StringByPost(listurl, params, null, 0, 0);
//            System.out.println(ret2);
            JSONObject jo2= JSON.parseObject(ret2);
            if(0==jo2.getInteger("state")){
                JSONArray jsonArray=JSON.parseArray(jo2.getString("data"));
                for(int i=0;i<jsonArray.size();i++){
                    JSONObject job = jsonArray.getJSONObject(i);
//                    System.out.println(i+"----"+job.toJSONString());
                    Employee emp=new Employee();
                    emp.setYgbh(job.getString("Ygbh"));
                    emp.setYgxm(job.getString("Ygxm"));
                    emp.setKsrq(job.getString("Ksrq"));
                    emp.setJsrq(job.getString("Jsrq"));
                    emp.setRsfw(job.getString("Rsfw"));
                    emp.setYgz(job.getString("Ygz"));
                    emp.setYgzz(job.getString("Ygzz"));
                    emp.setRszfw(job.getString("Rszfw"));
                    emp.setGzfw(job.getString("Gzfw"));
                    emp.setZzdwbm(job.getString("Zzdwbm"));
                    emp.setZzdwmc(job.getString("Zzdwmc"));
                    emp.setStatus(1);
                    list.add(emp);
                }
            }
        }

        //更新
        if(list.size()>0){
            orgDao.addorg(list);
        }
    }

}
