package com.library.ui;

import com.library.repository.UserDAO;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class LoginFrame extends JFrame {
    public LoginFrame() {

        setTitle("Library Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Border defaultUserBorder = usernameField.getBorder();
        Border defaultPassBorder = passwordField.getBorder();
        Border redBorder = new LineBorder(Color.RED, 1);

        JLabel errorLabel = new JLabel(" ");
        errorLabel.setForeground(Color.RED);

        usernameField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent e) { clearError(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { clearError(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { clearError(); }
            private void clearError() {
                errorLabel.setText(" ");
                usernameField.setBorder(defaultUserBorder);
            }
        });

        passwordField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { clearError(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { clearError(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { clearError(); }
            private void clearError() {
                errorLabel.setText(" ");
                passwordField.setBorder(defaultPassBorder);
            }
        });

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        panel.add(errorLabel);
        panel.add(loginButton);

        add(panel);
        getRootPane().setDefaultButton(loginButton);
        setVisible(true);

        loginButton.addActionListener(e -> {

            String username = usernameField.getText().stripTrailing();
            String password = new String(passwordField.getPassword());

            UserDAO userDAO = new UserDAO();

            if (username.isEmpty()) {
                errorLabel.setText("Username is empty!");
                usernameField.setBorder(redBorder);
                shakeWindow();
                return;
            }

            if (password.isEmpty()) {
                errorLabel.setText("Password is empty!");
                passwordField.setBorder(redBorder);
                shakeWindow();
                return;
            }

            if (userDAO.login(username, password)) {
                dispose();
                new LibraryFrame(username);
            }

            else {
                passwordField.setText("");
                usernameField.setBorder(redBorder);
                passwordField.setBorder(redBorder);
                errorLabel.setText("Invalid credentials!");
                shakeWindow();
            }
        });
    }

    private void shakeWindow() {
        Point originalLocation = getLocation();

        int shakeDistance = 5;
        int shakeTimes = 10;

        new Thread(() -> {

            try {
                for (int i = 0; i < shakeTimes; i++) {
                    setLocation(originalLocation.x + (i % 2 == 0 ? shakeDistance : -shakeDistance),
                            originalLocation.y);
                    Thread.sleep(15);
                }

                setLocation(originalLocation);

            } catch (InterruptedException ignored) {}

        }).start();
    }
}
