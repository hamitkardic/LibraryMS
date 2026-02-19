package com.library.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.library.model.Book;


public class LibraryFrame extends JFrame {

    private java.util.List<Book> books = new java.util.ArrayList<>();
    private JPanel booksGrid;

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

        books.add(new Book("The Count of Monte Cristo", "Alexandre Dumas"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book("Metamorphosis", "Franz Kafka"));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        JButton addBookButton = new JButton("+ Add Book");
        topPanel.add(addBookButton, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

        booksGrid = new JPanel(new GridBagLayout());

        renderBooks();

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        wrapper.add(booksGrid);
        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.setBorder(null);

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel);

        addBookButton.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Enter book title:");
            if (title == null || title.trim().isEmpty()) return;

            String author = JOptionPane.showInputDialog(this, "Enter author name:");
            if (author == null || author.trim().isEmpty()) return;

            books.add(new Book(title.trim(), author.trim()));
            renderBooks();
        });

        setVisible(true);
    }

    private void renderBooks() {
        booksGrid.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.NORTHWEST;

        int column = 0;
        int row = 0;
        int columnsPerRow = 3;

        for (Book book : books) {
            gbc.gridx = column;
            gbc.gridy = row;
            booksGrid.add(createBookCard(book.getTitle(), book.getAuthor()), gbc);

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

        booksGrid.revalidate();
        booksGrid.repaint();

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
