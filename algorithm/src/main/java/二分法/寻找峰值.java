package 二分法;

/**
 * @author parry
 * @date 2021/07/16
 * https://leetcode-cn.com/problems/find-peak-element/
 */
public class 寻找峰值 {
    
    public static void main(String[] args) {
        int[] nums = {1,2};
        System.out.println(findPeakElement(nums));
    }
    
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (mid == 0) {
                if (nums[mid] > nums[mid + 1]) {
                    return 0;
                } else {
                    left = mid + 1;
                }
            } else if (mid == nums.length - 1) {
                if (nums[mid] > nums[mid - 1]) {
                    return nums.length - 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                    return mid;
                }
                if (nums[mid] < nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
