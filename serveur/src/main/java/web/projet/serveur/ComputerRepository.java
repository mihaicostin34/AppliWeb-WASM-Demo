package web.projet.serveur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface ComputerRepository extends JpaRepository<Computer, String>{

    @Query("select c from #{#entityName} c where c.hostname = :#{#hostname}")
    Computer findByHostname(@Param("hostname") String hostname);

    @Modifying
    @Transactional
    @Query("UPDATE #{#entityName} a SET a.team = :#{#team} WHERE a.hostname = :#{#hostname}")
    int assignTeam(@Param("team") Integer team, @Param("hostname") String hostname);

}