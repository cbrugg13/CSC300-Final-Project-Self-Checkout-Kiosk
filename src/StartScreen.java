import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class StartScreen extends JFrame implements ActionListener{
    JButton button, button2;

    StartScreen(){
        
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // ---------------------------------------- LABEL ------------------------------------------
        
        JLabel label = new JLabel();
        label.setText("Please Begin Scanning");
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setBorder(border);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(350,200,500,200);
        label.setFont(new Font("Calibri", Font.PLAIN, 40));
        // -----------------------------------------------------------------------------------------


        // ======================================= BUTTON ==========================================
        button = new JButton();
        button.setBounds(450, 450, 300, 100);
        button.addActionListener(this);
        button.setText("START");
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setFont(new Font("Calibri", Font.BOLD, 35));
        // ======================================= BUTTON ==========================================


        // ======================================= BUTTON ==========================================
        button2 = new JButton();
        button2.setBounds(870, 600, 155, 50);
        button2.addActionListener(this);
        button2.setText("Assist Mode");
        button2.setFocusable(false);
        button2.setHorizontalTextPosition(JButton.CENTER);
        button2.setVerticalTextPosition(JButton.CENTER);
        button2.setFont(new Font("Calibri", Font.PLAIN, 15));
        // ======================================= BUTTON ==========================================

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,700);
        this.setLayout(null);
        this.add(button);
        this.add(button2);
        this.add(label);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
            System.out.println("Test.");
        }
    }
}
