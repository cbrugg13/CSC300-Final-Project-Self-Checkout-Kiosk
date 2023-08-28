import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class CheckoutScreen extends JFrame {
    JList<String> itemList;
    DefaultListModel<String> listModel;
    int bagCount;
    JLabel subtotalLabel;
    JLabel taxLabel;
    JLabel totalLabel;
    JButton creditButton;
    JButton debitButton;
    JButton goBackButton;
    JButton creditDebitButton;
    JButton applePayButton;
    JButton tapToPayButton;
    JLabel contactlessPaymentLabel; // Placeholder label for the image

    private void showContactlessPaymentMode() {
        creditButton.setVisible(false);
        debitButton.setVisible(false);
        goBackButton.setVisible(true); // Only this button remains visible

        creditDebitButton.setVisible(false);
        applePayButton.setVisible(false);
        tapToPayButton.setVisible(false);

        contactlessPaymentLabel.setVisible(true); // Show the placeholder label
    }

    // Delay added for testing after using Apple pay, credit and tap-to-pay
    private void moveToReceiptScreenAfterDelay() {
        Timer timer = new Timer(10000, e -> {
            this.dispose();
            new ReceiptScreen();
        });
        timer.setRepeats(false);
        timer.start();
    }
    

    public CheckoutScreen(int bagCount, DefaultListModel<String> items, double subtotal, double tax, double total) {
        this.bagCount = bagCount;
        this.listModel = items;

        // Setting up the cart display
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setBounds(50, 100, 450, 400);
        add(scrollPane);

        // Display Subtotal, Tax and Total
        subtotalLabel = new JLabel("Subtotal: $" + String.format("%.2f", subtotal));
        subtotalLabel.setBounds(50, 510, 200, 25);
        add(subtotalLabel);

        taxLabel = new JLabel("Tax: $" + String.format("%.2f", tax));
        taxLabel.setBounds(50, 540, 200, 25);
        add(taxLabel);

        totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
        totalLabel.setBounds(50, 570, 200, 25);
        add(totalLabel);

        JButton cancelCheckoutButton = new JButton("Cancel Checkout");
        cancelCheckoutButton.setBounds(50, 620, 200, 50);
        cancelCheckoutButton.addActionListener(e -> {
            this.dispose(); // Close the current CheckoutScreen
            new ScanScreen(); // Open the ScanScreen and starts a new checkout
        });
        add(cancelCheckoutButton);

        // Right Rectangle with Payment options
        creditDebitButton = new JButton("Credit / Debit");
        creditDebitButton.setBounds(600, 200, 200, 50);
        creditDebitButton.addActionListener(e -> {
            creditDebitButton.setVisible(false);
            applePayButton.setVisible(false);
            tapToPayButton.setVisible(false);

            creditButton.setVisible(true);
            debitButton.setVisible(true);
            goBackButton.setVisible(true);
        });
        add(creditDebitButton);

        contactlessPaymentLabel = new JLabel("*ContactlessPayment.jpg*");
        contactlessPaymentLabel.setBounds(600, 200, 200, 50);
        contactlessPaymentLabel.setVisible(false);
        add(contactlessPaymentLabel);

        applePayButton = new JButton("Apple Pay");
        applePayButton.setBounds(600, 300, 200, 50);
        applePayButton.addActionListener(e -> showContactlessPaymentMode());
        applePayButton.addActionListener(e -> {
            moveToReceiptScreenAfterDelay();
        });
        add(applePayButton);
        

        tapToPayButton = new JButton("Tap-To-Pay");
        tapToPayButton.setBounds(600, 400, 200, 50);
        tapToPayButton.addActionListener(e -> showContactlessPaymentMode());
        tapToPayButton.addActionListener(e -> {
            moveToReceiptScreenAfterDelay();
        });
        add(tapToPayButton);

        // New payment option buttons setup
        creditButton = new JButton("Credit");
        creditButton.setBounds(600, 200, 200, 50);
        creditButton.setVisible(false); // initially hidden
        creditButton.addActionListener(e -> {
            moveToReceiptScreenAfterDelay();
        });
        add(creditButton);

        debitButton = new JButton("Debit");
        debitButton.setBounds(600, 300, 200, 50);
        debitButton.setVisible(false); // initially hidden
        // Add any needed action listeners here...
        add(debitButton);

        goBackButton = new JButton("Go Back");
        goBackButton.setBounds(600, 400, 200, 50);
        goBackButton.setVisible(false); // initially hidden
        goBackButton.addActionListener(e -> {
            creditButton.setVisible(false);
            debitButton.setVisible(false);
            goBackButton.setVisible(false);
            contactlessPaymentLabel.setVisible(false);

            creditDebitButton.setVisible(true);
            applePayButton.setVisible(true);
            tapToPayButton.setVisible(true);
        });
        add(goBackButton);

        setSize(1200, 750);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
