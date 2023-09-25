package tn.spring.kaddem.otherUsed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;

public interface IGenericCRUD<Object> {

    List<Object> SelectAll ();
    ResponseEntity<Object> SelectBy (Object id);
    List<Object> SelectMultiBy( crudStruct select);
    Object Insert( Object Detail);
    List<Object> multiInsert(List<Object> Details);
    ResponseEntity<Object> update( Object Detail);
    crudStruct multiUpdate ( List<Object> Details);
    ResponseEntity<HttpStatus> delete(  Object id );
    crudStruct multiDelete( crudStruct delete );
}
