package mock;

import com.intuit.karate.PerfContext;
import com.intuit.karate.core.MockServer;

import java.util.Collections;
import java.util.Map;

/**
 *
 * @author pthomas3
 */
public class MockUtils {
    
    public static void main(String[] args) {
        startServer(8080);
    }
    
    public static void startServer(int port) {
        MockServer server = MockServer.feature("classpath:mock/mock.feature").http(port).build();
        System.setProperty("mock.port", server.getPort() + "");        
    }

    public static Map<String, Object> myRpc(Map<String, Object> map, PerfContext context) {
        long startTime = System.currentTimeMillis();
        // this is just an example, you can put any kind of code here
        int sleepTime = (Integer) map.get("sleep");
        try {
            Thread.sleep(sleepTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        // and here is where you send the performance data to the reporting engine
        context.capturePerfEvent("myRpc-" + sleepTime, startTime, endTime);
        return Collections.singletonMap("success", true);
    }
    
}
