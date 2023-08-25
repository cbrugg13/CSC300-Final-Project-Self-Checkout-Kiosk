public class Employee {
    private int id;
    private String name;
    private String role;
    private String email;

    public Employee(int id, String name, String role, String email) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public boolean verifyReceipt(Receipt receipt) {
        // Simplified verification logic
        if (receipt.getTotalAmount() > 0) {
            return true;
        }
        return false;
    }

    public boolean verifyAge(Customer customer) 
    {
        if (customer.getAge() >= 21) {
            return true;
        }
        else
            return false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
}

