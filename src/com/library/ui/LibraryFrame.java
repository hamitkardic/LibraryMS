package com.library.ui;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {

    public LibraryFrame(String username) {
        setTitle("Library Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome to the library, " + username, SwingConstants.CENTER);

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(welcomeLabel, BorderLayout.CENTER);
        setVisible(true);


    }
}
