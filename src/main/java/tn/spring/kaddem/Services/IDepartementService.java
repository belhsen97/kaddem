package tn.spring.kaddem.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.spring.kaddem.entities.Departement;

import java.util.List;


public interface IDepartementService {
    List<Departement> SelectAll ();
    Departement Insert(  Departement departement);
    List<Departement> MultiInsert(  List<Departement> departements);
    ResponseEntity<Departement> SelectByID ( int id);
    ResponseEntity<Departement> update(Departement departementDetails);
    ResponseEntity<HttpStatus> delete(  Integer id );

    Departement assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement);
    List<Departement> retrieveDepartementsByUniversite(Integer idUniversite);
}
