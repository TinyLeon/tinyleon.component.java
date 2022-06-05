package utility.host;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import io.leopard.javahost.JavaHost;

public class JvmDnsCacheUtil {
    public static void AddJVMDNSCache(String rawHostContent) {
        try {
            if (StringUtils.isEmpty(rawHostContent)) {
                return;
            }
            String[] rawHostPairArray = rawHostContent.split(",");
            Properties props = new Properties();
            String[] array = rawHostPairArray[0].split(":");
            props.put(array[0],array[1]);
            JavaHost.updateVirtualDns(props);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}