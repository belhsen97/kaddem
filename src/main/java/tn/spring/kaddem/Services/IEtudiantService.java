package tn.spring.kaddem.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.spring.kaddem.entities.Contrat;
import tn.spring.kaddem.entities.Equipe;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.entities.Option;
import tn.spring.kaddem.struct.crudStruct;

import java.util.ArrayList;
import java.util.List;


public interface IEtudiantService {
    List<Etudiant> SelectAll ();
    List<Etudiant> SelectByMultiIDs ( ArrayList<Integer> Ids);
    Etudiant SelectBy ( int id);
    ResponseEntity<Etudiant> SelectOneByID ( int id);
    List<Etudiant> SelectBy ( Option option);
    Etudiant Insert(  Etudiant etudiant);
    List<Etudiant> MultiInsert( List<Etudiant> etudiants);
    ResponseEntity<Etudiant> update ( Etudiant etudiantDetails);
    crudStruct MultiUpdate (List<Etudiant> etudiants);

    ResponseEntity<HttpStatus> delete(Integer id );
    crudStruct MultiDelete( crudStruct delete );

    Etudiant assignEtudiantToDepartement ( Integer idEtudient, Integer IdDEpartement);
    Etudiant assignEtudiantToEquipe(Integer idEtudient, Integer IdEquipe);

    List<Etudiant> assignEtudiantsToEquipe (Equipe equipe , ArrayList<Integer> idEtudients);

    Contrat assignEtudiantToContrat(Integer idContrat, Integer idEtudiant);
    // Contrat ajouteNouveauContrat(Contrat contrat, int IdEtudiant);

    Contrat affectContratToEtudiant (Contrat ce, String nomE,String prenomE);

    Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe);
    List<Etudiant> getEtudiantsByDepartement (Integer idDepartement);
}
