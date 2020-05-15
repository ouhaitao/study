/**
 * @author parry
 * @date 2020/04/13
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println(fun(4));
    }
    
    private static int fun(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        if (n == 2) {
            return 4;
        }
        return fun(n - 1) * 2 - fun(n - 3);
    }
}
