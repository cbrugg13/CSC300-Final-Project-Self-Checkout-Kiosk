public class PaymentMethod {
    private String type;
    private String cardNumber;
    private String expiryDate;

    public PaymentMethod(String type, String cardNumber, String expiryDate) {
        this.type = type;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    public String getType() {
        return type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}

