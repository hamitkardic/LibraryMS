import com.library.repository.UserDAO;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Main::createLoginUI);

    }

    private static void createLoginUI(){
        JFrame frame = new JFrame("Library Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        panel.add(userLabel);
        panel.add(usernameField);
        panel.add(passLabel);
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            UserDAO UserDAO = new UserDAO();

            if(UserDAO.login(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials", "Login failed", JOptionPane.ERROR_MESSAGE);
                passwordField.setText("");
            }

        });


    }
}
