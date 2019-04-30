package Model;

import javafx.beans.property.SimpleStringProperty;

public class Book {
    private final SimpleStringProperty title;
    private final SimpleStringProperty author;
    private final SimpleStringProperty taken;
    private final SimpleStringProperty ID;

    public Book(String title, String author, boolean taken, int ID) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.taken = new SimpleStringProperty(Boolean.toString(taken));
        this.ID = new SimpleStringProperty(Integer.toString(ID));
    }

    public String getTitle() {
        return title.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public String getTaken() {
        return taken.get();
    }

    public String getID() {
        return ID.get();
    }

    public String toString() {
        return title + " By: " + author + " isTaken: " + taken + " ID: " + ID;
    }
}