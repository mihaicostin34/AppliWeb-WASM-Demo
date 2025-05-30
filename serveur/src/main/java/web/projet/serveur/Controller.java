package web.projet.serveur;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/groups")
    public Collection<Group> getGroups() {
        return groupRepository.findAll();
    }

    @PostMapping("/creation-user")
    public User createUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
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

}