package tn.spring.kaddem.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import tn.spring.kaddem.entities.Contrat;
import tn.spring.kaddem.entities.Etudiant;

import java.util.Date;
import java.util.List;


public interface IContratService {
    List<Contrat> SelectAll ();
    Contrat Insert( Contrat contrat);
    ResponseEntity<Contrat> SelectByID ( int id);
    Contrat selectBy ( int id);
    ResponseEntity<Contrat> update(  Contrat contratDetails);
    ResponseEntity<HttpStatus> delete(  Integer id );
    Integer nbContratsValides(Date startDate, Date endDate);
    float getChiffreAffaireEntreDeuxDate( String startDate, String  endDate);
    //float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate);
    String retrieveAndUpdateStatusContrat();



     int CountContrat(Etudiant etudiant);
     boolean isExistContrat(Contrat ce);
    boolean isExistContrat(Etudiant etudiant);
    boolean validate (Etudiant etudiant);

    Contrat ajouteNouveauContrat(Contrat contrat, int IdEtudiant);
    float getClosestDate();
}
