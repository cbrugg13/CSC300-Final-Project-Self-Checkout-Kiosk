import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Database {
    private static Map<Integer, Item> items = new HashMap<>();

    static {
        items.put(1234, new Item(1234, "Bananas", 0.99f, 0.5, false, false, true, false)); // Requires weight check but doesn't cause an error.
        items.put(5678, new Item(5678, "Apple", 1.99f, 1.0, false, false, false, false)); // Doesn't require weight check.
        items.put(9101, new Item(9101, "ErrorApple", 1.99f, 0.6, false, false, true, true)); // Requires weight check and causes an error.

        items.put(2345, new Item(2345, "Water Bottle", 0.99f, 1.0, false, false, false, false));
        items.put(6789, new Item(6789, "Peanut Butter", 3.99f, 2.5, false, false, true, true));
        items.put(0123, new Item(0123, "Wine", 9.99f, 2.65, true, true, false, false));
        items.put(4567, new Item(4567, "Medicine", 19.99f, 1.0, true, false, false, false));
        items.put(8901, new Item(8901, "Chips", 1.99f, 1.0, false, false, false, false));
        items.put(2335, new Item(2335, "Bread", 4.99f, 1.0, false, false, false, false));
        items.put(6779, new Item(6779, "Cereal", 4.99f, 1.10, false, false, false, false));
        items.put(0113, new Item(0113, "Plates", 5.99f, 1.5, false, false, true, false));
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

    public static void removeItem(int key) {
        System.out.println("Item ()" + items.get(key).getName() + ") has been removed.");
        items.remove(key);
    }
}