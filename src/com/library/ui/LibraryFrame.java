package com.library.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class LibraryFrame extends JFrame {

    public LibraryFrame(String username) {
        setTitle("Library Management System");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        menuBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 0));


        JMenuItem settingsItem = new JMenuItem("Settings");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");

        menuBar.add(settingsItem);
        menuBar.add(separator());
        menuBar.add(logoutItem);
        menuBar.add(separator());
        menuBar.add(exitItem);

        setJMenuBar(menuBar);

        logoutItem.addActionListener(e -> {
           dispose();
           new LoginFrame();
        });

        exitItem.addActionListener(e -> System.exit(0));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel booksGrid = new JPanel(new GridLayout(0, 3, 8, 8));
        booksGrid.setBorder(new EmptyBorder(10, 0, 0, 0));

        booksGrid.add(createBookCard("The Count of Monte Cristo", "Alexandre Dumas"));
        booksGrid.add(createBookCard("The Hobbit", "J.R.R. Tolkien"));
        booksGrid.add(createBookCard("Metamorphosis", "Franz Kafka"));

        JScrollPane scrollPane = new JScrollPane(booksGrid);
        scrollPane.setBorder(null);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel);

        setVisible(true);
    }

    private JPanel createBookCard(String title, String author) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                new EmptyBorder(6, 6, 6, 6)
        ));

        card.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("<html>" + title + "</html>");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel authorLabel = new JLabel(author);
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        authorLabel.setForeground(Color.DARK_GRAY);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(6));
        textPanel.add(authorLabel);

        card.add(textPanel, BorderLayout.CENTER);

        card.setPreferredSize(new Dimension(0, 120));

        return card;
    }

    private JLabel separator() {
        JLabel sep = new JLabel("|");
        return sep;
    }
}
