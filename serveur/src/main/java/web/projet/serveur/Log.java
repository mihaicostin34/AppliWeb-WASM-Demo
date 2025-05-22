package web.projet.serveur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Logs")
public class Log {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idSession", nullable = false)
    private Long idSession;

    @Column(name = "time", nullable = false)
    private long time;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "level", nullable = false)
    private String level;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdSession() {
        return idSession;
    }
    public void setIdSession(Long idSession) {
        this.idSession = idSession;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    
}
