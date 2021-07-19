package 二分法;

import java.util.Arrays;

/**
 * @author parry
 * @date 2021/07/16
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {
    
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));
    }
    
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (nums.length == 0) {
            return res;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (target < nums[mid]) {
                high = mid - 1;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                int i = mid - 1, j = mid + 1;
                while ((i >= 0 && nums[i] == nums[mid]) || (j < nums.length && nums[j] == nums[mid])) {
                    if (i >= 0 && nums[i] == nums[mid]) {
                        i--;
                    }
                    if (j < nums.length && nums[j] == nums[mid]) {
                        j++;
                    }
                }
                res[0] = i + 1;
                res[1] = j - 1;
                return res;
            }
        }
        return res;
    }
}
