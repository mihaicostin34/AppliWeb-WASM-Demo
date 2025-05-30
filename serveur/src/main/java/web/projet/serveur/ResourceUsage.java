package web.projet.serveur;

import jakarta.persistence.*;


@Entity
@Table(name = "ResourceUsages")
public class ResourceUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;;

    @Column(name = "time", nullable = false)
    private long time;

    @Column(name = "usedRam", nullable = false)
    private double usedRam;

    @Column(name = "usedCpu", nullable = false)
    private double usedCpu;

    @ManyToOne
    private ResourceUser resourceUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ResourceUser getResourceUser() {
        return resourceUser;
    }

    public void setResourceUser(ResourceUser resourceUser) {
        this.resourceUser = resourceUser;
    }

    
}
