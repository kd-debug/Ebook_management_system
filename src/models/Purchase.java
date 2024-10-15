package models;

import java.sql.Timestamp;

public class Purchase {
    private int id;
    private int userId;
    private int bookId;
    private Timestamp purchaseDate;

    public Purchase(int id, int userId, int bookId, Timestamp purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.purchaseDate = purchaseDate;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getBookId() { return bookId; }
    public Timestamp getPurchaseDate() { return purchaseDate; }
}
