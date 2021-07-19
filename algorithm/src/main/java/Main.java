import java.util.Arrays;

/**
 * @author parry
 * @date 2021/07/09
 */
public class Main {
    
    public static void main(String[] args) {
        int[] nums = new int[]{3,1};
        System.out.println(new Main().search(nums, 1));
    }
    
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
//            左边数组有序
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
//                如果target在有序数组中
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        if (left == right && nums[left] == target) {
            return left;
        }
        return -1;
    }
    
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                if (mid != 0 && nums[mid - 1] == target) {
                    right = mid - 1;
                    continue;
                }
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    
}
