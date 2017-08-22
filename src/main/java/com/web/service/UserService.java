package com.web.service;

import com.common.SearchTemplate;
import com.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {

    @Autowired
    UserDao userDao;

}
