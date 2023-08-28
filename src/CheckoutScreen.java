import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class CheckoutScreen extends JFrame {
    JList<String> itemList;
    DefaultListModel<String> listModel;
    int bagCount;
    JLabel subtotalLabel;
    JLabel taxLabel;
    JLabel totalLabel;

    public CheckoutScreen(int bagCount, DefaultListModel<String> items, double subtotal, double tax, double total) {
        this.bagCount = bagCount;
        this.listModel = items;

        // Setting up the cart display just like ScanScreen
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
        add(cancelCheckoutButton);

        // Right Rectangle with Payment options
        JButton creditDebitButton = new JButton("Credit / Debit");
        creditDebitButton.setBounds(600, 200, 200, 50);
        add(creditDebitButton);

        JButton applePayButton = new JButton("Apple Pay");
        applePayButton.setBounds(600, 300, 200, 50);
        add(applePayButton);

        JButton tapToPayButton = new JButton("Tap-To-Pay");
        tapToPayButton.setBounds(600, 400, 200, 50);
        add(tapToPayButton);

        setSize(1200, 750);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
