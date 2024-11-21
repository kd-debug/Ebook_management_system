package views;

import models.Book;
import views.BookstoreFrame.QuantityCellEditor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;  
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BookstoreFrame extends JFrame {
    private List<Book> cart = new ArrayList<>();
    private JButton cartButton;
    private JLabel cartLabel;
    private JTable cartTable;
    private JLabel totalPriceLabel;
    private DefaultTableModel cartTableModel;
    
    public BookstoreFrame(int userId) {
        setTitle("EBOOKS - Bookstore");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel with BoxLayout to organize books into sections
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical sections

        // Scrollable Panel for Books
        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Adding the specific books and remaining random books
        addBooks(mainPanel);

        // Cart Button
        JButton cartButton = new JButton("VIEW CART");
        cartButton.setBounds(10, 10, 100, 40);
        cartButton.setFont(new Font("Arial", Font.BOLD, 16));
        cartButton.setBackground(Color.getHSBColor(208, 75, 65));
        cartButton.setForeground(Color.BLACK);
        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCart();
            }

        });
        cartLabel = new JLabel("Cart: 0 items");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartLabel.setForeground(Color.RED);

        JPanel cartPanel = new JPanel();
        cartPanel.add(cartButton);
        cartPanel.add(cartLabel);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(cartButton, BorderLayout.NORTH);
        setVisible(true);
    }

    private void addBooks(JPanel panel) {
        // Heading for Novels Section
        panel.add(createSectionTitle("Novels"));
        panel.add(createBookRow(new Book(1, "Rich Dad Poor Dad", "Robert Kiyosaki", 500.00, "new", "src/images/rich_dad_poor_dad.jpeg"),
                new Book(2, "Pride and Prejudice", "Jane Austen", 300.00, "new", "src/images/pride_and_prejudice.jpg"),
                new Book(3, "The Diary of a Young Girl", "Anne Frank", 450.00, "new", "src/images/diary_of_young_girl.jpg"),
                new Book(4, "A Little Life", "Hanya Yanagihara", 600.00, "new", "src/images/a_little_life.jpg"),
                new Book(5, "Never Lie", "Freida McFadden", 400.00, "new", "src/images/never_lie.jpg")));
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Padding between sections

        // Heading for Programming Section
        panel.add(createSectionTitle("Programming"));
        panel.add(createBookRow(new Book(6, "DSA Using Java", "Abdul Bari", 550.00, "new", "src/images/dsa_using_java.jpg"),
                new Book(7, "Java by Balguruswamy", "Balguruswamy", 600.00, "new", "src/images/java_by_balguruswamy.jpg"),
                new Book(8, "Programming with C++", "Bjarne Stroustrup", 500.00, "new", "src/images/programming_with_cpp.jpeg"),
                new Book(9, "C Programming", "Dennis Ritchie", 450.00, "new", "src/images/c_programming.jpg"),
                new Book(10, "Python Programming", "Clive Campbell", 400.00, "new", "src/images/python.jpg")));
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Padding between sections

        // Heading for Science Fiction Section
        panel.add(createSectionTitle("Science Fiction"));
        panel.add(createBookRow(new Book(11, "The Martian", "Andy Weir", 350.00, "new", "src/images/martian.jpg"),
                new Book(12, "Dune", "Frank Herbert", 499.00, "new", "src/images/dune.jpg"),
                new Book(13, "The last watch", "J. S. Dewes", 450.00, "new", "src/images/last.jpg"),
                new Book(14, "Ender's Game", "Orson Scott Card", 550.00, "new", "src/images/enders_game.jpg"),
                new Book(15, "The Left Hand of Darkness", "Ursula K. Le Guin", 480.00, "new", "src/images/left.jpeg")));
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Padding between sections

        // Heading for Romance Section
        panel.add(createSectionTitle("Romance"));
        panel.add(createBookRow(new Book(16, "The Fault in Our Stars", "John Green", 300.00, "new", "src/images/stars.jpg"),
                new Book(17, "Haunting Adeline", "H. D. Carlton", 400.00, "new", "src/images/haunting.jpg"),
                new Book(18, "Me Before You", "Jojo Moyes", 350.00, "new", "src/images/before.jpg"),
                new Book(19, "It Ends with Us", "Colleen Hoover", 450.00, "new", "src/images/ends.jpg"),
                new Book(20, "It Starts with Us", "Colleen Hoover", 400.00, "new", "src/images/start.jpg")));
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Padding between sections

        // Heading for Educational Section
        panel.add(createSectionTitle("Educational"));
        panel.add(createBookRow(new Book(21, "Science for Class 10", "NCERT", 200.00, "new", "src/images/sci10.jpg"),
                new Book(22, "Physics for Class 12", "NCERT", 250.00, "new", "src/images/phy12.jpeg"),
                new Book(23, "Engish for Class 12", "NCERT", 260.00, "new", "src/images/eng12.jpg"),
                new Book(24, "Maths for Class 11", "NCERT", 300.00, "new", "src/images/maths11.jpg"),
                new Book(25, "Geography for Class 9", "NCERT", 280.00, "new", "src/images/geo9.jpg")));
    }

    private JPanel createBookRow(Book... books) {
        // Use GridLayout to ensure justified alignment with even spacing
        JPanel rowPanel = new JPanel(new GridLayout(1, books.length, 20, 20)); // 1 row, number of books columns, horizontal gap, vertical gap
        rowPanel.setLayout(new GridLayout(1, books.length, 20, 20)); // Horizontal and vertical gaps between components
    
        // Add each book to the row
        for (Book book : books) {
            rowPanel.add(createBookCard(book));
        }
    
        return rowPanel;
    }
    
private JPanel createBookCard(Book book) {
    JPanel card = new JPanel(new BorderLayout());
    card.setPreferredSize(new Dimension(220, 360)); // Adjust size of each book card

    // Book cover image
    ImageIcon bookCover = new ImageIcon(book.getImagePath());
    JLabel coverLabel = new JLabel(new ImageIcon(bookCover.getImage().getScaledInstance(160, 240, Image.SCALE_SMOOTH)));

    // Title
    JLabel titleLabel = new JLabel(book.getTitle(), JLabel.CENTER);
    try {
        // Load Poppins font
        InputStream fontStream = new FileInputStream("src/fonts/Poppins-Bold.ttf");
        Font poppinsFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(18f); // Set font size
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(poppinsFont);
        titleLabel.setFont(poppinsFont.deriveFont(Font.BOLD)); // Set font to bold
    } catch (Exception e) {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Fallback font
        e.printStackTrace();
    }
    
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Bottom padding

    // Author
    JLabel authorLabel = new JLabel("By " + book.getAuthor(), JLabel.CENTER);
    authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    // Price (in INR)
    JLabel priceLabel = new JLabel("₹ " + book.getPrice(), JLabel.CENTER);
    priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

    // Add to Cart button
    JButton addToCartButton = new JButton("Add to Cart");
    addToCartButton.setBackground(Color.getHSBColor(208, 75, 65));
    addToCartButton.setForeground(Color.BLACK);
    addToCartButton.setFont(new Font("Arial", Font.BOLD, 12));
    addToCartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cart.add(book);
            JOptionPane.showMessageDialog(null, book.getTitle() + " added to cart.");
            updateCartLabel(); // Update cart count
        }
    });

    JButton buyNowButton = new JButton("Buy Now");
    buyNowButton.setBackground(Color.LIGHT_GRAY);
    buyNowButton.setForeground(Color.BLACK);
    buyNowButton.setFont(new Font("Arial", Font.BOLD, 12));
    buyNowButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Redirect to the payment window
        new PaymentFrame();
    }
});

    // Panel for buttons
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(addToCartButton);
    buttonPanel.add(buyNowButton);

    // Add components to card
    card.add(coverLabel, BorderLayout.NORTH);
    card.add(titleLabel, BorderLayout.CENTER);
    card.add(authorLabel, BorderLayout.SOUTH);
    card.add(priceLabel, BorderLayout.SOUTH);
    card.add(buttonPanel, BorderLayout.SOUTH);

    return card;
}


private JLabel createSectionTitle(String title) {
    JLabel sectionTitle = new JLabel(title, JLabel.CENTER);
        
    try {
        // Load Poppins font (ensure path is correct)
        InputStream fontStream = new FileInputStream("src/fonts/Poppins-Bold.ttf");
        Font poppinsFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(28f);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(poppinsFont);
        sectionTitle.setFont(poppinsFont.deriveFont(Font.BOLD));
    } catch (Exception e) {
        sectionTitle.setFont(new Font("Arial", Font.BOLD, 28)); // Fallback font
        e.printStackTrace();
    }
    
    sectionTitle.setForeground(Color.DARK_GRAY); // Color of the text
    sectionTitle.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
    sectionTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add padding at top and bottom
    
    return sectionTitle;
}

    private void updateCartLabel() {
        cartLabel.setText("Cart: " + cart.size() + " items");
    }
    private void viewCart() {
        JFrame cartFrame = new JFrame("Your Cart");
        cartFrame.setSize(800, 600);
        cartFrame.setLayout(new BorderLayout());
    
        String[] columnNames = {"Book Cover", "Book Description", "Quantity", "Price (INR)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable cartTable = new JTable(tableModel) {
            // Override the method to ensure proper rendering of images
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return ImageIcon.class; // Image column
                }
                return super.getColumnClass(columnIndex);
            }
        };
    
        // Increase font size for table
        cartTable.setFont(new Font("Arial", Font.PLAIN, 16)); // Set the font size to 16
        cartTable.setRowHeight(120); // Increase row height for better visibility
    
        double totalPrice = 0.0;
    
        for (Book book : cart) {
            ImageIcon bookCover = new ImageIcon(book.getImagePath());
            Image scaledImage = bookCover.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
    
            Object[] rowData = {
                scaledIcon, // Image in first column
                "<html><b>" + book.getTitle() + "</b><br>By " + book.getAuthor() + "</html>",
                1, // Default quantity
                String.format("₹%.2f", book.getPrice())
            };
    
            tableModel.addRow(rowData);
            totalPrice += book.getPrice();
        }
    
        Object[] totalRow = {"", "Total", "", String.format("₹%.2f", totalPrice)};
        tableModel.addRow(totalRow);
    
        cartTable.setDefaultEditor(Object.class, null); // Disable editing for the table
    
        cartFrame.add(new JScrollPane(cartTable), BorderLayout.CENTER);
    
        JButton paymentButton = new JButton("Make Payment");
        paymentButton.setBackground(Color.GREEN);
        paymentButton.addActionListener(e -> {
        new PaymentFrame();
    });
        cartFrame.add(paymentButton, BorderLayout.SOUTH);
    
        cartFrame.setVisible(true);
    }
    
    class QuantityCellEditor extends DefaultCellEditor {
    private JPanel panel;
    private JLabel quantityLabel;
    private JButton increaseButton;
    private JButton decreaseButton;
    private DefaultTableModel tableModel; // Declare the table model
    private int row;
    public QuantityCellEditor(DefaultTableModel tableModel  ) {
        super(new JCheckBox());
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        quantityLabel = new JLabel("1");

        increaseButton = new JButton("+");
        increaseButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityLabel.getText());
            quantity++;
            quantityLabel.setText(String.valueOf(quantity));
        });

        decreaseButton = new JButton("-");
        decreaseButton.addActionListener(e -> {
            int quantity = Integer.parseInt(quantityLabel.getText());
            if (quantity > 1) {
                quantity--;
                quantityLabel.setText(String.valueOf(quantity));
            }
        });

        panel.add(decreaseButton);
        panel.add(quantityLabel);
        panel.add(increaseButton);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        quantityLabel.setText(value.toString());
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        int quantity = Integer.parseInt(quantityLabel.getText());
        double pricePerUnit = cart.get(row).getPrice();
        double newPrice = quantity * pricePerUnit;
    
        String formattedPrice = String.format("₹%.2f", newPrice);
        tableModel.setValueAt(formattedPrice, row, 3); // Assuming price is in column index 3
    
        double totalPrice = 0.0;
        for (int i = 0; i < tableModel.getRowCount() - 1; i++) { // Exclude the last row (total row)
            String priceStr = (String) tableModel.getValueAt(i, 3);
            totalPrice += Double.parseDouble(priceStr.replace("₹", "").replace(",", ""));
        }
    
        tableModel.setValueAt(String.format("₹%.2f", totalPrice), tableModel.getRowCount() - 1, 3);
    
        return quantity; // Return the updated quantity
    }
    
}
    private void updatePrice(DefaultTableModel tableModel, int row, int quantity, double pricePerUnit) {
        double newPrice = quantity * pricePerUnit;
        String formattedPrice = String.format("₹%.2f", newPrice);
        
        // Update the price in the appropriate row
        tableModel.setValueAt(formattedPrice, row, 3); // Assuming price is in column index 3
    
        // Recalculate total price
        double totalPrice = 0.0;
        for (int i = 0; i < tableModel.getRowCount() - 1; i++) { // Exclude the last row (total row)
            String priceStr = (String) tableModel.getValueAt(i, 3);
            totalPrice += Double.parseDouble(priceStr.replace("₹", "").replace(",", ""));
        }
        
        // Update the total price row
        tableModel.setValueAt(String.format("₹%.2f", totalPrice), tableModel.getRowCount() - 1, 3);
    }
    

private void updateTotalPriceLabel(double[] totalPrice, JPanel cartPanel) {
    double newTotal = 0.0;

    Component[] components = cartPanel.getComponents();
    for (Component comp : components) {
        if (comp instanceof JLabel) {
            JLabel label = (JLabel) comp;
            // Look for labels that represent prices (starting with "₹")
            if (label.getText().startsWith("₹")) {
                String priceText = label.getText().replace("₹", "");
                try {
                    newTotal += Double.parseDouble(priceText);  // Accumulate total
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // Handle invalid price format, if any
                }
            }
        }
    }
    // Update the total price
    totalPrice[0] = newTotal;

    // Update the total price label at the bottom of the cart
    JPanel totalPanel = (JPanel) cartPanel.getParent().getComponent(1); // Access the bottom panel
    JLabel totalPriceLabel = (JLabel) totalPanel.getComponent(1);  // Access the total price label
    totalPriceLabel.setText("₹" + totalPrice[0]);
}


    private void updateTotalPriceLabel(JLabel totalPriceLabel) {
        double newTotal = 0.0;
    
        // Iterate through the cart to recalculate total price
        for (Book book : cart) {
            // Fetch the price for the current book based on its quantity in the cart
            int quantity = getBookQuantity(book);
            newTotal += book.getPrice() * quantity;
        }
    
        // Update the total price label at the bottom of the cart
        totalPriceLabel.setText("₹" + newTotal);
    }
    
    // This function should get the quantity of each book (you can modify based on your cart structure)
    private int getBookQuantity(Book book) {
        return 1;
    }
    
    public static void main(String[] args) {
        new BookstoreFrame(1);  // Example to test the bookstore frame
    }
}