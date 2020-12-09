package 一些问题;

import java.util.Stack;

/**
 * @author parry
 * @date 2020/12/01
 * 栈中的数字不包含重复
 * 给出一个压栈序列，判断给出的序列是否是该压栈序列的一个出栈序列
 * 压栈序列{1,2,3,4,5} 序列{4,5,3,2,1}是出栈序列 序列{4,3,5,1,2}不是出栈序列
 */
public class 栈的压入弹出序列 {
    
    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};
        int[] pop1 = {4, 5, 3, 2, 1};
        int[] pop2 = {4, 3, 5, 1, 2};
        System.out.println(fun(push, pop1));
        System.out.println(fun(push, pop2));
    }
    
    private static boolean fun(int[] push, int[] pop) {
        if (push == null || pop == null) {
            throw new IllegalArgumentException("push or pop can not be null");
        }
        if (push.length != pop.length) {
            return false;
        }
        Stack<Integer> pushStack = new Stack<>();
        int j = 0;
        for (int value : push) {
            if (value != pop[j]) {
                pushStack.push(value);
            } else {
                j++;
                while (pushStack.size() > 0 && pushStack.peek() == pop[j]) {
                    pushStack.pop();
            j++;
        }
    }
        }
        return j == pop.length;
    }
}
