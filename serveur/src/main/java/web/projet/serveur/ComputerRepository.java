package web.projet.serveur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ComputerRepository extends JpaRepository<Computer, Long>{
}