package web.projet.serveur;

import java.util.Collection;

import jakarta.persistence.*;


@Entity
public class Process extends ResourceUser {

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @Column(name = "resourceUsageId", nullable = false)
    private Long resourceUsageId;

    @Column(name = "sessionId", nullable = false)
    private Long sessionId;

    @OneToMany(mappedBy = "resourceUser")
    private Collection<ResourceUsage> resourceUsages;

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

    public Collection<ResourceUsage> getResourceUsages() {
        return resourceUsages;
    }

    public void setResourceUsages(Collection<ResourceUsage> resourceUsages) {
        this.resourceUsages = resourceUsages;
    }

    
}
