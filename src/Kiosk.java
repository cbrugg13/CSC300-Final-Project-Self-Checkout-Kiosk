import java.util.ArrayList;
import java.util.Scanner;

public class Kiosk {
    Scanner in = new Scanner(System.in);

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

    // Function that scans the items in the cart, while also displaying the weight and price.
    // Scanner will not function if the weight detection system is not calibrated.
    public void scanItem(Item item, WeightDetectionSystem wds, NotificationSystem ns) {
        if (wds.isCalibrated()) {
            System.out.println("Item " + item.getName() + " has been scanned. >>  Weight: " + item.getWeight() + " lbs, "
                                + "Price: $" + item.getPrice() + "0");
        } else {
            System.out.println("Weight detection system is not calibrated. Cannot scan the item.");
        }
    }

    // Checks to see if the customer is of age to buy Age-Restricted items.
    public void authorizeAgeRestrictedItem(Employee emp, Item item, Customer customer, NotificationSystem ns) {
        if (item.isAgeRestricted()) {
            if (customer != null && customer.getAge() >= 21) {
                System.out.println("Age-restricted item " + item.getName() + " has been authorized.");
            } else {
                System.out.println("Cannot authorize age-restricted item. Customer is under age.");
            }
        }
    }

    // checkout process that calculates the total for the customer.
    public void checkout(Customer customer, PaymentMethod pm, Receipt receipt, NotificationSystem ns, GiftCard gf, float removed) {
        float total = 0.0f;
        ArrayList<Item> items = receipt.getItems();
        for (Item item : items) {
            total += item.getPrice();
        }

        total += (total * taxRate);         // takes the tax of the total
        total += gf.getAmount();            // adds the amount on the gift card to the total. Adds zero if customer changed their mind.
        total = total - removed;            // subtracts the prices of the items that were removed due to failed verification/authorization.

        if (pm.getType().equals("Credit Card")) {
            System.out.println("Total Items: " + customer.getCart().size());
            System.out.println("How many bages did you use today? (1 bag costs 5 cents)");
            System.out.print("Bags Used: ");
            int bagUsed = in.nextInt();
            float bagPrice = 0.05f;
            total = total + (bagUsed * bagPrice);

            System.out.println(">> Total amount charged to credit card: $" + (Math.round(total * 100.0) / 100.0) + "0");
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

