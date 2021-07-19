package 二分法;

/**
 * @author parry
 * @date 2021/07/16
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class 寻找旋转排序数组中的最小值 {
    
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(findMin(nums));
        nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(findMin(nums));
        nums = new int[]{5, 4, 3, 2, 1};
        System.out.println(findMin(nums));
        nums = new int[]{5, 1, 2, 3, 4};
        System.out.println(findMin(nums));
    }
    
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left != right) {
            int mid = (left + right) >> 1;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
