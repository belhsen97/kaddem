package tn.spring.kaddem.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;


public interface IUniversiteService {
    List<Universite> SelectAll ();
    ResponseEntity<Universite> SelectByID (int id);
    List<Universite> SelectMultiByIDs ( crudStruct select);
    Universite Insert( Universite universite);
    List<Universite> MultiInsert( List<Universite> universites);

    ResponseEntity<Universite> update( Universite employeeDetails);
    crudStruct MultiUpdate (List<Universite> UniversitesDetails);
    ResponseEntity<HttpStatus> delete(  Integer id );
    crudStruct MultiDelete( crudStruct delete );





}
