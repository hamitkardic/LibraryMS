package com.library.ui;

import javax.swing.*;
import java.awt.*;

public class LibraryFrame extends JFrame {

    public LibraryFrame(String username) {
        setTitle("Library Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        menuBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));

        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");

        JLabel separator = new JLabel("|");

        menuBar.add(logoutItem);
        menuBar.add(separator);
        menuBar.add(exitItem);

        setJMenuBar(menuBar);

        logoutItem.addActionListener(e -> {
           dispose();
           new LoginFrame();
        });

        exitItem.addActionListener(e -> System.exit(0));


        JLabel welcomeLabel = new JLabel("Welcome to the library, " + username, SwingConstants.CENTER);

        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        add(welcomeLabel, BorderLayout.CENTER);
        setVisible(true);


    }
}
