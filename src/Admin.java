/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class Admin extends User implements Displayable {

    private String password;

    public Admin(String username, String password) {
        super(0, username, null);
        this.password = password;
    }

    @Override
    public String display() {
        return "Admin : " + getUserName();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
