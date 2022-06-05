package utility.host;

import org.apache.commons.lang3.StringUtils;

public class HostUtil {
    public static void setHost(String host) {
        if (StringUtils.isEmpty(host)) { 
            JvmDnsCacheUtil.AddJVMDNSCache(host);
        } else {

        }
    }

    public static void main(String[] args) {
        setHost("www.baidu.com:127.0.0.1");   
    }
}
