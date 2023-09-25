package tn.spring.kaddem.otherUsed;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.repositorys.EtudiantRepository;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("ex")
public class ex implements IGenericCRUD<Etudiant> {
    private EtudiantRepository Repository;

    //@Autowired
    //private objectRespository<Etudiant,Integer> objtRespository ;

    @Autowired
    public ex(EtudiantRepository Repository){
        this.Repository = Repository;
    }// Methode 2

    @Override
    public List<Etudiant> SelectAll() {
        return Repository.findAll(); //fetch a list of employees from database   return list
    }

    @Override
    public ResponseEntity<Etudiant> SelectBy(Etudiant id) {
        return null;
    }





    @Override
    public List<Etudiant> SelectMultiBy(crudStruct select)  {
        return null;
    }

    @Override
    public Etudiant Insert(Etudiant Detail) {
        return null;
    }

    @Override
    public List<Etudiant> multiInsert(List<Etudiant> Details) {
        return null;
    }



    @Override
    public ResponseEntity<Etudiant> update(Etudiant Detail) {
        return null;
    }

    @Override
    public crudStruct multiUpdate(List<Etudiant> Details) {
        return null;
    }



    @Override
    public ResponseEntity<HttpStatus> delete(Etudiant id) {
        return null;
    }



    @Override
    public crudStruct multiDelete(crudStruct delete) {
        return null;
    }
}
