package web.projet.serveur;

import jakarta.persistence.*;

@Entity
@Table(name = "Accounts")
public class Account {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

    @Id
    @Column(nullable = false, unique = true, name = "username")
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private AccountRole role;

    @Column(nullable = false)
    private Integer team;

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public AccountRole getRole() {
        return this.role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }

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

    public enum AccountRole{
        ADMIN, //full access
        MANAGER, // can see machines belonging to his team
        VIEWER, //goofy ahh can only see his machine
    }
}
