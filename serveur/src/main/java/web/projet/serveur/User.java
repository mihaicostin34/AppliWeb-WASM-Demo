package web.projet.serveur;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public enum UserRole{
        ADMIN,   // full access
        MANAGER, // can see activity of his team's members
        VIEWER,  // can only see his activty
    }
}
