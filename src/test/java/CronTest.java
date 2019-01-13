import com.byteland.model.Cron;
import com.byteland.model.DefaultCron;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class CronTest {

    public static void main(String[] args) throws IOException {
        CronTest cronTest = new CronTest();
        Gson gson = new Gson();
        String json = IOUtils.toString(cronTest.getClass().getResource("/json/sample_cron.json"), Charset.defaultCharset().name());
        DefaultCron defaultCron = gson.fromJson(json, DefaultCron.class);
        System.out.println("default " + defaultCron.getCron().getHours().getOn().getClass());
    }

}
