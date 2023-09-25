package tn.spring.kaddem.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.kaddem.entities.DetailEquipe;
@Repository
public interface DetailEquipeRepository extends JpaRepository<DetailEquipe, Integer> { }
