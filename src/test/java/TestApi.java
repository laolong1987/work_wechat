

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.HttpHelper;
import com.web.component.wechat.api.WechatComponent;
import com.web.model.WaitProcessModel;
import com.web.service.OrgService;
import com.web.service.WorkFromService;
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

    @Test
    public void getWaitProcessNotice() {
        //List<WaitProcessModel> res = approvalService.getWaitProcessNotice("220238",null,null);
        List<WaitProcessModel> res = approvalService.getWaitProcessNotice("220345", "0", "100", "waitProcess");

        System.out.println(res);
    }

    @Test
    public void GetFormInstance() {
        String res = approvalService.getFormInstance("321", "1538");
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

    public void getSelfProcessedNotice() {
        //List<WaitProcessModel> res = approvalService.getWaitProcessNotice("220238",null,null);
        List<WaitProcessModel> res = approvalService.getSelfProcessedNotice("220238", "0", "10", "2", "349");

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
        System.out.println(resultJson);
    }

    @Test
    public void getEmployeeUsers() {
        String resultJson = approvalService.getEmployeeUsers();
        System.out.println(resultJson);
    }

}
