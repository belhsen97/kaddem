package tn.spring.kaddem.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.entities.Option;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer>  {


    List<Etudiant> findByOption(Option option); //spring data jpa


    //@Query("select s from Etudiant s where s.option = :opt")  //JPQL
    //List<Etudiant> retrievestudentsbyoption(@Param("opt") Option option);

    @Query("select s from Etudiant s where s.option = ?1")  //JPQL
    List<Etudiant> retrievestudentsbyoption(Option option);
}
