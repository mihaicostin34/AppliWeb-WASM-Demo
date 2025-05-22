package web.projet.serveur;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;


@Entity
@Table(name = "ResourceUsages")
public class ResourceUsage {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idSession", nullable = false)
    private Long idSession;

    @Column(name = "time", nullable = false)
    private long time;

    @Column(name = "usedRam", nullable = false)
    private double usedRam;

    @Column(name = "usedCpu", nullable = false)
    private double usedCpu;

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
    public double getUsedRam() {
        return usedRam;
    }
    public void setUsedRam(double usedRam) {
        this.usedRam = usedRam;
    }
    public double getUsedCpu() {
        return usedCpu;
    }
    public void setUsedCpu(double usedCpu) {
        this.usedCpu = usedCpu;
    }
}
