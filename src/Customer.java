import java.util.ArrayList;

public class Customer {
    private int id;
    private String name;
    private int age;
    private ArrayList<Item> cart;

    public Customer(int id, String name, int age, ArrayList<Item> cart) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.cart = cart;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Item> getCart() {
        return cart;
    }
}

