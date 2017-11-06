import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.utils.HttpUtil;
import org.junit.Test;

/**
 * Created by admin on 2017/8/23.
 */
public class ApiSentMessageTest {



    public void sentMpNews() {
        JSONObject data = new JSONObject();

        data.put("touser", "YangShuKai");
        data.put("toparty", "");
        data.put("totag", "");
        data.put("msgtype", "mpnews");
        data.put("agentid", 1000003);

        JSONObject mpnews = new JSONObject();
        List<JSONObject> articles = new ArrayList<>();
        JSONObject article = new JSONObject();
        article.put("title", "这是一条测试");
        article.put("thumb_media_id", "2FM1myV0LZq9U6tnD9xNUkGM8KYpj4RjOmbChXHDmKtfIWueo4SratjNUjhiIqdCM");
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
            response = HttpUtil.httpPostWithJSON("http://127.0.0.1:8080/wechat/message/sent", data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    public void sentNews() {
        JSONObject data = new JSONObject();

        data.put("touser", "YangShuKai");
        data.put("toparty", "");
        data.put("totag", "");
        data.put("msgtype", "news");
        data.put("agentid", 1000003);

        JSONObject news = new JSONObject();
        List<JSONObject> articles = new ArrayList<>();
        JSONObject article = new JSONObject();
        article.put("title", "这是一条测试");
        article.put("picurl", "http://cache.shchengdian.com/images/201702/theme-201702-2.png");
        article.put("description", "今年中秋节公司有豪礼相送");
        article.put("url", "http://ent.163.com/17/1104/08/D2CQGEIC00038FO9.html");

        articles.add(article);
        news.put("articles", articles);
        data.put("news", news);

        String response = null;
        try {
            response = HttpUtil.httpPostWithJSON("http://127.0.0.1:8080/wechat/message/sent", data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    public void sentTextCard() {
        JSONObject data = new JSONObject();

        data.put("touser", "YangShuKai");
        data.put("toparty", "");
        data.put("totag", "");
        data.put("msgtype", "textcard");
        data.put("agentid", 1000003);


        JSONObject textcard = new JSONObject();
        textcard.put("title", "领奖通知");
        textcard.put("description", "<div class=\\\"gray\\\">2016年9月26日</div> <div class=\\\"normal\\\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\\\"highlight\\\">请于2016年10月10日前联系行政同事领取</div>");
        textcard.put("url", "http://ent.163.com/17/1104/08/D2CQGEIC00038FO9.html");

        data.put("textcard", textcard);


        String response = null;
        try {
            response = HttpUtil.httpPostWithJSON("http://127.0.0.1:8080/wechat/message/sent?secret=XseXOYGJkud2ed7KTsY9z5tJVxO74PWNdGsC6d", data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }


}
