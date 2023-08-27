import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Database {
    private static Map<Integer, Item> items = new HashMap<>();

    static {
        items.put(1234, new Item(1234, "Bananas", 0.99f, 0.5, false, false, true, false)); // Requires weight check but doesn't cause an error.
        items.put(5678, new Item(5678, "Apple", 1.99f, 1.0, false, false, false, false)); // Doesn't require weight check.
        items.put(9101, new Item(9101, "ErrorApple", 1.99f, 0.6, false, false, true, true)); // Requires weight check and causes an error.
    }

    public static Item getItemByPLU(int plu) {
        return items.get(plu);
    }

    public static Item getRandomItem() {
        if (items.size() == 0) {
            return null;
        }
        Integer[] keys = items.keySet().toArray(new Integer[0]);
        int randomKey = keys[new Random().nextInt(keys.length)];
        return items.get(randomKey);
    }    
}

