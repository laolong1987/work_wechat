package com.web.dao;


import com.common.BaseDao;
import com.common.SearchTemplate;
import com.web.entity.Admin;
import com.web.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao extends BaseDao {

    public List<Admin> findUser(String username,String password) {
        StringBuffer sql = new StringBuffer();
        Map map=new HashMap<>();
        sql.append("select * from admin  where status=1 ");
        if (!"".equals(username)) {
            sql.append(" and username =:username");
            map.put("username",username);
        }
        if (!"".equals(password)) {
            sql.append(" and password =:password");
            map.put("password",password);
        }
        return super.findObjects(sql.toString(), map, Admin.class);
    }

}
