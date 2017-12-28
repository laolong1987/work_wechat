

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.*;
import com.common.HttpHelper;
import com.web.component.wechat.WechatConfig;
import com.web.component.wechat.api.WechatComponent;
import com.web.model.WaitProcessModel;
import com.web.service.*;
import com.web.service.ws.ApprovalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Created by sukey on 2016/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring-mvc.xml",
    "classpath:spring-common.xml"})
public class TestApi {

    @Autowired
    WechatComponent wechatComponent;

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private WorkFromService workFromService;

    @Autowired
    private OrgService orgService;

    @Autowired
    private UserToWechatService userToWechatService;

    @Autowired
    WechatConfig wechatConfig;

    public void testMpNews() {
        JSONObject data = new JSONObject();

        data.put("touser", "YangShuKai");
        data.put("toparty", "");
        data.put("totag", "");
        data.put("msgtype", "mpnews");
        data.put("agentid", 1000002);

        JSONObject mpnews = new JSONObject();
        List<JSONObject> articles = new ArrayList<>();
        JSONObject article = new JSONObject();
        article.put("title", "这是一条测试");
        article.put("thumb_media_id", "2FP3MaXkftVyiyG4f4eqmhgLusEV3mzlXF4-BJ8qyvA3isXv_YSItQedR9eo2k6uh");
        article.put("author", "树先生");
        article.put("content", "测试，收到消息请回答，收到消息请回答，完毕！");
        article.put("content_source_url", "");
        article.put("digest", "这是一个描述");

        articles.add(article);
        mpnews.put("articles", articles);
        data.put("mpnews", mpnews);
        data.put("safe", 0);

        try {
            String response = wechatComponent.sentMpNews(data.toJSONString());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void testTextMessage() {
        JSONObject data = new JSONObject();

        data.put("touser", "YangShuKai");
        data.put("toparty", "");
        data.put("totag", "");
        data.put("msgtype", "text");
        data.put("agentid", 1000002);

        JSONObject text = new JSONObject();
        text.put("content", "你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。");
        data.put("text", text);
        data.put("safe", 0);

        try {
            String response = wechatComponent.sentMpNews(data.toJSONString());
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void getWaitProcessNotice() {
        //List<WaitProcessModel> res = approvalService.getWaitProcessNotice("220238",null,null);
        List<WaitProcessModel> res = approvalService.getWaitProcessNotice("220238", "0", "500", "processed");

        System.out.println(res);
    }

    @Test
    public void GetFormInstance() {
        String res = approvalService.getFormInstance("358", "1342");
        System.out.println(res);
    }


    public void getMayProcessItems() {
//        List<WaitProcessModel> list = approvalService.getWaitProcessNotice("220238", "0", "100", "waitProcess");
//        for (WaitProcessModel model : list) {
//            System.out.println(model.getSubject());
//            String res = approvalService.getMayProcessItems(String.valueOf(model.getTemplateId()), String.valueOf(model.getDataId()), "220238");
//            System.out.println(res);
//        }
        String res = approvalService.getMayProcessItems("326", "2245", "220238");
        System.out.println(res);

    }

    public void getGetNoticeList() {

        approvalService.getNoticeList("321", "1537");

    }


    public void OrgServiceTest() {
        String APPID = "0170822001";
        String listurl = "http://d.bm21.com.cn:20006/employee/list";
        Map params = new HashMap();
        params.put("Appid", APPID);
        params.put("Username", "220238");
        params.put("Passwd", "123456");
        String ret2 = HttpHelper.fetchUTF8StringByPost(listurl, params, null, 0, 0);

        System.out.println(ret2);
    }

    public void getFormSchemaTest() {
        String res = approvalService.getFormSchema("321");
        System.out.println(res);
    }

    @Test
    public void getSelfProcessedNotice() {
        //List<WaitProcessModel> res = approvalService.getWaitProcessNotice("220238",null,null);
        List<WaitProcessModel> res = approvalService.getSelfProcessedNotice("220184", "0", "50", "2", "321");

        System.out.println(res);
    }

    @Test
    public void RaiseWorkflow() {
        String res = approvalService.raiseWorkflow("FormCanceled", "326", "2245", "部门经理", "admin", "请重新提交材料", "220238");
        System.out.println(res);

    }

    @Test
    public void createFormInstanceTest() {

        JSONObject data = new JSONObject();

        data.put("Yysx", "我就改动一下");
        data.put("chargeleader", "疏博,邓征,加上无名");
        data.put("ID", 2245);

        JSONObject json = new JSONObject();
        json.put("FormType", 326);
        json.put("data", data);
        String FormConfigID = workFromService.CreateFormInstance(json.toJSONString());
        JSONObject resultJson = JSON.parseObject(FormConfigID);
        System.out.println(resultJson);
    }

    @Test
    public void getDepartmentsTest() {
        String resultJson = approvalService.getDepartments();
        JSONObject departmentJson = JSONObject.parseObject(resultJson);
        JSONArray deptJsonArr = departmentJson.getJSONArray("data");
        Map<Integer, Integer> childIdAsKey = new HashMap<>();
        Map<Integer, JSONObject> deptMap = new HashMap<>();
        Set<Integer> parentIdSet = new HashSet<>();

        Set<Integer> childrenDept = new HashSet<>();
        for (int i = 0; i < deptJsonArr.size(); i++) {
            JSONObject obj = deptJsonArr.getJSONObject(i);
            Integer key = obj.getInteger("DeptID");
            if (parentIdSet.contains(key))
                continue;
            childrenDept.add(key);
        }

        String empStr = approvalService.getEmployeeUsers();
        JSONObject empJson = JSONObject.parseObject(empStr);
        Map<Integer, JSONArray> userList = new HashMap<>();
        JSONArray userJsonArr = empJson.getJSONArray("data");
        for (int i = 0; i < userJsonArr.size(); i++) {
            JSONObject obj = userJsonArr.getJSONObject(i);
            obj.put("realName", obj.getString("RealName"));
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
            JSONObject obj = deptJsonArr.getJSONObject(i);
            childIdAsKey.put(obj.getInteger("DeptID"), obj.getInteger("UpDeptID"));
            deptMap.put(obj.getInteger("DeptID"), obj);
            parentIdSet.add(obj.getInteger("UpDeptID"));
        }
        Map<Integer, JSONArray> childList = new HashMap<>();

        for (Integer key : userList.keySet()) {
            JSONObject dept = deptMap.get(key);
            JSONObject obj = new JSONObject();
            Integer parentId = dept.getInteger("UpDeptID");
            obj.put("id", key);
            obj.put("name", dept.getString("DeptName"));
            obj.put("parentId", parentId);
            obj.put("childList", new ArrayList<>());
            obj.put("userList", userList.get(key));

            if (childList.containsKey(parentId)) {
                childList.get(parentId).add(obj);
            } else {
                JSONArray array = new JSONArray();
                array.add(obj);
                childList.put(parentId, array);
            }

        }
        Map<Integer, JSONArray> treeDeptMap = createJsonTree(childList, deptMap);
//        JSONArray = JSONArray
        System.out.println();

    }

    public Map<Integer, JSONArray> createJsonTree(Map<Integer, JSONArray> dataMap, Map<Integer, JSONObject> deptMap) {
        Map<Integer, JSONArray> childList = new HashMap<>();
        for (Integer key : dataMap.keySet()) {
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
            if (childList.containsKey(parentId)) {
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

    @Test
    public void getEmployeeUsers() {
        String resultJson = approvalService.getEmployeeUsers();
        System.out.println(resultJson);
    }

    @Test
    public void RegexTest() {
        String str = "{\"attachments\":\"&&left;a href='javascript:ShowAtt(&puot;/UploadFiles/2017/2300/MB-20141016143700/4ea75645-9b7c-466e-a4d5-b9b41515960e/1副本.jpg&puot;)'&&right;1副本.jpg&&left;/a&&right;  (3483171 Byte);&&left;a href='javascript:ShowAtt(&puot;/UploadFiles/2017/2300/MB-20141016143700/2f1ffbad-37f7-4874-9e24-1eeb47e042f8/2副本.jpg&puot;)'&&right;2副本.jpg&&left;/a&&right;  (2938135 Byte)\"}";
        String rgex = "\\(&puot;(.*?)&puot;\\)";

        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(str);
        String match = "";
        while (m.find()) {
            match = m.group(1);
            String name = match.substring(match.lastIndexOf("/"));
            System.out.println(name);
            System.out.println(match);
        }


    }

    @Test
    public void getDepartTest() {
        String resultJson = approvalService.getDepartments();
        JSONObject departmentJson = JSONObject.parseObject(resultJson);
        JSONArray deptJsonArr = departmentJson.getJSONArray("data");
        Map<Integer, Integer> childIdAsKey = new HashMap<>();
        Map<Integer, JSONObject> deptMap = new HashMap<>();
        Set<Integer> parentIdSet = new HashSet<>();

        Set<Integer> childrenDept = new HashSet<>();
        for (int i = 0; i < deptJsonArr.size(); i++) {
            JSONObject obj = deptJsonArr.getJSONObject(i);
            System.out.println(obj.toJSONString());
        }


    }


    public void createDepartTest() {

        String res = userToWechatService.createDepartment();
        System.out.println(res);
    }

    public void createUserTest() {

        boolean res = userToWechatService.employeeToWechat();
        System.out.println(res);
    }


    @Test
    public void getPropertyValue(){
        System.out.println(wechatConfig.getApprovalAgentId());
    }



}
