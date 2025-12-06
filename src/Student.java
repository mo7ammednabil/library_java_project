/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class Student extends User implements Displayable {

    private String address;

    public Student(int userID, String userName, String address, String email) {
        super(userID, userName, email);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String display() {
        return "Student: " + getUserName()
                + "\n Address: " + address
                + "\n Email: " + getEmail()
                + "\n ID: " + getUserID();
    }

}
