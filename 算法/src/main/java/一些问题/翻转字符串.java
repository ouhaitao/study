package 一些问题;

/**
 * @author parry
 * @date 2020/12/14
 */
public class 翻转字符串 {
    
    public static void main(String[] args) {
        String str = "  hello world!  ";
        System.out.println(fun1(str));
    }
    
    /**
     *  https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
     *  由于需要删除多余的空格字符，这里多了个O(n)的空间复杂度
     *  一般来说不会要求把多余的空格字符删掉
     */
    private static String fun(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        input = input.trim();
//        剔除多余的空格字符
        String[] split = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String tmp : split) {
            if (!tmp.equals("")) {
                sb.append(tmp).append(" ");
            }
        }
//        等于0说明字符串全是空格
        if (sb.length() == 0) {
            return "";
        }
        char[] chars = sb.deleteCharAt(sb.length() - 1).toString().toCharArray();
//        全字符翻转
        reverse(chars, 0, chars.length - 1);
        int start = 0, end = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                end++;
            } else {
                reverse(chars, start, end);
                start = i + 1;
                end++;
            }
        }
//        翻转最后一个单词
        reverse(chars, start, end);
        return new String(chars);
    }
    
    /**
     * 理论上空间复杂度为O(1) 时间复杂度为O(n)
     * 但是实际执行起来该方法花费7ms慢 fun()花费3ms 空间上该方法比fun少用0.1 具体看LeetCode提交记录
     * 可能是因为最后的replacAll中的正则匹配导致的
     */
    private static String fun1(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] chars = input.trim().toCharArray();
//        全字符翻转
        reverse(chars, 0, chars.length - 1);
        int start = 0, end = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
            
            } else if (chars[i - 1] == ' ' || chars[i - 1] == '@') {
                chars[i] = '@';
            } else {
                reverse(chars, start, end);
                start = i + 1;
            }
            end++;
        }
//        翻转最后一个单词
        reverse(chars, start, end);
        String res = new String(chars);
        return res.replaceAll("@", "");
    }
    
    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char tmp = chars[start];
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;
        }
    }
}
