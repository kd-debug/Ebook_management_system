package views;

import db.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("EBOOKS - Login");
        setSize(450, 550); // Increased size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on the screen

        // Main panel for login form
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Header with EBOOKS title
        JLabel headerLabel = new JLabel("EBOOKS", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 32));
        headerLabel.setForeground(new Color(51, 153, 255));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(headerLabel);

        // Icon
        JLabel iconLabel = new JLabel(new ImageIcon("src/views/usricon.png"), JLabel.CENTER);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(iconLabel);

        // Email field
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(350, 40)); // Increased size
        emailField.setPreferredSize(new Dimension(350, 40));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        emailField.setBorder(BorderFactory.createTitledBorder("Email"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(emailField);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(350, 40)); // Increased size
        passwordField.setPreferredSize(new Dimension(350, 40));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(passwordField);

        // Sign in button
        JButton loginButton = new JButton("Sign In");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(350, 50)); // Increased size
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);

        // Register link for new users
        JLabel registerLabel = new JLabel("New user? Register here");
        registerLabel.setForeground(new Color(51, 153, 255));
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openRegisterFrame();
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(registerLabel);

        add(panel);
        setVisible(true);
    }

    private void authenticateUser() {
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database authentication
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // If login is successful, open the bookstore
                int userId = rs.getInt("id");
                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();  // Close the login frame
                new BookstoreFrame(userId);  // Open the bookstore
            } else {
                JOptionPane.showMessageDialog(null, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void openRegisterFrame() {
        dispose();  // Close the login frame
        new RegisterFrame();  // Open the register frame
    }
}
