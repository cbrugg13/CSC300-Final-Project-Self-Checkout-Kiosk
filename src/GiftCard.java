public class GiftCard {
    private int giftCardID;
    private boolean activationStatus;
    private float amount;

    // Constructor
    public GiftCard(int giftCardID, boolean activationStatus, float amount) {
        this.giftCardID = giftCardID;
        this.activationStatus = activationStatus;
        this.amount = amount;
    }

    // Getter for giftCardID
    public int getGiftCardID() {
        return giftCardID;
    }

    // Setter for giftCardID
    public void setGiftCardID(int giftCardID) {
        this.giftCardID = giftCardID;
    }

    // Getter for activationStatus
    public boolean isActivationStatus() {
        return activationStatus;
    }

    // Setter for activationStatus
    public void setActivationStatus(boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    // Getter for amount
    public float getAmount() {
        return amount;
    }

    // Setter for amount
    public void setAmount(float amount) {
        if (amount >= 0) { // Checking that the amount is not negative
            this.amount = amount;
        } else {
            System.out.println("Invalid amount. Cannot be negative.");
        }
    }
}

