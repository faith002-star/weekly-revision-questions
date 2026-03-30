import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    // GUI Components
    private JTextField display;
    private JPanel buttonPanel;
    
    // Calculation variables
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public SimpleCalculator() {
        // 1. JFrame Setup
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --- DISPLAY (JTextField) ---
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // --- BUTTONS (GridLayout 5x4) ---
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        // Button labels in order of grid placement
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle Clear
        if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        } 
        // Handle Result (Equals)
        else if (command.equals("=")) {
            if (!display.getText().isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                calculate();
            }
        } 
        // Handle Operators
        else if (command.equals("+") || command.equals("-") || 
                 command.equals("*") || command.equals("/")) {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = command.charAt(0);
                display.setText(""); // Clear for next number
            }
        } 
        // Handle Numbers and Decimal
        else {
            display.setText(display.getText() + command);
        }
    }

    private void calculate() {
        switch (operator) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/': 
                if (num2 == 0) {
                    display.setText("Error: Div by 0");
                    return;
                }
                result = num1 / num2; 
                break;
        }
        
        // Display result (removes .0 if it's a whole number for cleaner look)
        if (result % 1 == 0) {
            display.setText(String.valueOf((int) result));
        } else {
            display.setText(String.valueOf(result));
        }
        num1 = result; // Allow for chain calculations
    }

    public static void main(String[] args) {
        // Ensure UI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
}