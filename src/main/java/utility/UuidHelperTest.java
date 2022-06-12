package utility;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

public class UuidHelperTest {
    @Test
    public void batchGet(){
        for (int i=0;i<1000;i++) {
            UuidHelper.getUniquePartId();
        }

        long start = System.currentTimeMillis();
        Set<String> set = new HashSet<>(1000000);
        for(int i=0;i<1000000; i++){
            set.add(UuidHelper.getUniquePartId());
        }
        long end = System.currentTimeMillis();
        System.out.println("cost"+(end - start) + "ms");
        Assert.assertTrue(set.size() == 1000000);
    }

    @Test
    public void testConcurrentGet() throws InterruptedException {
        for(int i=0;i<1000;i++) {
            UuidHelper.getUniquePartId();
        }
        int concur = 10;
        ExecutorService service = Executors.newFixedThreadPool(concur);
        for(int i=0;i<concur;i++) {
            service.submit(new Thread());
        }
        CountDownLatch countDownLatch = new CountDownLatch(concur);
        long start = System.currentTimeMillis();

        Map<String, String> set = new ConcurrentHashMap<>(10000);
        for(int i=0;i<concur;i++){
            service.submit(()->{
                for(int j=0;j<100000; j++){
                    set.put(UuidHelper.getUniquePartId(), "1");
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end =System.currentTimeMillis();
        System.out.println("cost" + (end - start) + "ms");
        TimeUnit.SECONDS.sleep(10);
        Assert.assertTrue(set.size() == 1000000);
        service.shutdown();
    }
}
