package 栈;

import java.util.Stack;

/**
 * @author parry
 * @date 2020/12/14
 */
public class 两个栈实现队列 {
    
    private static Stack<Integer> stack1 = new Stack<>();
    private static Stack<Integer> stack2 = new Stack<>();
    
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            push(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(pop() + " ");
        }
    }
    
    private static void push(Integer data) {
        stack1.push(data);
    }
    
    private static Integer pop() {
        if (stack2.size() > 0) {
            return stack2.pop();
        }
        while (stack1.size() > 0) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
