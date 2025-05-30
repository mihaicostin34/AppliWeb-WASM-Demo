package web.projet.serveur;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;


@Entity
public class Session extends ResourceUser {

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "endTime", nullable = true)  // nullable = true because the session may not have ended yet
    private Date endTime;

    @ManyToOne
    private User user;

    @ManyToOne
    private Computer computer;

    @OneToMany(mappedBy = "session")
    private Collection<Log> logs;

    @OneToMany(mappedBy = "resourceUser")
    private Collection<ResourceUsage> resourceUsages;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Collection<Log> getLogs() {
        return logs;
    }

    public void setLogs(Collection<Log> logs) {
        this.logs = logs;
    }

    public Collection<ResourceUsage> getResourceUsages() {
        return resourceUsages;
    }

    public void setResourceUsages(Collection<ResourceUsage> resourceUsages) {
        this.resourceUsages = resourceUsages;
    }
    
    
}