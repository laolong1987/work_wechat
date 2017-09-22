import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

/**
 * Created by admin on 2017/9/21.
 */
public class RedisClient {
    private Jedis jedis;


    @Test
    public  void  testClient(){


        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }

}
