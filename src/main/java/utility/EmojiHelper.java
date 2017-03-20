package utility;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LeonChen
 */
public class EmojiHelper {
    /**
     * @param str 待转换字符串
     * @return 转换后字符串
     * @throws UnsupportedEncodingException exception
     * @Description 将字符串中的emoji表情转换成可以在utf-8字符集数据库中保存的格式（表情占4个字节，需要utf8mb4字符集）
     */
    public static String emojiConvert1(String str) {
        String res = "";
        try {
            String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {

                matcher.appendReplacement(
                        sb,
                        "[["
                                + URLEncoder.encode(matcher.group(1),
                                "UTF-8") + "]]");

            }
            matcher.appendTail(sb);
            res = sb.toString();
            return res;
        } catch (UnsupportedEncodingException e) {
            return res;
        }
    }

    /**
     * @param str 转换后的字符串
     * @return 转换前的字符串
     * @throws UnsupportedEncodingException exception
     * @Description 还原utf8数据库中保存的含转换后emoji表情的字符串
     */
    public static String emojiRecovery2(String str) {
        String res = "";
        try {
            String patternString = "\\[\\[(.*?)\\]\\]";

            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(str);

            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {

                matcher.appendReplacement(sb,
                        URLDecoder.decode(matcher.group(1), "UTF-8"));

            }
            matcher.appendTail(sb);
            res = sb.toString();
            return res;
        } catch (UnsupportedEncodingException e) {
            return res;
        }
    }

    /**
     * 移除文本中的emoji表情
     *
     * @param str
     * @return
     */
    public static String emojiRemove(String str) {
        String res = "";
        try {
            String patternString = "\\[\\[(.*?)\\]\\]";

            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(str);

            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {

                matcher.appendReplacement(sb, "");
            }
            matcher.appendTail(sb);
            res = sb.toString();
            if (res == null || res.isEmpty())
                return ".";
            return res;
        } catch (Exception e) {
            if (res == null || res.isEmpty())
                return ".";
            return res;
        }
    }
}
