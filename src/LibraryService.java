/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

//    admin
    public boolean checkAdminLoginDB(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- Students ---
    public void addStudent(Student s) {
        String sql = "INSERT INTO Students(StudentID, StudentName, Address, Email) VALUES(?,?,?,?)";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, s.getUserID());
            ps.setString(2, s.getUserName());
            ps.setString(3, s.getAddress());
            ps.setString(4, s.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean studentExists(int id) {
        String sql = "SELECT 1 FROM Students WHERE StudentID = ?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteStudent(int studentID) {
        String sql = "DELETE FROM Students WHERE StudentID=?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection con = DatabaseConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("StudentID"),
                        rs.getString("StudentName"),
                        rs.getString("Address"),
                        rs.getString("Email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // --- Books ---
    public void addBook(Book b) {
        String sql = "INSERT INTO Books(BookID, BookName, Author, CategoryID) VALUES(?,?,?,?)";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getBookID());
            ps.setString(2, b.getBookName());
            ps.setString(3, b.getAuthor());
            ps.setInt(4, b.getCategory());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookID) {
        String sql = "DELETE FROM Books WHERE BookID=?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bookID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean bookExists(int bookID) {
        String sql = "SELECT 1 FROM Books WHERE BookID = ?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Books";
        try (Connection con = DatabaseConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Book(rs.getInt("BookID"),
                        rs.getString("BookName"),
                        rs.getString("Author"),
                        rs.getInt("CategoryID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // --- Borrowing ---
    public boolean checkStudentBorrowed(int studentID) {
        String sql = "SELECT 1 FROM Borrowing WHERE StudentID = ?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // لو رجع rows يبقى الطالب لسه مستعير كتب

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkBookBorrowed(int bookID) {
        String sql = "SELECT 1 FROM Borrowing WHERE BookID = ?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bookID);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean borrowExists(int id) {
        String sql = "SELECT COUNT(*) FROM Borrowing WHERE BorrowID = ?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void addBorrowing(Borrowing b) {
        String sql = "INSERT INTO Borrowing(BookID, StudentID, DateBorrowing, DateReturn) VALUES(?,?,?,?)";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getBookID());
            ps.setInt(2, b.getStudentID());
            ps.setDate(3, Date.valueOf(b.getDateBorrowing()));
            ps.setDate(4, Date.valueOf(b.getDateReturn()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBorrowing(int borrowID) {
        String sql = "DELETE FROM Borrowing WHERE BorrowID=?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, borrowID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Borrowing> getAllBorrowing() {
        List<Borrowing> list = new ArrayList<>();
        String sql = "SELECT b.BorrowID, b.BookID, s.StudentName, b.StudentID, b.DateBorrowing, b.DateReturn FROM Borrowing b JOIN Students s ON b.StudentID = s.StudentID ";

        try (Connection con = DatabaseConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Borrowing(
                        rs.getInt("BorrowID"),
                        rs.getInt("BookID"),
                        rs.getInt("StudentID"),
                        rs.getString("StudentName"),
                        rs.getDate("DateBorrowing").toLocalDate(),
                        rs.getDate("DateReturn").toLocalDate()
                )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // --- Categories ---
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Categories";
        try (Connection con = DatabaseConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Category(rs.getInt("CategoryID"),
                        rs.getString("CategoryName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
