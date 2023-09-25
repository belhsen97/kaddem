package tn.spring.kaddem.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.kaddem.entities.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> { }
