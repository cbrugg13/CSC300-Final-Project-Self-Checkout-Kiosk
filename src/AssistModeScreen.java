import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AssistModeScreen extends JFrame {
    JList<String> itemList;
    DefaultListModel<String> listModel;

    private ScanScreen scanScreenReference;

    public AssistModeScreen(ScanScreen scanScreen, DefaultListModel<String> sharedListModel) {
        this.scanScreenReference = scanScreen;
        this.listModel = sharedListModel;  // Set the shared listModel
        this.itemList = new JList<>(listModel);  // Create the JList using the shared listModel
    
        // Left Rectangle with Cart Details
        JLabel myCartLabel = new JLabel("Assist Mode");
        myCartLabel.setBounds(100, 50, 200, 30);
        myCartLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
    
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
        cancelItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!listModel.isEmpty()) { // Check if the list isn't empty
                    listModel.removeElementAt(listModel.size() - 1); // Remove the last item
                }
            }
        });

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBounds(225, 620, 200, 50);

        // Right Rectangle with Assist Mode Options
        JButton verifyAgeButton = new JButton("Verify Age");
        verifyAgeButton.setBounds(675, 225, 200, 50);
        verifyAgeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NumericKeypadScreen(scanScreenReference, NumericKeypadScreen.Mode.VERIFY_AGE);  // Open the numeric keypad in age verification mode
            }
        });

        JButton overrideWeightButton = new JButton("Override Weighed Item");
        overrideWeightButton.setBounds(675, 300, 200, 50);

        JButton giftCardAmountButton = new JButton("Enter Gift Card Amount");
        giftCardAmountButton.setBounds(675, 375, 200, 50);
        giftCardAmountButton.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                new NumericKeypadScreen(scanScreenReference, NumericKeypadScreen.Mode.GIFT_CARD_AMOUNT);  // Open the numeric keypad in gift card amount mode
            }
        });

        JButton exitAssistModeButton = new JButton("Exit Assist Mode");
        exitAssistModeButton.setBounds(900, 620, 155, 50);
        exitAssistModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AssistModeScreen.this.dispose();  // Close the assist mode screen.
            scanScreenReference.setVisible(true);  // Show the original scan screen.
            }
        });

        // Adding components to JFrame
        this.add(myCartLabel);
        this.add(scrollPane);
        this.add(subtotalLabel);
        this.add(totalTaxLabel);
        this.add(totalLabel);
        this.add(cancelItemButton);
        this.add(checkoutButton);
        this.add(verifyAgeButton);
        this.add(overrideWeightButton);
        this.add(giftCardAmountButton);
        this.add(exitAssistModeButton);

        // JFrame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 750);
        this.setLayout(null);
        this.setVisible(true);
    }
}
