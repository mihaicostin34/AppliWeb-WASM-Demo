package web.projet.serveur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import web.projet.serveur.User.UserRole;

public interface UserRepository extends JpaRepository<User, String>{

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} a SET a.role = :#{#role} WHERE a.username = :#{#id}")
    int updateRole(@Param("role") UserRole role, @Param("username") String username);
}