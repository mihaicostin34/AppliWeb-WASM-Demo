package web.projet.serveur;

import jakarta.persistence.*;

@Entity
@Table(name = "Computers")
public class Computer {

    @Id
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
    
}