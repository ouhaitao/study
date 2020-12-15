package 队列;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author parry
 * @date 2020/12/14
 */
public class 队列的最大值 {
    
    private static Queue<Integer> data = new LinkedList<>();
    private static Deque<Integer> maxData = new LinkedList<>();
    
    private static Integer max() {
        Integer peek = maxData.peek();
        return peek == null ? -1 : peek;
    }
    
    private static void offer(Integer num) {
        data.offer(num);
        if (maxData.isEmpty() || num > max()) {
            maxData.clear();
        } else {
            while (true) {
                assert maxData.peekLast() != null;
                if (maxData.peekLast() < num) {
                    maxData.removeLast();
                } else {
                    break;
                }
            }
        }
        maxData.offer(num);
    }
    
    private static Integer poll() {
        Integer poll = data.poll();
        if (!maxData.isEmpty() && maxData.peek().equals(poll)) {
            maxData.poll();
        }
        return poll == null ? -1 : poll;
    }
}
