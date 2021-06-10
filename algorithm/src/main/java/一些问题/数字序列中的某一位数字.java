package 一些问题;

/**
 * @author parry
 * @date 2020/12/09
 * 数字以 0123456789101112131415.....格式排列
 * 在这个序列中，第0位是0，第5位是5，第13位是1，第19位是4
 * 计算第i位是多少
 */
public class 数字序列中的某一位数字 {
    
    public static void main(String[] args) {
        System.out.println(fun(5));
        System.out.println(fun(13));
        System.out.println(fun(15));
        System.out.println(fun(19));
        System.out.println(fun(10));
        System.out.println(fun(190));
        System.out.println(fun(1000));
    }
    
    /**
     * 序列从1位数开始排列
     * 1位数所在的下标 0 ~ 9
     * 2位数所在的下标 10 ~ 10 + 99 * 2
     * 3位数所在的下标 10 + 99 * 2 + 1 ~ (10 + 99 * 2 + 1) + 999 * 3
     * ...
     * 可以通过i来确定要求的数字在哪个区间，然后再通过计算得出对应位上是哪个数字
     */
    private static int fun(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("i can not lower than 0");
        }
        if (i <= 9) {
            return i;
        }
//        计算i在哪个区间 digit表示当前是几位数的区间
        int bigBorder = 9, smallBorder = 0, digit = 1;
        while (i > bigBorder) {
            smallBorder = bigBorder + 1;
            digit++;
            bigBorder = bigBorder + 1 + digit * (bigBorder * 10 + 9);
        }
//        i - (smallBorder - 1)得出i是当前区间的第几位
        int t = i - (smallBorder - 1);
//        quotient表示第i位是哪个数字
        int quotient = t / digit;
//        计算是否有余数
        int remainder = t - (quotient * digit);
//        计算当前区间的最小值
        int minNum = 1;
        for (int j = 1; j < digit; j++) {
            minNum *= 10;
        }
//        如果余数为0 则第i位就是当前区间的第quotient个数字的最后一位
        if (remainder == 0) {
            String resultNum = String.valueOf(minNum + quotient - 1);
            return resultNum.charAt(resultNum.length() - 1) - '0';
        } else {
//            否则 第i位是第quotient + 1个数字的第remainder位
            String resultNum = String.valueOf(minNum + quotient);
            return resultNum.charAt(remainder - 1) - '0';
        }
    }
}
