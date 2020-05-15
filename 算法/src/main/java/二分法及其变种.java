/**
 * @author parry
 * @date 2020/04/25
 */
public class 二分法及其变种 {
    
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5, 5, 5, 7, 8, 9, 0};
        System.out.println(search(a, 7));
        System.out.println(searchFirstValue(a, 5));
        System.out.println(searchLastValue(a, 5));
        System.out.println(searchFirstBiggerOrEqualValue(a, 5));
        System.out.println(searchFirstBiggerOrEqualValue(a, 6));
        System.out.println(searchLastLessOrEqualValue(a, 5));
        System.out.println(searchLastLessOrEqualValue(a, 6));
    }
    
    
    /**
     * 正常二分法
     */
    private static int search(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value){
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    /**
     * 查找第一个等于给定值的元素(5)，即a[3]
     */
    private static int searchFirstValue(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
//                不同点
                if ((mid == 0) || (a[mid - 1] != value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    
    /**
     * 查找最后一个等于给定值的元素(5)，即a[5]
     */
    private static int searchLastValue(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
//                不同点
                if ((mid == a.length - 1) || (a[mid + 1] != value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
    
    /**
     * 查找第一个大于等于给定值的元素(5)，即a[3]
     * 查找第一个大于等于给定值的元素(6)，即a[6]
     */
    private static int searchFirstBiggerOrEqualValue(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
    
    /**
     * 查找最后一个小于等于给定值的元素(5)，即a[5]
     * 查找最后一个小于等于给定值的元素(6)，即a[5]
     */
    private static int searchLastLessOrEqualValue(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == a.length - 1) || (a[mid + 1] > value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
