package web.projet.serveur;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    @Query("SELECT m.role FROM Membership m WHERE m.user.id = ?1 AND m.group.id = ?2")
    String findRoleByUserIdAndGroupId(Long userId, Long groupId);

    @Query("SELECT m.group FROM Membership m WHERE m.user.id = ?1 AND m.role = ?2")
    Collection<Group> findGroupsByUserIdAndRole(Long userId, String role);
}
