import java.text.ParseException;

import com.utils.DateUtil;
import org.junit.Test;

/**
 * Created by admin on 2017/9/16.
 */
public class DateUtilTest {

    @Test
    public void formatTest(){


          String date =  DateUtil.formatUTCDate("2017-09-03T21:17:00.9200000+08:00","yyyy-MM-dd HH:mm:ss");
            System.out.println(date);

    }

}
