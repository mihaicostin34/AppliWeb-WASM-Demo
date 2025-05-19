package web.projet.serveur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import web.projet.serveur.Account.AccountRole;

public interface AccountRepository extends JpaRepository<Account, String>{

    @Query("select u from #{#entityName} u where u.username = ?1")
    Account findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} a SET a.role = :#{#role} WHERE a.username = :#{#id}")
    int updateAccountRole(@Param("role") AccountRole role, @Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} a SET a.team = :#{#team} WHERE a.username = :#{#username}")
    int assignTeam(@Param("team") Integer team, @Param("username") String username);

}