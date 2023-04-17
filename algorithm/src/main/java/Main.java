import java.util.*;

/**
 * @author parry
 * @date 2021/07/09
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        String arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-16";
        System.out.println(main.countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob));
    }
    
    private final int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String maxArrive = compare(arriveAlice, arriveBob, false);
        String minLeave = compare(leaveAlice, leaveBob, true);
        if (maxArrive.equals(minLeave)) {
            return 1;
        }
        if (compare(maxArrive, minLeave, true).equals(minLeave)) {
            return 0;
        }
        String[] arrive = maxArrive.split("-");
        String[] leave = minLeave.split("-");
        if (arrive[0].equals(leave[0])) {
            return Integer.parseInt(leave[1]) - Integer.parseInt(arrive[1]) + 1;
        } else {
            int arriveMonth = Integer.parseInt(arrive[0]);
            int leaveMonth = Integer.parseInt(leave[0]);
            int res = -Integer.parseInt(arrive[1]) + 1;
            for (int i = arriveMonth - 1; i < leaveMonth - 1; i++) {
                res += days[i];
            }
            return res + Integer.parseInt(leave[1]);
        }
    }
    
    public String compare(String date1, String date2, boolean asc) {
        String[] date1Split = date1.split("-");
        String[] date2Split = date2.split("-");
        int date1Month = Integer.parseInt(date1Split[0]);
        int date2Month = Integer.parseInt(date2Split[0]);
        if (date1Month > date2Month) {
            return asc ? date2 : date1;
        } else if (date1Month < date2Month) {
            return asc ? date1 : date2;
        } else if (Integer.parseInt(date1Split[1]) >= Integer.parseInt(date2Split[1])) {
            return asc ? date2 : date1;
        } else {
            return asc ? date1 : date2;
        }
    }
}
