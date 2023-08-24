public class Charity {
    private int id;                // unique identifier for the charity
    private String name;           // name of the charity
    private String purpose;        // purpose or cause supported by the charity
    private double fundsRaised;    // amount of money raised

    // Constructor
    public Charity(int id, String name, String purpose, double fundsRaised) {
        this.id = id;
        this.name = name;
        this.purpose = purpose;
        this.fundsRaised = fundsRaised;
    }

    // Getter for 'id'
    public int getId() {
        return id;
    }

    // Setter for 'id'
    public void setId(int id) {
        this.id = id;
    }

    // Getter for 'name'
    public String getName() {
        return name;
    }

    // Setter for 'name'
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {  // Validation check
            this.name = name;
        } else {
            System.out.println("Invalid charity name.");
        }
    }

    // Getter for 'purpose'
    public String getPurpose() {
        return purpose;
    }

    // Setter for 'purpose'
    public void setPurpose(String purpose) {
        if (purpose != null && !purpose.isEmpty()) {  // Validation check
            this.purpose = purpose;
        } else {
            System.out.println("Invalid charity purpose.");
        }
    }

    // Getter for 'fundsRaised'
    public double getFundsRaised() {
        return fundsRaised;
    }

    // Setter for 'fundsRaised'
    public void setFundsRaised(double fundsRaised) {
        if (fundsRaised >= 0) {  // Validation check
            this.fundsRaised = fundsRaised;
        } else {
            System.out.println("Invalid funds raised amount.");
        }
    }
}
