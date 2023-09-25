package tn.spring.kaddem.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.kaddem.entities.Universite;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Integer> { }
