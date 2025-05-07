package web.projet.serveur;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
public class Controller {

    @Autowired
    SysInfoRepository sir;

    @PostMapping("/ajoutsysinfo")
    public void ajoutInfo(
        @RequestParam("hostname") String hostname, 
        @RequestParam("hostip") String hostip,
        @RequestParam("cpuUsage") String cpuUsage,
        @RequestParam("processCount") String processCount,
        @RequestParam("date") String date
    ){
        Float cpuF = Float.parseFloat(cpuUsage);
        SysInfo s = new SysInfo();
        s.setCpuUsage(cpuF);
        s.setHostip(hostip);
        s.setHostname(hostname);
        s.setProcesses(Integer.parseInt(processCount));

        SimpleDateFormat sdf
            = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String isoDate = sdf.format(new Date());
        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            // Parse the ISO 8601 string and convert to Date
            Date timestamp = sdf.parse(isoDate);
            s.setTimestamp(timestamp);
        }
        catch (Exception e) {
            s.setTimestamp(new Date());
        }
        sir.save(s);
    }

    @GetMapping("/listeinfo")
    public Collection<SysInfo> listInfo(){
        return sir.findAll();
    }
}