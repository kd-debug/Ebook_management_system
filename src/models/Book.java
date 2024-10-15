package models;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private String condition; // new or old
    private String imagePath;

    public Book(int id, String title, String author, double price, String condition, String imagePath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.condition = condition;
        this.imagePath = imagePath;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getCondition() {
        return condition;
    }

    public String getImagePath() {
        return imagePath;
    }
}
