package web.projet.serveur;

import jakarta.persistence.*;

@Entity
@Table(name = "Computers")
public class Computer {

    @Id
    @Column(nullable = false, name="hostname")
    private String hostname;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String user;

    @Column(nullable = false)
    private Integer team;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }
    
}