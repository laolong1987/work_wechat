package com.web.service;

import com.common.SearchTemplate;
import com.web.dao.UserDao;
import com.web.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {

    @Autowired
    UserDao userDao;


    public Admin findUser(String username, String password) {
        List<Admin> users = userDao.findUser(username,password);
        if (users.size() == 0) {
            return null;
        } else {
            return (Admin) users.get(0);
        }
    }
    public void saveUser(Admin user){
        userDao.save(user);
    }
}
