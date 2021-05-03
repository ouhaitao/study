package 高频面试题.字符串;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author parry
 * @date 2021/05/03
 * https://leetcode-cn.com/problems/decode-string/
 */
public class 字符串解码 {
    
    public static void main(String[] args) {
        String str = "ef3[a10[bc]]gh";
        System.out.println(decodeString(str));
    }
    
    /**
     * S1 数字 [
     * S2 [ 字母
     *
     * 这里可以将整个字符串入栈 将整个数字入栈
     * 这样就可以不用加很多处理操作了
     */
    public static String decodeString(String s) {
        Deque<Character> s1 = new LinkedList<>();
        Deque<Character> s2 = new LinkedList<>();
        Deque<Character> countStack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                s1.push(c);
            } else if (c == '[') {
                s1.push(c);
                s2.push(c);
            } else if (c == ']') {
                List<Character> list = new LinkedList<>();
                while (true) {
                    char tmp = s2.pop();
                    if (tmp == '[') {
                        break;
                    } else {
                        list.add(tmp);
                    }
                }
//                必定是[
                s1.pop();
//                必定是[左边的数字
                int count = getCount(s1, countStack);
                char[] decodeString = decodeString(list, count);
                for (char decodeC : decodeString) {
                    s2.push(decodeC);
                }
            } else {
                s2.push(c);
            }
        }
        char[] res = new char[s2.size()];
        int i = res.length - 1;
        while (s2.size() > 0) {
            res[i--] = s2.pop();
        }
        return new String(res);
    }
    
    public static int getCount(Deque<Character> s1, Deque<Character> countStack) {
        int count = 0;
        while (true) {
            Character tmp = s1.peek();
            if (tmp == null || !Character.isDigit(tmp)) {
                break;
            }
            countStack.push(s1.pop());
        }
        while (countStack.size() > 0) {
            char tmp = countStack.pop();
            count = count * 10 + tmp - '0';
        }
        return count;
    }
    
    public static char[] decodeString(List<Character> list, int count) {
        char[] res = new char[list.size() * count];
        int index = 0;
        for (int i = 0; i < count; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                res[index++] = list.get(j);
            }
        }
        return res;
    }
    
}
