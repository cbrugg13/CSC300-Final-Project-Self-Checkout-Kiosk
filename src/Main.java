import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Item apple = new Item(1, "Apple", 1.5f, false);
        Item wine = new Item(2, "Wine", 10.0f, true);

        Item searchedItem = apple.searchItemByPLU(1);
        if (searchedItem != null) {
            System.out.println("Found item: " + searchedItem.getName());
        } else {
            System.out.println("Item not found.");
        }
        
        ArrayList<Item> items = new ArrayList<>();
        items.add(apple);
        items.add(wine);

        PaymentMethod paymentMethod = new PaymentMethod("Credit Card", "1234 5678 9012 3456", "12/25");

        Kiosk kiosk = new Kiosk(1, "Front", "Grocery", "Online", 0.10);

        Employee employee = new Employee(1, "John", "Manager", "john@email.com");

        WeightDetectionSystem weightSystem = new WeightDetectionSystem(0.0f, 100.0f, true);

        NotificationSystem notificationSystem = new NotificationSystem();

        Receipt receipt = new Receipt(1, 1, items, 11.5f, "23-08-2023");

        kiosk.scanItem(apple, weightSystem, notificationSystem);
        kiosk.authorizeAgeRestrictedItem(employee, wine, null, notificationSystem);
        kiosk.checkout(null, paymentMethod, receipt, notificationSystem);

        boolean isVerified = employee.verifyReceipt(receipt);

        System.out.println("Receipt verification: " + isVerified);
    }
}

