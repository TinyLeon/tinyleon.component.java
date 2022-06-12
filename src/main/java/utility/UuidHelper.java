package utility;

import java.net.InetAddress;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UuidHelper {
    private static final Random RANDOM =new Random();

    private static String localHost;

    private static Logger logger = Logger.getLogger("UuidHelper");

    static {
        try{
            localHost = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "get localhost error");
        }
    }

    public static String getUniquePartId(){
        StringBuilder sb = new StringBuilder();
        sb.append(getHostIpStr());
        sb.append(getAtomicLongValue());
        sb.append(System.currentTimeMillis());

        return sb.toString();
    }

    //ATOMIC_LONG活动范围
    private static final int MIN_VALUE = 1000;
    private static final int MAX_VALUE = 9999;
    private static final AtomicLong ATOMIC_LONG = new AtomicLong(MIN_VALUE);

    /**
     * 获取当前的Long值，重复循环
     * @return
     */
    private static String getAtomicLongValue(){
        ATOMIC_LONG.compareAndSet(MAX_VALUE, MIN_VALUE);
        return ATOMIC_LONG.addAndGet(1) + "";
    }

    //Random活动范围
    private static final int RANDOM_MIN_VALUE = 100;
    private static final int RANDOM_MAX_VALUE = 999;

    /**
     * 获取IP地址的12位数字，缺省部分用0补齐
     * @return
     */
    private static String getHostIpStr() {
        String hostIp;
        try {
            if (localHost==null) {
                localHost = InetAddress.getLocalHost().getHostAddress();
            }
            hostIp = localHost!=null?localHost:"000.000.000.000";
            String[] ips = hostIp.split("\\.");
            StringBuilder sb = new StringBuilder();
            for(String item : ips) {
                sb.append(alignRight(item, 3, "0"));
            }
            hostIp = sb.toString();
        }catch (Exception e) {
            String randomStr = RANDOM.nextInt(RANDOM_MAX_VALUE) % (RANDOM_MAX_VALUE - RANDOM_MIN_VALUE) + RANDOM_MIN_VALUE + "";
            hostIp = randomStr + randomStr + randomStr + randomStr;
        }
        return hostIp;
    }

    private static String alignRight(String str, int size, String padStr) {
        if (str ==null) {
            return null;
        }
        if((padStr==null)||(padStr.length()==0)) {
            padStr = " ";
        }
        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size-strLen;

        if (pads ==padLen) {
            return padStr.concat(str);
        } else if (pads<padLen){
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();
            for(int i=0;i<pads;i++){
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

    public static void main(String[] args) {
        System.out.println(getUniquePartId());
    }
}
