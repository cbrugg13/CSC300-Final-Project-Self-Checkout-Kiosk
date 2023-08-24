import java.util.ArrayList;

public class Receipt {
    private int id;
    private int customerId;
    private ArrayList<Item> items;
    private float totalAmount;
    private String date;

    public Receipt(int id, int customerId, ArrayList<Item> items, float totalAmount, String date) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public String getDate() {
        return date;
    }
}

