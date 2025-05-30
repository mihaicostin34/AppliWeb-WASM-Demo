package web.projet.serveur;

import jakarta.persistence.*;

@Entity
@Table(name = "Memberships")
public class Membership {

    
    public enum UserRole{
        ADMIN,   // full access to his group's activities
        VIEWER,  // can only see his activty
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    private UserRole role;

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    

}
