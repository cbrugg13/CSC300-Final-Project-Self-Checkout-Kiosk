public class Item implements PLUInterface {
    private int pluCode;
    private String name;
    private float price;
    private double weight;
    private boolean isAgeRestricted;
    private boolean isAlcohol;

    public Item(int pluCode, String name, float price, double weight, boolean isAgeRestricted, boolean isAlcohol) {
        this.pluCode = pluCode;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.isAgeRestricted = isAgeRestricted;
        this.isAlcohol = isAlcohol;
    }

    @Override
    public Item searchItemByPLU(int pluCode) {
        if (this.pluCode == pluCode) {
            return this;
        }
        return null;
    }

    public int getPluCode() {
        return pluCode;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isAgeRestricted() {
        return isAgeRestricted;
    }

    public boolean isAlcohol() {
        return isAlcohol;
    }
}

