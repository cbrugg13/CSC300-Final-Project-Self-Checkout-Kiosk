import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;

public class ScanScreen extends JFrame {
    JList<String> itemList; // This will display the items in the cart.
    DefaultListModel<String> listModel; // The model for the JList.
    WeightDetectionSystem weightSystem = new WeightDetectionSystem(0f, 10f, true);

    // Calculation variables
    double subtotal = 0.0;
    double tax = 0.0;
    double total = 0.0;
    final double TAX_RATE = 0.09;  // 9%
    final double BAG_FEE = 0.10;  // 10 cents per bag

    private boolean isWeightCorrect(Item item) {
        if (!item.requiresWeightCheck()) {
            return true; // if the item doesn't need to be weighed, always return true
        }

        if (item.causesWeightError()) {
            return false; // this simulates the weight error
        }

        // Here, one could add logic to check the weight system and the weight of the item.
        return true; // assume it's correct if no errors were simulated.
    }

    private void showBagPrompt() {
        JDialog bagDialog = new JDialog(this, "Bag Count", true);
        bagDialog.setLayout(new GridBagLayout());
        bagDialog.setSize(300, 150);
        
        GridBagConstraints gbc = new GridBagConstraints();
    
        JLabel bagCountLabel = new JLabel("How many bags have you used today?");
        JTextField bagCountField = new JTextField("0", 3);
        
        JButton plusButton = new JButton("+");
        plusButton.addActionListener(e -> {
            int count = Integer.parseInt(bagCountField.getText());
            bagCountField.setText(String.valueOf(++count));
        });
    
        JButton minusButton = new JButton("-");
        minusButton.addActionListener(e -> {
            int count = Integer.parseInt(bagCountField.getText());
            if (count > 0) {
                bagCountField.setText(String.valueOf(--count));
            }
        });
    
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> {
            double bagFee = Integer.parseInt(bagCountField.getText()) * BAG_FEE;
            total += bagFee;  // Add bag fee to total
        
            new CheckoutScreen(Integer.parseInt(bagCountField.getText()), listModel, subtotal, tax, total);
            bagDialog.dispose();
            ScanScreen.this.dispose();
        });
        
        // Add the bag count label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        bagDialog.add(bagCountLabel, gbc);
    
        // Add the minus button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        bagDialog.add(minusButton, gbc);
    
        // Add the text field
        gbc.gridx = 2;
        gbc.gridy = 1;
        bagDialog.add(bagCountField, gbc);
    
        // Add the plus button
        gbc.gridx = 3;
        gbc.gridy = 1;
        bagDialog.add(plusButton, gbc);
    
        // Add the enter button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        bagDialog.add(enterButton, gbc);
        
        bagDialog.setVisible(true);
    }

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

        JLabel subtotalLabel = new JLabel("Subtotal: $0.00");
        subtotalLabel.setBounds(50, 520, 200, 25);

        JLabel totalTaxLabel = new JLabel("Total Taxes: $0.00");
        totalTaxLabel.setBounds(50, 550, 200, 25);

        JLabel totalLabel = new JLabel("Total: $0.00");
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
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBagPrompt();
            }
        });

        JButton scanButton = new JButton("Scan");
        scanButton.setBounds(450, 620, 200, 50);
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mock scanning an item
                Item scannedItem = Database.getRandomItem();
        
                if (scannedItem == null) {
                    JOptionPane.showMessageDialog(null, "No items in the database!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                if (scannedItem.requiresWeightCheck() && !isWeightCorrect(scannedItem)) {
                    // Show a pop-up that disappears after 4 seconds
                    JOptionPane optionPane = new JOptionPane("Weight error. Please scan again.", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Error");
                    Timer timer = new Timer(4000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            dialog.setVisible(false);
                            dialog.dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    dialog.setVisible(true);
        
                    // On second scan, prompt employee check
                    if (!isWeightCorrect(scannedItem)) {
                        // Notify the employee (simulated for now with a popup)
                        JOptionPane.showMessageDialog(null, "A Team Member Is On Their Way To Assist", "Error", JOptionPane.ERROR_MESSAGE);
                        // In a real-world application, we'd probably integrate with the NotificationSystem to send the notification to the employee.
                    }
                } else {
                    // Add the item to the cart
                    listModel.addElement(scannedItem.getName() + " - $" + scannedItem.getPrice());
        
                    // Update the subtotal
                    subtotal += scannedItem.getPrice();
        
                    // Calculate tax and total
                    tax = subtotal * TAX_RATE;
                    total = subtotal + tax;
        
                    // Update the labels
                    subtotalLabel.setText("Subtotal: $" + String.format("%.2f", subtotal));
                    totalTaxLabel.setText("Total Taxes: $" + String.format("%.2f", tax));
                    totalLabel.setText("Total: $" + String.format("%.2f", total));
                }
            }
        });
        
        // Right Rectangle with Placeholder
        JLabel placeholder = new JLabel("*PleaseScanYourItems.jpg*"); // Replace with an actual image for final product.
        placeholder.setBorder(border);
        placeholder.setBounds(600, 100, 450, 400);

        JButton itemCodeButton = new JButton("Item Code");
        itemCodeButton.setBounds(675, 620, 200, 50);
        itemCodeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new NumericKeypadScreen(ScanScreen.this, NumericKeypadScreen.Mode.PLU_CODE);
        }
        });

        // "Assist Mode" button - Adjusted the position from StartScreen to avoid clipping
        JButton assistModeButton = new JButton("Assist Mode");
        assistModeButton.setBounds(900, 620, 155, 50);

        // Listener for Assist Mode Button
        assistModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssistModeScreen(ScanScreen.this, listModel);  // Open the assist mode screen and pass the current scan screen.
                ScanScreen.this.setVisible(false); // Hide the ScanScreen while in AssistMode.
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
        this.add(scanButton);
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
