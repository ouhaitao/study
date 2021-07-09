import java.util.LinkedHashMap;

/**
 * @author parry
 * @date 2021/07/07
 */
public class Main {
    
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>(4, 0.75f, true);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.get("1");
        map.get("2");
        map.get("3");
        map.get("4");
        System.out.println();
    }
}
