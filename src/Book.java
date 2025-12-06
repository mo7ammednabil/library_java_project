/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class Book implements Displayable {

    private int bookID;
    private String bookName;
    private String author;
    private int category;

    public Book(int bookID, String bookName, String author, int category) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
    }

    public int getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public int getCategory() {
        return category;
    }

    @Override
    public String display() {
        return "Book Name: " + bookName
                + "\nAuthor: " + author
                + "\n ID: " + bookID
                + "\n Category: " + category;
    }
}
