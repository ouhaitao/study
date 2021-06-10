package 一些问题;

/**
 * @author parry
 * @date 2020/02/20
 * 已知根号2约等于1.414，求精确到0.0000000001的值
 */
public class 有精确度要求求平方根 {
    
    public static void main(String[] args) {
//        能够认为确定一个较为精确的值
        System.out.println(sqrt(1.413, 1.415, 2,0.0000000001));
        System.out.println(sqrt(1.4, 1.5, 2, 0.0000000001));
//        不进行精确根号n的大小在0-n之间
        System.out.println(sqrt(0, 2, 2, 0.0000000001));
        System.out.println(sqrt(0, 4, 4, 0.0000000001));
    }
    
    /**
     * 二分查找求平方根
     */
    private static double sqrt(double low, double high, int n, double precision) {
        double mid = (high + low) / 2;
        if (mid < 0) {
            throw new IllegalArgumentException("high < low");
        }
        while (high - low > precision) {
            double tmp = mid * mid;
            if (tmp == n) {
                break;
            }else if (tmp > n) {
                high = mid;
            }else {
                low = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }
}
