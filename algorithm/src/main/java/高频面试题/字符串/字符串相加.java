package 高频面试题.字符串;

/**
 * @author parry
 * @date 2021/05/03
 * https://leetcode-cn.com/problems/add-strings/
 */
public class 字符串相加 {
    
    public static void main(String[] args) {
        String num1 = "11";
        String num2 = "189";
        System.out.println(addStrings(num1, num2));
    }
    
    /**
     * @param num1 非负整数
     * @param num2 非负整数
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
//        进位
        int flag = 0;
//        a1是长度更长的字符串
        char[] a1, a2;
        if (num1.length() > num2.length()) {
            a1 = num1.toCharArray();
            a2 = num2.toCharArray();
        } else {
            a1 = num2.toCharArray();
            a2 = num1.toCharArray();
        }
    
        int num1Index = a1.length - 1;
        int num2Index = a2.length - 1;
        for (; num2Index >= 0; num2Index--, num1Index --) {
            int res = getInt(a1[num1Index]) + getInt(a2[num2Index]) + flag;
            if (res > 9) {
                res -= 10;
                flag = 1;
            } else {
                flag = 0;
            }
            sb.append(res);
        }
    
        for (int i = a1.length - a2.length - 1; i >= 0; i--) {
            int res = getInt(a1[i]) + flag;
            if (res > 9) {
                res -= 10;
                flag = 1;
            } else {
                flag = 0;
            }
            sb.append(res);
        }
        if (flag == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }
    
    public static int getInt(char c) {
        return c - '0';
    }
}
