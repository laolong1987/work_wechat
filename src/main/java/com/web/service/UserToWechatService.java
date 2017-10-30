package com.web.service;

import java.util.List;

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
    OrgDao orgDao;

//    public boolean employeeToWechat(){
//       List<Employee> employeeList = orgDao.findEmployee();
//
//    }

}
