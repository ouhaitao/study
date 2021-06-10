package 高频面试题.字符串;

import java.util.LinkedList;

/**
 * @author parry
 * @date 2021/05/03
 * https://leetcode-cn.com/problems/multiply-strings/
 */
public class 字符串相乘 {
    
    public static void main(String[] args) {
//        String num1 = "123";
//        String num2 = "456";
        String num1 = "98";
        String num2 = "9";
        System.out.println(multiply(num1, num2));
    }
    
    /**
     * 非负整数
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
//        进位
        int carry = 0;
//        a1是长度更长的字符串
        char[] a1 = num1.toCharArray();
        char[] a2 = num2.toCharArray();
        int count = 0;
        StringBuilder res = new StringBuilder("0");
        for (int i = a1.length - 1; i >= 0; i--) {
            StringBuilder multiplyRes = new StringBuilder();
//            每一次乘积都需要补0
            for (int k = 0; k < count; k++) {
                multiplyRes.append(0);
            }
//            计算乘积
            for (int j = a2.length - 1; j >= 0; j--) {
                int multiply = getInt(a1[i]) * getInt(a2[j]) + carry;
                carry = multiply / 10;
                multiplyRes.append(multiply % 10);
            }
            if (carry > 0) {
                multiplyRes.append(carry);
                carry = 0;
            }
//            添加结果
            count++;
            res = addStrings(res, multiplyRes);
        }
        return res.reverse().toString();
    }
    
    public static StringBuilder addStrings(StringBuilder num1, StringBuilder num2) {
        StringBuilder res = new StringBuilder();
//        进位
        int flag = 0;
        
        for (int i = 0; i < num1.length() || i < num2.length(); i++) {
            int n1 = i < num1.length() ? getInt(num1.charAt(i)) : 0;
            int n2 = i < num2.length() ? getInt(num2.charAt(i)) : 0;
            int sum = n1 + n2 + flag;
            if (sum > 9) {
                flag = 1;
                sum -= 10;
            } else {
                flag = 0;
            }
            res.append(sum);
        }
        
        if (flag == 1) {
            res.append(1);
        }
        return res;
    }
    
    public static int getInt(char c) {
        return c - '0';
    }
    
}
