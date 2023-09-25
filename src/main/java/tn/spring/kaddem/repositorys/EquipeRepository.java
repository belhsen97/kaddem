package tn.spring.kaddem.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.kaddem.entities.Equipe;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> { }
