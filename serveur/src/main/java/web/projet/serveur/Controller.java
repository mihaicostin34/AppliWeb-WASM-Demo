package web.projet.serveur;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import web.projet.serveur.Account.AccountRole;

import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
public class Controller {

    @Autowired
    SysInfoRepository sir;

    @Autowired
    AccountRepository ar;

    @Autowired
    ComputerRepository cr;

    @PostMapping("/ajoutsysinfo")
    public int ajoutInfo(
        @RequestParam("hostname") String hostname, 
        @RequestParam("hostip") String hostip,
        @RequestParam("cpuUsage") String cpuUsage,
        @RequestParam("processCount") String processCount,
        @RequestParam("date") String date,
        @RequestParam("user") String user
    ){
        Float cpuF = Float.parseFloat(cpuUsage);
        SysInfo s = new SysInfo();
        s.setCpuUsage(cpuF);
        s.setHostip(hostip);
        s.setHostname(hostname);
        s.setProcesses(Integer.parseInt(processCount));
        s.setUser(user);

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

        //check if computer has been seen before
        Computer seen = cr.findByHostname(hostname);
        if(seen== null){
            //not seen before -> add entry in computer database
            Computer nc = new Computer();
            nc.setHostname(hostname);
            nc.setIp(hostip);
            // nc.setTeam(team);
            Account associatedUser = ar.findByUsername(user);
            if(associatedUser!=null){
                nc.setTeam(associatedUser.getTeam());
            }else{
                return 401;
            }
            nc.setUser(user);
            cr.save(nc);
            return 200;
        }else{
            //if it has been seen before, check if its team number needs updating
            Account associatedUser = ar.findByUsername(user);
            if(associatedUser!=null){
                if(associatedUser.getTeam()!= seen.getTeam()){
                    // update team associated to computer
                    cr.assignTeam(associatedUser.getTeam(), hostname);
                }
            }else{
                return 402;
            }
        }

        sir.save(s);
        return 200;
    }

    @GetMapping("/listeinfo")
    public Collection<SysInfo> listInfo(){
        return sir.findAll();
    }

    @GetMapping("/listeordi")
    public Collection<Computer> listComputers(){
        return cr.findAll();
    }

    @GetMapping("/listeacc")
    public Collection<Account> listAccounts(){
        return ar.findAll();
    }

    @PostMapping("/createaccount")
    public void createAccount(
        @RequestParam("username") String username,
        @RequestParam("password") String password
    ){
        Account a = new Account();
        a.setUsername(username);
        a.setPassword(password);
        a.setTeam(-1);
        a.setRole(AccountRole.VIEWER);
        ar.save(a);
    }

    @PostMapping("/createadminaccount")
    public void createAdminAccount(
        @RequestParam("username") String username,
        @RequestParam("password") String password
    ){
        Account a = new Account();
        a.setUsername(username);
        a.setPassword(password);
        a.setTeam(0);
        a.setRole(AccountRole.ADMIN);
        ar.save(a);
    }

    @PostMapping("/login")
    public int login(
        @RequestParam("username") String username,
        @RequestParam("password") String password
    ){
        Account a = ar.findByUsername(username);
        if(a==null){
            return 401;
        }else if(!a.getPassword().equals(password)){
            return 402;
        }else{
            return a.getTeam();
        }
    }

    @PostMapping("/assignteam")
    public int assign(
        @RequestParam("admin") String admin,
        @RequestParam("manager") String manager,
        @RequestParam("team") Integer team
    ){
        Account a = ar.findByUsername(admin);
        Account m = ar.findByUsername(manager);
        if(a==null || a.getRole()!=AccountRole.ADMIN){
            return 401; 
        }else if (m==null){
            return 402;
        }else{
            ar.assignTeam(team, manager);
            ar.updateAccountRole(AccountRole.MANAGER, manager);
            return 200;
        }
    }

}