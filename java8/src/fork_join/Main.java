package fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

//RecursiveAction与RecursiveTask区别是compute方法一个有返回值,一个没有返回值,还有setRawResult(),具体看源码
public class Main extends RecursiveTask<Long> {
    
    private int limit = 100;
    private long star;
    private long end;
    
    public Main(long star, long end) {
        this.star = star;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        long limit = end - star;
        if (limit < this.limit) {
            long sum = 0L;
            for (Long i = star; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = (star + end) / 2;
            Main m1 = new Main(star, mid);
            Main m2 = new Main(mid + 1, end);
            //将两个任务
            m1.fork();
            m2.fork();
            return m1.join() + m2.join();
        }
    }
    
    public static void main(String[] args) {
        f1();
    }
    
    private static void f1() {
        
        int end = 1000000;
        
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new Main(0, end);
        
        long star = System.currentTimeMillis();
        System.out.println("forkJoin:" + forkJoinPool.invoke(forkJoinTask));
        System.out.println(System.currentTimeMillis() - star);
        
        star = System.currentTimeMillis();
        // 并行流使用默认的ForkJoinPool
        System.out.println("parallel:" + LongStream.rangeClosed(0, end).parallel().reduce(Long::sum).getAsLong());
        System.out.println(System.currentTimeMillis() - star);
        
        star = System.currentTimeMillis();
        System.out.println("reduce:" + LongStream.rangeClosed(0, end).reduce(Long::sum).getAsLong());
        System.out.println(System.currentTimeMillis() - star);
    }
    
    private static void f2() {
        Lock lock = new ReentrantLock();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        IntStream.range(0, 10000).forEach(list1::add);
        
        IntStream.range(0, 10000).parallel().forEach(list2::add);
        
        IntStream.range(0, 10000).forEach(i -> {
            lock.lock();
            try {
                list3.add(i);
            } finally {
                lock.unlock();
            }
        });
        
        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println(list3.size());
    }
}
