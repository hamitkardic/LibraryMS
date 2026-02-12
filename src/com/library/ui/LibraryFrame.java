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

        JPanel booksGrid = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;

        java.util.List<String[]> books = java.util.Arrays.asList(
                new String[]{"The Count of Monte Cristo", "Alexandre Dumas"},
                new String[]{"The Hobbit", "J.R.R. Tolkien"},
                new String[]{"Metamorphosis", "Franz Kafka"}
        );

        int column = 0;
        int row = 0;
        int columnsPerRow = 3;

        for (String[] book : books) {
            gbc.gridx = column;
            gbc.gridy = row;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.weighty = 0;

            booksGrid.add(createBookCard(book[0], book[1]), gbc);

            column++;
            if (column == columnsPerRow) {
                column = 0;
                row++;
            }
        }

        GridBagConstraints filler = new GridBagConstraints();
        filler.gridx = 0;
        filler.gridy = row + 1;
        filler.weighty = 1;
        booksGrid.add(Box.createVerticalGlue(), filler);


        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapper.add(booksGrid);
        JScrollPane scrollPane = new JScrollPane(wrapper);
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
        card.add(textPanel, BorderLayout.CENTER);

        card.add(authorLabel, BorderLayout.SOUTH);

        card.setPreferredSize(new Dimension(150, 200));
        card.setMaximumSize(new Dimension(180, 220));

        return card;
    }

    private JLabel separator() {
        JLabel sep = new JLabel("|");
        return sep;
    }
}
