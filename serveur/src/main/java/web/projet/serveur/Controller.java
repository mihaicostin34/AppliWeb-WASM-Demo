package web.projet.serveur;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/computer")
    public Computer createComputer(@RequestBody Computer newComputer) {
        Computer computer = new Computer();
        computer.setHostname(newComputer.getHostname());
        computer.setIp(newComputer.getIp());
        computer.setMaxRam(newComputer.getMaxRam());
        computer.setMaxCpu(newComputer.getMaxCpu());
        computer.setMaxStorage(newComputer.getMaxStorage());
        return computerRepository.save(computer);
    }
    
    @GetMapping("/groups")
    public Collection<Group> getGroups() {
        return groupRepository.findAll();
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

    @PostMapping("/group")
    public Group createGroup(@RequestBody Group newGroup) {
        Group group = new Group();
        group.setName(newGroup.getName());
        group.setDescription(newGroup.getDescription());
        return groupRepository.save(group);
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
    public Membership createMembership(@RequestBody Membership newMembership) {
        Membership membership = new Membership();
        membership.setUser(newMembership.getUser());
        membership.setGroup(newMembership.getGroup());
        membership.setRole(newMembership.getRole());
        return membershipRepository.save(membership);
    }

    @GetMapping("/processes")
    public Collection<Process> getProcesses() {
        return processRepository.findAll();
    }

    @GetMapping("/process")
    public Process getProcess(@RequestParam Long id) {
        return processRepository.findById(id).orElseThrow(() -> new RuntimeException("Process not found"));
    }

    @GetMapping("/process/resourceUsages")
    public Collection<ResourceUsage> getProcessResourceUsages(@RequestParam Long id) {
        Process process = processRepository.findById(id).orElseThrow(() -> new RuntimeException("Process not found"));
        return process.getResourceUsages();
    }

    @PostMapping("/process")
    public Process createProcess(@RequestBody Process newProcess) {
        Process process = new Process();
        process.setName(newProcess.getName());
        process.setPid(newProcess.getPid());
        process.setResourceUsages(newProcess.getResourceUsages());
        return processRepository.save(process);
    }

    @PostMapping("/process/resourceUsage")
    public ResourceUsage createResourceUsage(@RequestBody ResourceUsage newResourceUsage) {
        ResourceUsage resourceUsage = new ResourceUsage();
        resourceUsage.setUsedRam(newResourceUsage.getUsedRam());
        resourceUsage.setUsedCpu(newResourceUsage.getUsedCpu());
        resourceUsage.setResourceUser(newResourceUsage.getResourceUser());
        resourceUsage.setTime(newResourceUsage.getTime());
        return resourceUsageRepository.save(resourceUsage);
    }

    @GetMapping("/resourceUsages")
    public Collection<ResourceUsage> getResourceUsages() {
        return resourceUsageRepository.findAll();
    }

    @GetMapping("/resourceUsage")
    public ResourceUsage getResourceUsage(@RequestParam Long id) {
        return resourceUsageRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource Usage not found"));
    }

    @GetMapping("/sessions")
    public Collection<Session> getSessions() {
        return sessionRepository.findAll();
    }

    @GetMapping("/session")
    public Session getSession(@RequestParam Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Session not found"));
    }

    @PostMapping("/session")
    public Session createSession(@RequestBody Session newSession) {
        Session session = new Session();
        session.setStartTime(newSession.getStartTime());
        session.setEndTime(newSession.getEndTime());
        session.setComputer(newSession.getComputer());
        session.setUser(newSession.getUser());
        return sessionRepository.save(session);
    }

    @GetMapping("/storages")
    public Collection<Storage> getStorages() {
        return storageRepository.findAll();
    }

    @PostMapping("/storage")
    public Storage createStorage(@RequestBody Storage newStorage) {
        Storage storage = new Storage();
        storage.setStorageUsed(newStorage.getStorageUsed());
        storage.setComputer(newStorage.getComputer());
        storage.setUser(newStorage.getUser());
        return storageRepository.save(storage);
    }


    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/user/sessions")
    public Collection<Session> getUserSessions(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getSessions();
    }

    @GetMapping("/user/storages")
    public Collection<Storage> getUserStorages(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getStorages();
    }

    @GetMapping("/user/memberships")
    public Collection<Membership> getUserMemberships(@RequestParam Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getMemberships();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        return userRepository.save(user);   
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