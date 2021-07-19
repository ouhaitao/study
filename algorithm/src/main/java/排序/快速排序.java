package 排序;

import java.util.Arrays;

public class 快速排序 {
    
    public static void main(String[] args) {
        
        int[] data = {6, 2, 7, 3, 8, 9};
        int[] data1 = {1, 2, 3, 4, 5, 6};
        int[] data2 = {6, 5, 4, 3, 2, 1};
        int[] data3 = {5, 2, 6, 4, 3, 5};
        Arrays.stream(quickSort3(data3, 0, data3.length - 1)).forEach(x -> System.out.print(x + " "));
//        System.out.println(findMinNValue(data, 2));
    }
    
    /**
     * 时间复杂度为O(nlog n)，O(log n)
     * 非原地排序方法，需要额外空间
     *
     * @param data  待排数组
     * @param start 待排元素起始下标
     * @param end   待排元素结束下标
     */
    
    static int[] quickSort(int[] data, int start, int end) {
        if (end <= start) {
            return data;
        }
        
        //简便起见,确定枢纽元,这里以下标为start的元素
        int key = data[start];
        int i = 0;
        int j = end - start + 1;
        //为了减少交换次数,新建数组用于存储排好序的结果
        int[] tmp = new int[j--];
        for (int z = start + 1; z <= end; z++) {
            if (data[z] > key) {//将小于枢纽元的元素从数组头开始存放
                tmp[j--] = data[z];
            } else {//这里会造成不稳定排序
                tmp[i++] = data[z];//将大于枢纽元的元素从数组尾部开始存放
            }
        }
        //将枢纽元放入结果数组中
        tmp[i] = key;
        //最后将排好序的结果放入data中,最后枢纽元在data中的下标是start+i
        System.arraycopy(tmp, 0, data, start, tmp.length);
        //对枢纽元左边的元素进行排序
        data = quickSort(data, start, start + i - 1);
        //对枢纽元右边的元素进行排序
        data = quickSort(data, start + i + 1, end);
        return data;
    }
    
    /**
     * 原地排序，不需要额外空间
     */
    public static int[] quickSort1(int[] data, int start, int end) {
        if (end <= start) {
            return data;
        }
        int key = partition(data, start, end);
        quickSort1(data, start, key - 1);
        quickSort1(data, key + 1, end);
        return data;
    }
    
    /**
     * 分区返回枢纽元的下标
     */
    private static int partition(int[] data, int start, int end) {
        int key = data[end];
//        [start,i)是小于枢纽元的区间,(i,end]是大于等于枢纽元的区间
        int i = start;
        for (int j = start; j < end; j++) {
            if (data[j] < key) {
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
                i++;
            }
        }
        int tmp = data[i];
        data[i] = data[end];
        data[end] = tmp;
        return i;
    }
    
    /**
     * 两路快排
     * 由于一趟快排会把小于枢纽元的放在左边，大于枢纽元的放在右边，等于枢纽元的要么放在左边要么放在右边
     * 当等于枢纽元的元素过多时会导致某一边的数据量大于另一边，严重的可能会退化成O(n²)所以产生了二路快排
     * 二路快排通过遇到相等的就不处理，最终结果是等于枢纽元的数据分散在枢纽元两边
     */
    private static int[] quickSort2(int[] data, int start, int end) {
        if (end <= start) {
            return data;
        }
        int key = partition2(data, start, end);
        quickSort2(data, start, key - 1);
        quickSort2(data, key + 1, end);
        return data;
    }
    
    private static int partition2(int[] data, int start, int end) {
//        base必须是下标为i的元素，i为start
        int i = start;
        int j = end;
        int base = data[i];

//        必须是先从右往左扫描，base取的是下标为i的元素，第一次从右往左扫描会将第一个小于base的元素覆盖base
//        相当于将base从数组中删除，这样直接进行赋值操作就不会删除其他元素
//        [start,i)是小于等于base (j,end]是大于等于base的
        while (i < j) {
            
            //从右到左扫描，扫描出第一个比base小的元素，然后j停在那里。
            while (j > i && data[j] >= base) {
                j--;
            }
            data[i] = data[j];
            
            //从左到右扫描，扫描出第一个比base大的元素，然后i停在那里。
            while (i < j && data[i] <= base) {
                i++;
            }
            data[j] = data[i];
        }
        data[j] = base;
        return j;
    }
    
    /**
     * 三路快排
     * 对二路快排的优化，当数组中等于枢纽元的元素分布极端，例如全部分布在数据末尾
     * 使用二路快排会使大于枢纽元的一边元素数量远大于小于枢纽元的一边，造成性能损失
     */
    private static int[] quickSort3(int[] data, int start, int end) {
        if (end <= start) {
            return data;
        }
        int[] keys = partition3(data, start, end);
        quickSort3(data, start, keys[0] - 1);
        quickSort3(data, keys[1] + 1, end);
        return data;
    }
    
    private static int[] partition3(int[] data, int start, int end) {
        int i = start;
        int j = end;
        int k = i;
        int base = data[i];
//        [start,i)是小于base的元素 (j,end]是大于base的元素 [i,j]是等于base的元素
        while (k <= j) {
            if (data[k] < base) {
//                由于k从i开始，所以k>=i，小于k的都是已经处理过的，所以这里交换后k自增
                swap(data, k++, i++);
            } else if (data[k] > base) {
//                由于将数组后面的元素换到了下标为k的位置来，没有进行过判断需要对其进行判断，所以k不自增，
                swap(data, k, j--);
            } else {
                k++;
            }
        }
        return new int[]{i, j};
    }
    
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    /**
     * 找到第n小的数
     * 利用快排的分区思想
     * 对于升序的数组，第n小的元素即为下标为n-1的元素
     * 每次分区相当于确定了枢纽元在数组中的位置，即确定了枢纽元是数组中第几小的元素
     */
    public static Integer findMinNValue(int[] data, int n) {
        if (data.length < n) {
            return null;
        }
        int start = 0, end = data.length - 1;
        while (true) {
            int partition = partition(data, start, end);
            if (partition + 1 == n) {
                return data[partition];
            } else if (partition + 1 > n) {
                end = partition - 1;
            } else {
                start = partition + 1;
            }
        }
    }
}
