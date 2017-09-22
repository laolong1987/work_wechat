package com.web.service;

import com.common.SearchTemplate;
import com.utils.ConvertUtil;
import com.web.dao.UserDao;
import com.web.entity.Admin;
import com.web.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {

    @Autowired
    UserDao userDao;


    public Admin findUser(String username, String password) {
        List<Admin> users = userDao.findUser(username, password);
        if (users.size() == 0) {
            return null;
        } else {
            return (Admin) users.get(0);
        }
    }

    public void saveUser(Admin user) {
        userDao.save(user);
    }

    public void addUser(Map map) {
        Integer id= ConvertUtil.safeToInteger(map.get("id"),0);
        String ygbh=ConvertUtil.safeToString(map.get("Ygbh"),"");
        String ygxm=ConvertUtil.safeToString(map.get("Ygxm"),"");
        if (0 != id) {
            List<Admin> list = userDao.findUser(ygbh, "");
            if (list.size() > 0) {
                Admin admin = list.get(0);
                admin.setStatus(2);
                admin.setUpdatetime(new Date());
                userDao.save(admin);
            } else {
                Admin admin = new Admin();
                admin.setStatus(2);
                admin.setCreatetime(new Date());
                admin.setPassword("123456");
                admin.setUsername(ygbh);
                admin.setName(ygxm);
                userDao.save(admin);
            }
        }
    }

    public void removeUser(Integer aid) {
        Admin admin = (Admin) userDao.getObjectById(aid, Admin.class);
        admin.setStatus(0);
        admin.setUpdatetime(new Date());
        userDao.save(admin);

    }
}
