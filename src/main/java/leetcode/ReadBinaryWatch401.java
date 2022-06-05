package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author cl10805
 * @date 2019/1/17 16:36
 */
public class ReadBinaryWatch401 {
    public static void main(String[] args) {
        List<String> res = readBinaryWatch(1);
        System.out.println(res);
    }

    public static List<String> readBinaryWatch(int turnedOn) {
        if (turnedOn <0 || turnedOn >10) {
            return null;
        }
        List<String> res = new ArrayList<>();
        for (int i=0;i<12;i++) {
            for (int j=0;j<60;j++) {
                if (count(i)+ count(j) ==turnedOn) {
                    res.add(i+":"+ (j<10?"0"+j : j));
                }
            }
        }
        return res;
    }

    private static int count(int n){
        int res =0;
        while (n>0) {
            n=n&(n-1);
            res++;
        }
        return res;
    }
}