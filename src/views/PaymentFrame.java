package views;

import java.awt.*;
import javax.swing.*;

public class PaymentFrame extends JFrame {
    private JTextField nameField;
    private JTextField flatNoField;
    private JTextField areaField;
    private JTextField landmarkField;
    private JTextField pincodeField;
    private JTextField phoneField;
    private JRadioButton codButton;
    private JRadioButton upiButton;
    private JTextField upiIdField;

    public PaymentFrame() {
        setTitle("Payment");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 10, 20, 10); // Padding

        // User details inputs
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Payment Details"), gbc);
        
        gbc.gridwidth = 1; // Reset to default
        gbc.gridy++;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Flat No. / House No.:"), gbc);
        gbc.gridx = 1;
        flatNoField = new JTextField(20);
        add(flatNoField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Area:"), gbc);
        gbc.gridx = 1;
        areaField = new JTextField(20);
        add(areaField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Landmark:"), gbc);
        gbc.gridx = 1;
        landmarkField = new JTextField(20);
        add(landmarkField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Pincode:"), gbc);
        gbc.gridx = 1;
        pincodeField = new JTextField(20);
        add(pincodeField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(20);
        add(phoneField, gbc);

        // Payment options
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Payment Method:"), gbc);
        gbc.gridx = 1;

        JPanel paymentPanel = new JPanel();
        codButton = new JRadioButton("Cash on Delivery");
        upiButton = new JRadioButton("UPI");
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(codButton);
        paymentGroup.add(upiButton);
        paymentPanel.add(codButton);
        paymentPanel.add(upiButton);
        add(paymentPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("UPI ID (if UPI selected):"), gbc);
        gbc.gridx = 1;
        upiIdField = new JTextField(20);
        upiIdField.setEnabled(false);
        add(upiIdField, gbc);

        // Enable/Disable UPI ID field based on payment method selection
        upiButton.addActionListener(e -> upiIdField.setEnabled(true));
        codButton.addActionListener(e -> upiIdField.setEnabled(false));

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns
        JButton submitButton = new JButton(" Pay ");
        submitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Payment processed successfully!");
            dispose(); // Close the payment frame
        });
        add(submitButton, gbc);

        // Styling
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JLabel) {
                component.setFont(new Font("Arial", Font.PLAIN, 18));
                component.setForeground(Color.DARK_GRAY);
            } else if (component instanceof JTextField) {
                component.setFont(new Font("Arial", Font.PLAIN, 18));
            } else if (component instanceof JButton) {
                component.setFont(new Font("Arial", Font.BOLD, 18));
                component.setBackground(Color.GREEN);
                component.setForeground(Color.BLACK);
            }
        }

        setVisible(true);
    }
}
