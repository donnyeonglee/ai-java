package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {

    private JTextField num1Field;
    private JTextField num2Field;
    private JLabel resultLabel; // For displaying the result at the bottom

    private JButton addBtn;
    private JButton subtractBtn;
    private JButton multiplyBtn;
    private JButton divideBtn;

    public SimpleCalculator() {
        // Frame setup
        setTitle("Proper Calculator");
        setSize(400, 350); // Adjust size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(230, 230, 230)); // Light gray background

        // Title Panel (North)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        titlePanel.setBackground(new Color(50, 50, 50)); // Dark background for title
        JLabel titleLabel = new JLabel("CALCULATOR");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE); // White text
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Center Panel to hold input and buttons
        JPanel centerContainerPanel = new JPanel(new BorderLayout(10, 10));
        centerContainerPanel.setBackground(new Color(230, 230, 230));

        // Input Fields Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center align inputs
        inputPanel.setBackground(Color.WHITE); // White background for input area

        num1Field = new JTextField(10);
        num2Field = new JTextField(10);

        // Styling for text fields
        Dimension textFieldSize = new Dimension(120, 35);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 16);
        Color borderColor = new Color(180, 180, 180); // Light gray border

        num1Field.setPreferredSize(textFieldSize);
        num1Field.setFont(textFieldFont);
        num1Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding inside
        ));

        num2Field.setPreferredSize(textFieldSize);
        num2Field.setFont(textFieldFont);
        num2Field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding inside
        ));

        inputPanel.add(new JLabel("Number 1:"));
        inputPanel.add(num1Field);
        inputPanel.add(new JLabel("Number 2:"));
        inputPanel.add(num2Field);

        centerContainerPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel (GridLayout for operations)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2x2 grid for 4 buttons, with gaps
        buttonPanel.setBackground(new Color(230, 230, 230)); // Match main panel background

        addBtn = new JButton("+");
        subtractBtn = new JButton("-");
        multiplyBtn = new JButton("*");
        divideBtn = new JButton("/");

        // Button styling
        Dimension buttonSize = new Dimension(60, 40); // Smaller button size
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        Color buttonBgColor = new Color(70, 130, 180); // Steel blue for operations
        Color buttonFgColor = Color.WHITE;

        applyButtonStyle(addBtn, buttonBgColor, buttonFgColor, buttonSize, buttonFont);
        applyButtonStyle(subtractBtn, buttonBgColor, buttonFgColor, buttonSize, buttonFont);
        applyButtonStyle(multiplyBtn, buttonBgColor, buttonFgColor, buttonSize, buttonFont);
        applyButtonStyle(divideBtn, buttonBgColor, buttonFgColor, buttonSize, buttonFont);

        buttonPanel.add(addBtn);
        buttonPanel.add(subtractBtn);
        buttonPanel.add(multiplyBtn);
        buttonPanel.add(divideBtn);

        centerContainerPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(centerContainerPanel, BorderLayout.CENTER);

        // Result Display Panel (South)
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        resultPanel.setBackground(new Color(70, 130, 180)); // Match button color
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(Color.WHITE); // White text
        resultPanel.add(resultLabel);
        mainPanel.add(resultPanel, BorderLayout.SOUTH);

        add(mainPanel); // Add the main panel to the frame
    }

    // Helper method to apply consistent button styling
    private void applyButtonStyle(JButton button, Color bgColor, Color fgColor, Dimension size, Font font) {
        button.setPreferredSize(size);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(font);
        button.setBorder(BorderFactory.createRaisedBevelBorder()); // Add a bevel border
        button.setFocusPainted(false); // Remove focus border
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(num1Field.getText().trim()); // Use trim() to remove leading/trailing whitespace
            double num2 = Double.parseDouble(num2Field.getText().trim());
            double result = 0;
            String operation = ((JButton) e.getSource()).getText();

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        resultLabel.setText("Error: Divide by zero");
                        resultLabel.setForeground(Color.RED); // Indicate error with color
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            resultLabel.setText("Result: " + String.format("%.2f", result)); // Format result to 2 decimal places
            resultLabel.setForeground(Color.WHITE); // Reset color to white for successful result

        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input");
            resultLabel.setForeground(Color.RED);
        } catch (Exception ex) {
            resultLabel.setText("Error: An unexpected error occurred");
            resultLabel.setForeground(Color.RED);
            ex.printStackTrace(); // For debugging
        }
    }

    public static void main(String[] args) {
        // Run the GUI creation on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}
