package 高频面试题.字符串;

/**
 * @author parry
 * @date 2021/05/03
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class 翻转字符串里的单词 {
    
    public static void main(String[] args) {
        System.out.println(reverseWords("  Bob    Loves  Alice   "));
    }
    
    public static String reverseWords(String s) {
//        剔除开头结尾空格
        s = s.trim();
        char[] sChars = s.toCharArray();
        char[] res = new char[sChars.length];
    
//        剔除中间多余空格
        int lastSpaceIndex = -2;
//        res数组的长度
        int resLength = 0;
        for (int i = 0; i < sChars.length; i++) {
            if (sChars[i] == ' ') {
                if (i - lastSpaceIndex != 1) {
                    res[resLength] = sChars[i];
                    resLength++;
                }
                lastSpaceIndex = i;
            } else {
                res[resLength] = sChars[i];
                resLength++;
            }
        }
    
        StringBuilder sb = new StringBuilder(resLength);
        int wordLength = 0;
        for (int i = resLength - 1; i >= 0; i--) {
            if (res[i] != ' ') {
                wordLength++;
            } else {
                sb.append(res, i + 1, wordLength);
                sb.append(' ');
                wordLength = 0;
            }
        }
        sb.append(res, 0, wordLength);
        return sb.toString();
    }
}
