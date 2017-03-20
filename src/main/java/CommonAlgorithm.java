/**
 * Created by cl10805 on 2017/3/20.
 */
public class CommonAlgorithm {
    public static void main(String[] args) {
        getnarcissiFew();
        printImage();
    }

    /**
     * 获取水仙花数
     */
    private static void getnarcissiFew() {
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 100; i < 1000; i++) {
            a = i / 100;
            b = i % 100 / 10;
            c = i % 100 % 10;
            if ((mi3(a) + mi3(b) + mi3(c)) == i) {
                System.out.println(i);
            }
        }
    }

    private static int mi3(int number) {
        return (int) Math.pow(number, 3);
    }

    /**
     * 打印图形，类似于： * * * * * * * * * * * * * * *
     */
    private static void printImage() {
        int colMax = 9;
        int rowMax = 5;
        for (int i = 1, z = 0; i <= rowMax; i++, z++) {
            for (int j = 1; j <= colMax; j++) {
                if (colMax / 2 - z + 1 <= j && j <= colMax / 2 + z + 1) {// 获取打印*的范围
                    if ((i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0)) {// 如果行为奇数，则奇数为打印*，若行为偶数，在偶数位打印
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
