package web.projet.serveur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Storages")
public class Storage {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "hostname", nullable = false)
    private String hostname;

    @Column(name = "storageUsed", nullable = false)
    private Integer storageUsed;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public Integer getStorageUsed() {
        return storageUsed;
    }
    public void setStorageUsed(Integer storageUsed) {
        this.storageUsed = storageUsed;
    }
    
}
