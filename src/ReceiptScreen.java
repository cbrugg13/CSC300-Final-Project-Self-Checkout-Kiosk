import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class ReceiptScreen extends JFrame {
    JButton printReceiptButton;
    JButton emailReceiptButton;
    JButton noReceiptButton;
    JLabel receiptTitleLabel;

    public ReceiptScreen() {
        // Title of the receipt
        receiptTitleLabel = new JLabel("Your Receipt");
        receiptTitleLabel.setBounds(300, 50, 450, 50);
        receiptTitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(receiptTitleLabel);

        // Buttons in the ReceiptScreen
        printReceiptButton = new JButton("Print receipt");
        printReceiptButton.setBounds(300, 200, 200, 50);
        printReceiptButton.addActionListener(e -> showFinalScreen());
        add(printReceiptButton);

        emailReceiptButton = new JButton("Email receipt");
        emailReceiptButton.setBounds(300, 300, 200, 50);
        emailReceiptButton.addActionListener(e -> showFinalScreen());
        add(emailReceiptButton);

        noReceiptButton = new JButton("No receipt");
        noReceiptButton.setBounds(300, 400, 200, 50);
        noReceiptButton.addActionListener(e -> showFinalScreen());
        add(noReceiptButton);

        // Similar frame styling as the CheckoutScreen
        setSize(1200, 750);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showFinalScreen() {
        this.dispose();
        
        JFrame finalScreen = new JFrame();

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // ---------------------------------------- LABEL ------------------------------------------
        JLabel messageLabel = new JLabel();
        messageLabel.setText("<html>Thank You For Shopping With Us!<br>Please Take Your Items</html>");
        messageLabel.setHorizontalTextPosition(JLabel.CENTER);
        messageLabel.setBorder(border);
        messageLabel.setVerticalTextPosition(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setBounds(350, 200, 500, 200);
        messageLabel.setFont(new Font("Calibri", Font.PLAIN, 36));
        finalScreen.add(messageLabel);        
        // -----------------------------------------------------------------------------------------

        // ======================================= BUTTON ==========================================
        JButton closeButton = new JButton();
        closeButton.setBounds(450, 450, 300, 100);
        closeButton.addActionListener(e -> System.exit(0));  // Close the application when this button is clicked
        closeButton.setText("CLOSE");
        closeButton.setFocusable(false);
        closeButton.setHorizontalTextPosition(JButton.CENTER);
        closeButton.setVerticalTextPosition(JButton.CENTER);
        closeButton.setFont(new Font("Calibri", Font.BOLD, 32));
        finalScreen.add(closeButton);
        // ======================================= BUTTON ==========================================

        finalScreen.setSize(1200, 700);
        finalScreen.setLayout(null);
        finalScreen.setVisible(true);
        finalScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(5000, e -> {
            finalScreen.dispose();
            new StartScreen();
        });
        timer.setRepeats(false);  // Important: make sure the timer only runs once
        timer.start();
    }
}
