import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class ScanScreen extends JFrame {
    JList<String> itemList; // This will display the items in the cart.
    DefaultListModel<String> listModel; // The model for the JList.

    public ScanScreen() {
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Left Rectangle with Cart Details
        JLabel myCartLabel = new JLabel("My Cart");
        myCartLabel.setBounds(100, 50, 200, 30);
        myCartLabel.setFont(new Font("Calibri", Font.PLAIN, 20));

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setBounds(50, 100, 450, 400);
        
        JLabel subtotalLabel = new JLabel("Subtotal: ");
        subtotalLabel.setBounds(50, 520, 200, 25);
        
        JLabel totalTaxLabel = new JLabel("Total Taxes: ");
        totalTaxLabel.setBounds(50, 550, 200, 25);
        
        JLabel totalLabel = new JLabel("Total: ");
        totalLabel.setBounds(50, 580, 200, 25);

        JButton cancelItemButton = new JButton("Cancel Item");
        cancelItemButton.setBounds(50, 620, 200, 50);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(300, 620, 200, 50);

        // Right Rectangle with Placeholder
        JLabel placeholder = new JLabel("PleaseScanYourItems.jpg"); // Replace with an actual image for final product.
        placeholder.setBorder(border);
        placeholder.setBounds(600, 100, 450, 400);

        JButton itemCodeButton = new JButton("Item Code");
        itemCodeButton.setBounds(725, 620, 200, 50);
        
        // "Assist Mode" button - Adjusted the position to avoid clipping
        JButton assistModeButton = new JButton("Assist Mode");
        assistModeButton.setBounds(950, 620, 155, 50);

        // Adding components to JFrame
        this.add(myCartLabel);
        this.add(scrollPane);
        this.add(subtotalLabel);
        this.add(totalTaxLabel);
        this.add(totalLabel);
        this.add(cancelItemButton);
        this.add(checkoutButton);
        this.add(placeholder);
        this.add(itemCodeButton);
        this.add(assistModeButton);

        // JFrame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ScanScreen();
    }
}
