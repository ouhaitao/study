package 栈;

import java.util.Stack;

/**
 * @author parry
 * @date 2020/12/01
 * 定义一个栈，该栈拥有min、pop、push方法，时间复杂度都是O(1)
 * min方法是返回栈的最小值
 *
 * 本题用实际例子进行pop、push等操作分析最小值的变化
 * 最终得出一个结论
 */
public class 包含min函数的栈 {
    
    /**
     * 存储数据
     */
    private static Stack<Integer> data = new Stack<>();
    
    /**
     * 存储最小值
     */
    private static Stack<Integer> minData = new Stack<>();
    
    private static Integer min() {
        if (minData.size() == 0) {
            return null;
        }
        return minData.peek();
    }
    
    private static void push(int num) {
        data.push(num);
        if (data.empty() || num <= minData.peek()) {
            minData.push(num);
        } else {
            minData.push(min());
        }
    }
    
    private static int pop() {
        minData.pop();
        return data.pop();
    }

}
