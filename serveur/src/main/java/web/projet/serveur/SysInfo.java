package web.projet.serveur;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class SysInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

    // machine metadata + timestamp
    private String hostname;
    private String hostip;
    // private Date timestamp;

    // stats
    private float cpuUsage;
    private int processes;
    private Date timestamp;
    private String user;

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setHostip(String hostip) {
        this.hostip = hostip;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public void setProcesses(int processes){
        this.processes = processes;
    }

    public String getHostname() {
        return hostname;
    }

    public String getHostip() {
        return hostip;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public float getCpuUsage() {
        return cpuUsage;
    }

    public int getProcesses(){
        return processes;
    }

    public String getUser(){
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }

    // public SysInfo(
    //     String hostname, 
    //     String hostip, 
    //     // Date timestamp, 
    //     float cpuUsage
    // ) {
    //     this.hostname = hostname;
    //     this.hostip = hostip;
    //     // this.timestamp = timestamp;
    //     this.cpuUsage = cpuUsage;
    // }

    
}