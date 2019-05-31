package leetcode;

/**
 * description
 *
 * @author cl10805
 * @date 2019/1/29 16:45
 */
public class LetterDigitHelper {
    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }


    public static boolean isLetter(String str) {
        String regex = "^[a-zA-Z]+$";//其他需要，直接修改正则表达式就好
        return str.matches(regex);
    }


    public static void main(String[] args) {
        String test = "";
        System.out.println(isLetter(test));
    }

}
