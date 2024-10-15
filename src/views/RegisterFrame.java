package views;

import db.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public RegisterFrame() {
        setTitle("EBOOKS - Register");
        setSize(450, 550); // Same size as LoginFrame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window on the screen

        // Main panel for register form
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Header with EBOOKS title
        JLabel headerLabel = new JLabel("EBOOKS", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Same font size as LoginFrame
        headerLabel.setForeground(new Color(51, 153, 255)); // Same color as LoginFrame
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(headerLabel);

        // Icon
        JLabel iconLabel = new JLabel(new ImageIcon("path/to/user_icon.png"), JLabel.CENTER);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(iconLabel);

        // Email field (moved to the top)
        emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(350, 40)); // Increased size
        emailField.setPreferredSize(new Dimension(350, 40));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16)); // Same font as in LoginFrame
        emailField.setBorder(BorderFactory.createTitledBorder("Email")); // Titled border
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(emailField);

        // Username field (moved below email)
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(350, 40)); // Increased size
        usernameField.setPreferredSize(new Dimension(350, 40));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16)); // Same font as in LoginFrame
        usernameField.setBorder(BorderFactory.createTitledBorder("Username")); // Titled border
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(usernameField);

        // Password field (stays at the bottom)
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(350, 40)); // Increased size
        passwordField.setPreferredSize(new Dimension(350, 40));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16)); // Same font as in LoginFrame
        passwordField.setBorder(BorderFactory.createTitledBorder("Password")); // Titled border
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(passwordField);

        // Register button
        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setBackground(Color.RED); // Same color as LoginFrame
        registerButton.setForeground(Color.WHITE);
        registerButton.setPreferredSize(new Dimension(350, 50)); // Same size as LoginFrame button
        registerButton.setFont(new Font("Arial", Font.BOLD, 18)); // Same font as LoginFrame
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(registerButton);

        // Action for link to login page
        JLabel loginLabel = new JLabel("Already have an account? Login here");
        loginLabel.setForeground(new Color(51, 153, 255)); // Same color as in LoginFrame
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openLoginFrame();
            }
        });
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginLabel);

        add(panel);
        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database registration
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(RegisterFrame.this, "Registration successful!");
            
            // Redirect to login after successful registration
            dispose();  // Close the register frame
            new LoginFrame();  // Open the login frame
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void openLoginFrame() {
        dispose();  // Close the register frame
        new LoginFrame();  // Open the login frame
    }
}
