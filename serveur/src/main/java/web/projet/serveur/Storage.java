package web.projet.serveur;

import jakarta.persistence.*;

@Entity
@Table(name = "Storages")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "storageUsed", nullable = false)
    private Integer storageUsed;

    @ManyToOne
    private Computer computer;
    
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStorageUsed() {
        return storageUsed;
    }

    public void setStorageUsed(Integer storageUsed) {
        this.storageUsed = storageUsed;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

}
