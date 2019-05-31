package leetcode;

/**
 * description
 *
 * @author cl10805
 * @date 2019-05-14 15:13
 */
public class QuickSortTest {

    public static void main(String[] args) {
        int[] array = new int[]{6, 4, 5, 2, 3, 1};
        quickSort(array, 0, array.length - 1);
    }

    /**
     * 对array快速排序
     *
     * @param array  待操作数组
     * @param low    低位
     * @param height 高位
     */
    public static void quickSort(int[] array, int low, int height) {
        // 记录划分后的基准元素所对应的位置
//        int temp;
        // 仅当区间长度大于1时才须排序
        if (low < height) {
            // 对array做划分
            int temp = quick(array, low, height);
            // 对左区间递归排序
            quickSort(array, low, temp - 1);
            // 对右区间递归排序
            quickSort(array, temp + 1, height);
        }
    }

    /**
     * 分治法划分算法
     *
     * @param array  待操作数组
     * @param low    划分中模块的起始地址
     * @param height 划分中模块的结束地址
     * @return 基准元素的位置下标
     */
    private static int quick(int[] array, int low, int height) {
        // 设置第一个数为基准元素
        int pivot = array[low];
        // 从右向左扫描，查找第1个小于pivot的元素
        while (low < height) {
            while (low < height && array[height] >= pivot) {
                height--;
            }
            // 表示找到了小于pivot的元素
            if (low < height) {
                // 交换后low执行+1操作
                swap(array, low++, height);
            }
            // 从左向右扫描，查找第1个大于pivot的元素
            while (low < height && array[low] <= pivot) {
                low++;
            }
            // 表示找到了大于pivot的元素
            if (low < height) {
                // 交换后heigh执行-1操作
                swap(array, low, height--);
            }
        }
        // 返回基准元素最终位置下标
        return height;
    }

    private static void swap(int[] nums, int src, int target) {
        int temp = nums[src];
        nums[src] = nums[target];
        nums[target] = temp;
    }
}
