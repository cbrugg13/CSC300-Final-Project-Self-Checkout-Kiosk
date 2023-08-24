import java.util.ArrayList;

public class Kiosk {
    private int id;
    private String location;
    private String type;
    private String status;
    private double taxRate;

    public Kiosk(int id, String location, String type, String status, double taxRate) {
        this.id = id;
        this.location = location;
        this.type = type;
        this.status = status;
        this.taxRate = taxRate;
    }

    public void scanItem(Item item, WeightDetectionSystem wds, NotificationSystem ns) {
        if (wds.isCalibrated()) {
            System.out.println("Item " + item.getName() + " has been scanned. Price: $" + item.getPrice());
        } else {
            System.out.println("Weight detection system is not calibrated. Cannot scan the item.");
        }
    }

    public void authorizeAgeRestrictedItem(Employee emp, Item item, Customer customer, NotificationSystem ns) {
        if (item.isAgeRestricted()) {
            if (customer != null && customer.getAge() >= 21) {
                System.out.println("Age-restricted item " + item.getName() + " has been authorized.");
            } else {
                System.out.println("Cannot authorize age-restricted item. Customer is under age.");
            }
        }
    }

    public void checkout(Customer customer, PaymentMethod pm, Receipt receipt, NotificationSystem ns) {
        float total = 0.0f;
        ArrayList<Item> items = receipt.getItems();
        for (Item item : items) {
            total += item.getPrice();
        }

        total += (total * taxRate);

        if (pm.getType().equals("Credit Card")) {
            System.out.println("Total amount charged to credit card: $" + total);
        } else {
            System.out.println("Payment method not supported.");
        }
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
