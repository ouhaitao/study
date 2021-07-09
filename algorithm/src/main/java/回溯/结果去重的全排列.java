package 回溯;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author parry
 * @date 2021/06/22
 *
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 * 去重两个思路：
 * 1. 使用全排列的流程得到的结果，最后去重
 * 2. 在回溯的过程中防止出现重复结果
 */
public class 结果去重的全排列 {
    
    /**
     * 重复结果出现的原因如下:
     * a1 a2 b
     * a2 a1 b
     * 第一个先使用a1 第二个先使用a2
     * 那么我们如果限制每次只能够 使用重复字符集合中的从左往右第一个未被使用的元素
     * 这里的重复字符集合为 a1 a2
     * 必须先使用a1才能够使用a2
     */
    public static void main(String[] args) {
        String s = "kzfxxx";
        System.out.println(Arrays.toString(new 结果去重的全排列().permutation(s)));
    }
    
    public String[] permutation(String s) {
        char[] a = s.toCharArray();
//        排序 将重复的元素放在一起
        Arrays.sort(a);
        List<String> list = new LinkedList<>();
        fun(a, new boolean[a.length], new StringBuilder(), list);
        return list.toArray(new String[0]);
    }
    
    public void fun(char[] a, boolean[] used, StringBuilder sb, List<String> res) {
        if (sb.length() == a.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < a.length; i++) {
//            去重
            if (i > 0 && !used[i - 1] && a[i - 1] == a[i]) {
                continue;
            }
            if (!used[i]) {
                sb.append(a[i]);
                used[i] = true;
                fun(a, used, sb, res);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
}
