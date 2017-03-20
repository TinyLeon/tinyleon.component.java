package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LeonChen
 */
public class RegxHelper {
    public static String coverMobile(String mobile){
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher m = p.matcher(mobile);
        if (m.matches()) {
            return mobile.substring(0, 7) + "****";
        } else {
            return mobile;
        }
    }
}
