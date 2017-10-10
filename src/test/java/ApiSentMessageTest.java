import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.utils.HttpUtil;
import org.junit.Test;

/**
 * Created by admin on 2017/8/23.
 */
public class ApiSentMessageTest {


    public void sent(){
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

        String response = null;
        try {
            response = HttpUtil.httpPostWithJSON("http://127.0.0.1/wechat/message/sent",data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

}
