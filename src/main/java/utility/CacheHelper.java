package utility;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheHelper {
    private static Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(10)
            .expireAfterWrite(10, TimeUnit.MINUTES).build();

    public static void put(String key, String value) {
        cache.put(key, value);
    }

    public static String get(String key) {
        return cache.getIfPresent(key);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 11; i++) {
            CacheHelper.put("cacheKey" + i, String.valueOf(i));
        }
        for (int i = 0; i < 11; i++) {
            String val = CacheHelper.get("cacheKey" + i);
            System.out.println(val);
        }
    }
}
