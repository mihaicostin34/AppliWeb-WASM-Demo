package web.projet.serveur;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "Processes")
public class Process {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @Column(name = "resourceUsageId", nullable = false)
    private Long resourceUsageId;

    @Column(name = "sessionId", nullable = false)
    private Long sessionId;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Long getResourceUsageId() {
        return resourceUsageId;
    }
    public void setResourceUsageId(Long resourceUsageId) {
        this.resourceUsageId = resourceUsageId;
    }
    public Long getSessionId() {
        return sessionId;
    }
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
