package web.projet.serveur;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "Computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hostname", nullable = false, unique = true)
    private String hostname;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "maxRam", nullable = false)
    private Integer maxRam;

    @Column(name = "maxCpu", nullable = false)
    private Integer maxCpu;

    @Column(name = "maxStorage", nullable = false)
    private Integer maxStorage;

    @OneToMany(mappedBy = "computer")
    @JsonIgnore
    private Collection<Session> sessions;

    @OneToMany(mappedBy = "computer")
    @JsonIgnore
    private Collection<Storage> storages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(Integer maxRam) {
        this.maxRam = maxRam;
    }

    public Integer getMaxCpu() {
        return maxCpu;
    }

    public void setMaxCpu(Integer maxCpu) {
        this.maxCpu = maxCpu;
    }

    public Integer getMaxStorage() {
        return maxStorage;
    }

    public void setMaxStorage(Integer maxStorage) {
        this.maxStorage = maxStorage;
    }

    public Collection<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Collection<Session> sessions) {
        this.sessions = sessions;
    }

    public Collection<Storage> getStorages() {
        return storages;
    }

    public void setStorages(Collection<Storage> storages) {
        this.storages = storages;
    }

    
}