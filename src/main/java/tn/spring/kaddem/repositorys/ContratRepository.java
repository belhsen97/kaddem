package tn.spring.kaddem.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.spring.kaddem.entities.Contrat;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.entities.Option;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {

   // Contrat findByEtudiant(int etudiant);

    @Query("select c from Contrat c where c.etudiant.idEtudiant = :idEtudiant")  //JPQL
    Contrat selectByEtudiant(@Param("idEtudiant")int idEtudiant);


    //
    @Query("select c from Contrat c where ((c.dateDebutContrat >= :dateDebut) and (c.dateDebutContrat <= :finDebut))")  //JPQL @between(c.dateDebutContrat, now-5, now, day, user_timezone)")
    List<Contrat> selectByTwoDates(@Param("dateDebut") Date dateDebut , @Param("finDebut") Date finDebut);

    Integer countByArchiveIsFalseAndDateDebutContratBetween (Date start,Date end);
}
