import java.lang.reflect.Array;
import java.util.Arrays;

public class 动态规划 {

    public static void main(String[] args) {
        backpack();
    }

    //背包问题
    public static void backpack() {

        int[] w = {0, 2, 2, 6, 5, 4};//重量
        int[] v = {0, 6, 3, 5, 4, 6};//价值
        int c = 10;//背包最大重量
        int[][] res = new int[w.length + 1][c + 1];

        //初始化边界
        for (int i = 1; i < c + 1; i++) {
            if (i < w[1]) {
                res[1][i] = 0;
            }
            else {
                res[1][i] = v[1];
            }
        }

        //从放入第二个物体开始算起
        for (int n = 2; n < 6; n++) {
            for (int m = 1; m < 11; m++) {
                if (m < w[n]) {
                    res[n][m] = res[n - 1][m];
                }
                else {
                    res[n][m] = Math.max(res[n - 1][m], res[n - 1][m - w[n]] + v[n]);
                }
            }
        }
        System.out.println(res[5][10]);
    }

    //硬币找零
    private static void coin() {
        int n = 21;
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        for (int i = 3; i < res.length; i++) {
            if (i < 5)
                res[i] = Math.min(res[i - 1], res[i - 3]) + 1;
            else {
                int tmp = Math.min(res[i - 1], res[i - 3]);
                res[i] = Math.min(res[i - 5], tmp) + 1;
            }
        }
        System.out.println(res[n]);
    }

    //最大公共序列
    private static void findMaxLengthStr() {
        char[] str1 = {'A', 'B', 'C', 'B', 'D', 'A', 'B'};
        char[] str2 = {'B', 'D', 'C', 'A', 'B', 'A'};
        int[][] res = new int[str1.length][str2.length];

        for (int i=0;i<str2.length;i++){
            if (str1[0]==str2[i])
                res[0][i]=1;
        }
        for (int i=0;i<str1.length;i++){
            if (str2[0]==str1[i])
                res[i][0]=1;
        }

        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else
                    res[i][j] = Math.max(res[i - 1][j], res[i][j - 1]);

            }
        }

        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str2.length; j++) {
                System.out.print(res[i][j]+" ");
            }
            System.out.println();
        }
    }

    //最大公共子串
//    private static void findMaxString(){
//        char[] str1 = {'A', 'B', 'D', 'C', 'D', 'A', 'B'};
//        char[] str2 = {'B', 'D', 'C', 'A', 'B', 'A'};
//        int[][] res=new int[str1.length][str2.length];
//        int max=0;
//
//        for (int i=0;i<str2.length;i++) {
//            if (str1[0]==str2[i]) {
//                res[0][i] = 1;
//                max=1;
//            }
//        }
//
//        for (int i=0;i<str1.length;i++) {
//            if (str1[i]==str2[0]) {
//                res[i][0] = 1;
//                max=1;
//            }
//        }
//
//        for (int i=1;i<str1.length;i++){
//            for (int j=1;j<str2.length;j++){
//                if (str1[i]==str2[j]){
//                    res[i][j] = res[i - 1][j - 1] + 1;
//                    if (max<res[i][j])
//                        max=res[i][j];
//                }else {
//                    res[i][j]=0;
//                }
//            }
//        }
//
//        Arrays.stream(res).forEach(x->{
//            Arrays.stream(x).forEach(y-> System.out.print(y+" "));
//            System.out.println();
//        });
//
//        for (int i=0;i<str1.length;i++){
//            for (int j=0;j<str2.length;j++){
//                if (res[i][j]==max){
//                    for (int tmp=max-1;tmp>=0;tmp--){
//                        System.out.print(str1[i-tmp]);
//                    }
//                    System.out.println();
//                }
//            }
//        }
//
//    }

    private static void findMaxString(){
        char[] str1 = {'A', 'B', 'D', 'C', 'D', 'A', 'B'};
        char[] str2 = {'B', 'D', 'C', 'A', 'B', 'A'};
        int[][] res=new int[str1.length][str2.length];
        int[] index=new int[str2.length];
        int count=0;
        int max=0;

        for (int i=0;i<str2.length;i++) {
            if (str1[0]==str2[i]) {
                res[0][i] = 1;
                index[count++]=0;
                max=1;
            }
        }

        for (int i=0;i<str1.length;i++) {
            if (str1[i]==str2[0]) {
                res[i][0] = 1;
                index[count++]=i;
                max=1;
            }
        }

        for (int i=1;i<str1.length;i++){
            for (int j=1;j<str2.length;j++){
                if (str1[i]==str2[j]){
                    res[i][j] = res[i - 1][j - 1] + 1;
                    if (max<res[i][j]) {
                        max = res[i][j];
                        count=0;
                        index[count++]=i;
                    }
                }else {
                    res[i][j]=0;
                }
            }
        }

        Arrays.stream(res).forEach(x->{
            Arrays.stream(x).forEach(y-> System.out.print(y+" "));
            System.out.println();
        });

        for (int i=0;i<count;i++){
            for (int tmp=max-1;tmp>=0;tmp--){
                System.out.print(str1[index[i]-tmp]);
            }
            System.out.println();
        }
    }
}
