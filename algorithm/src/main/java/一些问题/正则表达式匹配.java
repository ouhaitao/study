package 一些问题;

/**
 * @author parry
 * @date 2020/11/26
 */
public class 正则表达式匹配 {
    
    /**
     * 实现一个只有"." "*"的正则表达式 "."表示任意字符 "*"表示前面的字符匹配若干次
     * 字符串中不会出现"." "*"
     * 表达式语法正确
     */
    public static void main(String[] args) {
        String str = "aaa";
        String regex = "a.a";
        fun(str, regex);
        regex = "ab*ac*a";
        fun(str, regex);
        regex = "aaa*";
        fun(str, regex);
        regex = "aa.";
        fun(str, regex);
    }
    
    private static void fun(String str, String regex) {
        if (str == null) {
            System.out.println("字符串不能为null");
            return;
        }
        if (regex == null || regex.length() == 0) {
            System.out.println("表达式不能为空");
            return;
        }
        char[] strChars = str.toCharArray();
        char[] regexChars = regex.toCharArray();
        int index = 0;
        for (int i = 1; i < regexChars.length; i++) {
//            如果当前字符不是 *
            if (!isStartChar(regexChars[i])) {
                if (isPointChar(regexChars[i - 1])) {
                    index++;
                    if (index == strChars.length) {
                        System.out.println("匹配");
                        return;
                    }
                    continue;
                }
                if (strChars[index++] != regexChars[i - 1]) {
                    System.out.println("不匹配");
                    return;
                }
            } else {
                if (isPointChar(regexChars[i - 1])) {
                    System.out.println("匹配");
                    return;
                }
                while (strChars[index] == regexChars[i - 1]) {
                    index++;
                    if (index == strChars.length) {
                        System.out.println("匹配");
                        return;
                    }
                }
//                当前字符是*,判断完之后需要向后多移动一位
                i++;
            }
        }
        
//        如果最后一个字符是 * 号直接返回 上面的循环已经做完判断
        if (isStartChar(regexChars[regexChars.length - 1])) {
            System.out.println("匹配");
            return;
        }
        
        if (isPointChar(regexChars[regexChars.length - 1])) {
            if (index == strChars.length - 1) {
                System.out.println("匹配");
            }else {
                System.out.println("不匹配");
            }
        } else if (!isStartChar(regexChars[regexChars.length - 1])) {
            if (index == strChars.length - 1 && strChars[index] == regexChars[regexChars.length - 1]) {
                System.out.println("匹配");
            }else {
                System.out.println("不匹配");
            }
        }
    }
    
    private static boolean isStartChar(char target) {
        return target == '*';
    }
    
    private static boolean isPointChar(char target) {
        return target == '.';
    }
}
