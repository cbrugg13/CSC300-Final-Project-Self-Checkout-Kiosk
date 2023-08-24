public class Item implements PLUInterface {
    private int pluCode;
    private String name;
    private float price;
    private boolean isAgeRestricted;

    public Item(int pluCode, String name, float price, boolean isAgeRestricted) {
        this.pluCode = pluCode;
        this.name = name;
        this.price = price;
        this.isAgeRestricted = isAgeRestricted;
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

    public boolean isAgeRestricted() {
        return isAgeRestricted;
    }
}

