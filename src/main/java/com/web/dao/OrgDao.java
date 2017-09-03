package com.web.dao;


import com.common.BaseDao;
import com.common.SearchTemplate;
import com.utils.ConvertUtil;
import com.utils.DateUtil;
import com.web.entity.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoyang on 16/2/29.
 */
@Repository
public class OrgDao extends BaseDao{

    /**
     * 查询
     * @param map
     * @return
     */
    public SearchTemplate searchEmployee(Map map){
        StringBuffer sql =new StringBuffer();
        Map p=new HashMap();
        sql.append("select  * from employee ");
        return super.search(sql.toString(),map);
    }



    @Transactional
    public void addorg(List<Employee> list) {
        if (list.size()>0) {
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            try {
                System.out.println(DateUtil.getCurrentTime()+"----beginTransaction -----");
                Transaction ts = session.beginTransaction();
                //先清空表格数据
                Query query = session.createSQLQuery("DELETE FROM employee");
                query.executeUpdate();

                //把list插入数据库中
                Query query2 = session.createSQLQuery(getinsetorgsql(list));
                query2.executeUpdate();

                ts.commit();
                session.close();
                System.out.println(DateUtil.getCurrentTime()+"----endTransaction -----");
            } catch (HibernateException e) {
                System.out.println(DateUtil.getCurrentTime()+"----errorTransaction-----"+e);
                throw e;
            } catch (Exception e){

            }finally {
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
    }
    @Transactional
    public String getinsetorgsql(List<Employee> list){
        StringBuilder sql=new StringBuilder();
        sql.append("insert into ").append("employee");
        sql.append(" (Ygbh,Ygxm,Ksrq,Jsrq,Rsfw," +
                "Ygz,Ygzz,Rszfw,Gzfw,Zzdwbm,Zzdwmc," +
                "createtime,updatetime,status)");
        sql.append(" values ");
        for (Employee employee:list) {
            sql.append("(");
            sql.append("'").append(ConvertUtil.safeToString(employee.getYgbh(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getYgxm(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getKsrq(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getJsrq(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getRsfw(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getYgz(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getYgzz(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getRszfw(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getGzfw(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getZzdwbm(),"")).append("',");
            sql.append("'").append(ConvertUtil.safeToString(employee.getZzdwmc(),"")).append("',");
            sql.append("getdate(),");
            sql.append("getdate(),");
            sql.append("'").append(ConvertUtil.safeToString(employee.getStatus(),"")).append("'");
            sql.append("),");

//            break;
        }
        System.out.println(sql);
        return sql.toString().substring(0,sql.length() - 1);
    }
}
