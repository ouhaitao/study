package 一些问题;

/**
 * @author parry
 * @date 2020/11/19
 * 把一个数组最开始的若干个元素（可以为0）搬到数组的末尾，我们称之为数组的旋转
 */
public class 旋转数组的最小数字 {
    
    /**
     * 把一个递增排序的数组变成一个旋转数组，然后求数组最小的元素
     */
    public static void main(String[] args) {
        int[] a = {3, 4, 5, 1, 2};
        System.out.println(fun(a));
        a = new int[]{1, 0, 1, 1, 1};
        System.out.println(fun(a));
        a = new int[]{1, 1, 1, 0, 1};
        System.out.println(fun(a));
        a = new int[]{1, 1, 1, 1, 1};
        System.out.println(fun(a));
    }
    
    /**
     * 旋转数组分为两部分 前面若干个数字时候一个递增序列，后面若干个数字是一个递增序列
     * 最小值一定在后面的递增序列中
     */
    private static int fun(int[] a) {
//        如果只把数组最开始的0个元素搬到数组末尾，则最小的依然为a[0]
        if (a[0] < a[a.length - 1]) {
            return a[0];
        }

//        始终指向前面的递增序列
        int left = 0;
//        始终指向后面的递增序列
        int right = a.length - 1;
        while (left != right - 1) {
            int mid = (left + right) >> 1;
            if (a[mid] == a[left] && a[mid] == a[right]) {
                System.out.println("无法确定属于哪个递增序列,使用顺序查找");
                return -1;
            }
//            a[mid]大于a[left]说明a[mid]属于前面的递增序列
            if (a[mid] > a[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return a[right];
    }
}
