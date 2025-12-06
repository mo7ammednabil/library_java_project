/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
import java.time.LocalDate;

public class Borrowing implements Displayable {

    private int borrowID;
    private int bookID;
    private int studentID;
    private String studentName;
    private LocalDate dateBorrowing;
    private LocalDate dateReturn;

    public Borrowing(int borrowID, int bookID, int studentID, String studentName,
            LocalDate dateBorrowing, LocalDate dateReturn) {
        this.borrowID = borrowID;
        this.bookID = bookID;
        this.studentID = studentID;
        this.studentName = studentName;
        this.dateBorrowing = dateBorrowing;
        this.dateReturn = dateReturn;
    }

    public int getBorrowID() {
        return borrowID;
    }

    public int getBookID() {
        return bookID;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDate getDateBorrowing() {
        return dateBorrowing;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    @Override
    public String display() {
        return "Borrow ID: " + borrowID
                + "\n Book ID: " + bookID
                + "\n Student: " + studentName
                + "\n Borrow Date: " + dateBorrowing
                + "\n Return Date: " + dateReturn;
    }
}
