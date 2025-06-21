package Expense_Share_App.Expense.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String phone;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public User() {
    }
}
