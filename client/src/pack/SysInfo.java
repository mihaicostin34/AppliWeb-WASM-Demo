package pack;

// import java.util.Date;

public class SysInfo {

	long id;

    // machine metadata + timestamp
    String hostname;
    String hostip;
    // private Date timestamp;

    // stats
     float cpuUsage;

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setHostip(String hostip) {
        this.hostip = hostip;
    }

    // public void setTimestamp(Date timestamp) {
    //     this.timestamp = timestamp;
    // }

    public void setCpuUsage(float cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getHostname() {
        return hostname;
    }

    public String getHostip() {
        return hostip;
    }

    // public Date getTimestamp() {
    //     return timestamp;
    // }

    public float getCpuUsage() {
        return cpuUsage;
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