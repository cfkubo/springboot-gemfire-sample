// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import com.example.gemfire.service.GemfireService;

// import java.util.concurrent.ExecutionException;

// @SpringBootTest
// class SpringGemfirePerformanceApplicationTests {

//     @Autowired
//     private GemfireService gemfireService;

//     @Test
//     void testPutAndReadPerformance() throws ExecutionException, InterruptedException {
//         gemfireService.performParallelPuts(100000);
//         gemfireService.performParallelReads(10000);
//     }
// }