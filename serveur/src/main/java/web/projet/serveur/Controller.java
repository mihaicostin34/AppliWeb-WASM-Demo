package web.projet.serveur;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private ResourceUsageRepository resourceUsageRepository;

    @Autowired
    private ProcessRepository processRepository;

    @GetMapping("/computers")
    public Collection<Computer> getComputers() {
        return computerRepository.findAll();
    }
    
    @PostMapping("/computer")
    public Computer createComputer(@RequestParam String hostname, @RequestParam String ip, 
    @RequestParam Integer maxRam, @RequestParam Integer maxCpu, 
    @RequestParam Integer maxStorage) {
        Computer computer = new Computer();
        computer.setHostname(hostname);
        computer.setIp(ip);
        computer.setMaxRam(maxRam);
        computer.setMaxCpu(maxCpu);
        computer.setMaxStorage(maxStorage);
        return computerRepository.save(computer);
    }

    @GetMapping("/computer")
    public Computer getComputer(@RequestParam Long id) {
        return computerRepository.findById(id).orElseThrow(() -> new RuntimeException("Computer not found"));
    }

    @GetMapping("/computer/sessions")
    public Collection<Session> getComputerSessions(@RequestParam Long id) {
        Computer computer = computerRepository.findById(id).orElseThrow(() -> new RuntimeException("Computer not found"));
        return computer.getSessions();
    }

    @GetMapping("/computer/storages")
    public Collection<Storage> getComputerStorages(@RequestParam Long id) {
        Computer computer = computerRepository.findById(id).orElseThrow(() -> new RuntimeException("Computer not found"));
        return computer.getStorages();
    }
    
    @GetMapping("/groups")
    public Collection<Group> getGroups() {
        return groupRepository.findAll();
    }

    @PostMapping("/group")
    public Group createGroup(@RequestParam String name, @RequestParam String description) {
        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        return groupRepository.save(group);
    }
    
    @GetMapping("/group")
    public Group getGroup(@RequestParam Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found"));
    }
    
    @GetMapping("/group/memberships")
    public Collection<Membership> getGroupMemberships(@RequestParam Long id) {
        Group group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group not found"));
        return group.getMemberships();
    }

    @GetMapping("/logs")
    public Collection<Log> getLogs() {
        return logRepository.findAll();
    }

    @GetMapping("/log")
    public Log getLog(@RequestParam Long id) {
        return logRepository.findById(id).orElseThrow(() -> new RuntimeException("Log not found"));
    }

    @GetMapping("/memberships")
    public Collection<Membership> getMemberships() {
        return membershipRepository.findAll();
    }

    @GetMapping("/membership")
    public Membership getMembership(@RequestParam Long id) {
        return membershipRepository.findById(id).orElseThrow(() -> new RuntimeException("Membership not found"));
    }

    @PostMapping("/membership")
    public Membership createMembership(@RequestParam Long userId, @RequestParam Long groupId, @RequestParam String role) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Group not found"));

        Membership membership = new Membership();
        membership.setUser(user);
        membership.setGroup(group);
        membership.setRole(Membership.UserRole.valueOf(role.toUpperCase()));

        return membershipRepository.save(membership);
    }

    @GetMapping("/processes")
    public Collection<Process> getProcesses() {
        return processRepository.findAll();
    }

    @PostMapping("/process")
    public Process createProcess(@RequestParam String name, @RequestParam Integer pid, 
                                 @RequestParam Long resourceUsageId, @RequestParam Long sessionId) {
        Process process = new Process();
        process.setName(name);
        process.setPid(pid);
        process.setResourceUsageId(resourceUsageId);
        process.setSessionId(sessionId);
        return processRepository.save(process);
    }


    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);   
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/user/memberships")
    public Collection<Membership> getUserMemberships(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getMemberships();
    }

    

    

    @GetMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

}