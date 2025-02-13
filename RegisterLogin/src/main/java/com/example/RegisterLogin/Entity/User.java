package com.example.RegisterLogin.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(name="email", length = 255)
    private String email;
    @Column(name="password", length = 255)
    private String password;
    public User() {
    }
    public User(int userid, String email, String password) {
        this.userid = userid;
        this.email = email;
        this.password = password;
    }
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
