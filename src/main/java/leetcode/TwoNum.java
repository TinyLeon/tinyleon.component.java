package leetcode;

public class TwoNum {
    public static void main(String[] args) {
        String res = method("0", "1234");
        System.out.println(res);
    }


    private static String method(String a, String b) {
        if (compare(a, b)) {
            return minus(a, b);
        } else {
            return "-" + minus(b, a);
        }
    }

    private static String minus(String aa, String bb) {
        int aLen = aa.length()-1, bLen = bb.length()-1;
        boolean flag = false;
        StringBuilder res = new StringBuilder();
        while (bLen >= 0 || aLen>=0) {

            char a = flag ? (char) (aa.charAt(aLen) - '1') : aa.charAt(aLen);
            char b = bLen>=0? bb.charAt(bLen): '0';
            if (a < b) {
                flag = true;
                a = (char) (a + 10);
            } else {
                flag = false;
            }
            res.append(a - b);
            bLen--;
            aLen--;
        }
        return res.reverse().toString();
    }

    private static boolean compare(String a, String b) {
        if (a.length() > b.length()) {
            return true;
        } else if (a.length() < b.length()) {
            return false;
        } else {
            int i = 0;
            while (i < a.length()) {
                char aChar = a.charAt(i);
                char bChar = b.charAt(i);
                if (aChar == bChar) {
                    i++;
                } else {
                    return aChar > bChar;
                }
            }
        }
        return true;
    }
}
