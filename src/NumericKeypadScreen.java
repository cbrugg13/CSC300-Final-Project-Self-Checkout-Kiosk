import javax.swing.*;
import java.awt.*;

public class NumericKeypadScreen extends JFrame {

    private JTextField inputField; // To display entered numbers
    private StringBuilder inputStringBuilder; // To manage the entered number
    private ScanScreen scanScreenReference;
    private Mode currentMode;

    // Enum to represent the different modes
    public enum Mode {
        PLU_CODE,
        VERIFY_AGE,
        GIFT_CARD_AMOUNT
    }

    public NumericKeypadScreen(ScanScreen scanScreen, Mode mode) {
        this.scanScreenReference = scanScreen;
        this.inputStringBuilder = new StringBuilder();
        this.currentMode = mode;

        switch (currentMode) {
            case PLU_CODE:
                setTitle("Enter PLU Code");
                break;
            case VERIFY_AGE:
                setTitle("Verify Age");
                break;
            case GIFT_CARD_AMOUNT:
                setTitle("Enter Gift Card Amount");
                break;
        }

        setSize(300, 400);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setHorizontalAlignment(JTextField.RIGHT);

        add(inputField, BorderLayout.NORTH);

        JPanel keypadPanel = new JPanel(new GridLayout(4, 3));

        for (int i = 1; i <= 9; i++) {
            addButton(String.valueOf(i), keypadPanel);
        }

        addButton("0", keypadPanel);

        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(e -> handleEnterAction());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            inputStringBuilder.setLength(0);
            inputField.setText("");
        });

        keypadPanel.add(enterButton);
        keypadPanel.add(clearButton);

        add(keypadPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void handleEnterAction() {
        String inputValue = inputStringBuilder.toString();

        switch (currentMode) {
            case PLU_CODE:
                handlePLUCode(inputValue);
                break;
            case VERIFY_AGE:
                handleAgeVerification(inputValue);
                break;
            case GIFT_CARD_AMOUNT:
                handleGiftCardAmount(inputValue);
                break;
        }
    }

    private void handlePLUCode(String pluCode) {
        if (!pluCode.isEmpty()) {
            try {
                int pluCodeInt = Integer.parseInt(pluCode);
                Item item = Database.getItemByPLU(pluCodeInt);
                if (item != null) {
                    scanScreenReference.listModel.addElement(item.getName() + " - $" + item.getPrice());
                    this.dispose();
                } else {
                    showError("Invalid PLU Code.");
                }
            } catch (NumberFormatException ex) {
                showError("Invalid PLU Code.");
            }
        }
    }

    private void handleAgeVerification(String age) {
        // Logic to handle age verification can be added here
        // Legal age is 21
        try {
            int ageInt = Integer.parseInt(age);
            if (ageInt >= 21) {
                JOptionPane.showMessageDialog(this, "Age Verified.", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                showError("Age not valid for purchase.");
            }
        } catch (NumberFormatException ex) {
            showError("Invalid Age Entered.");
        }
    }

    private void handleGiftCardAmount(String amount) {
        // Logic to handle gift card amount can be added here
        try {
            double giftCardAmount = Double.parseDouble(amount);
            if (giftCardAmount > 0) {
                // Add gift card logic here
                JOptionPane.showMessageDialog(this, "Gift card of $" + giftCardAmount + " added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                showError("Invalid Gift Card Amount.");
            }
        } catch (NumberFormatException ex) {
            showError("Invalid Gift Card Amount Entered.");
        }
    }

    private void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        inputStringBuilder.setLength(0);
        inputField.setText("");
    }

    private void addButton(String text, JPanel parent) {
        JButton button = new JButton(text);
        button.addActionListener(e -> {
            inputStringBuilder.append(text);
            inputField.setText(inputStringBuilder.toString());
        });
        parent.add(button);
    }
}
