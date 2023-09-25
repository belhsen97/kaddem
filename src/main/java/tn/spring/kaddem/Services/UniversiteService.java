package tn.spring.kaddem.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.repositorys.UniversiteRepository;
import tn.spring.kaddem.struct.*;

import java.util.ArrayList;
import java.util.List;

//@RequiredArgsConstructor


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("Universite")
public class UniversiteService implements IUniversiteService {
    // Methode 1
    //@Autowired
    //private UniversiteRepository universiteRepository;

    // Methode 2
    private UniversiteRepository universiteRepository;
    @Autowired // Methode 2
    public UniversiteService (UniversiteRepository universiteRepository){this.universiteRepository = universiteRepository;}





    @Override
    public List<Universite> SelectAll () {
        return universiteRepository.findAll(); //fetch a list of employees from database   return list
    }

    @Override
    public ResponseEntity<Universite> SelectByID ( int id) {
        Universite universiteSelectByID = universiteRepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("Universite not existe with id : "+id))  ;
        return ResponseEntity.ok(universiteSelectByID);
    }

    @Override
    public List<Universite> SelectMultiByIDs ( crudStruct select) {
        List<Universite> getUniversite = new ArrayList<Universite>();
        for (int id:select.Ids){
            boolean verify = universiteRepository.existsById(id);
            if (verify) {getUniversite.add(universiteRepository.findById(id).get());}
        }
        return getUniversite;
    }

    @Override
    public Universite Insert(  Universite universite)  //this annotation will convert a json into java object d
    {
        return universiteRepository.save(universite);
    }

    @Override
    public List<Universite> MultiInsert( List<Universite> universites)  //this annotation will convert a json into java object d
    {
        List<Universite> getuniversites = new ArrayList<>();
        for (Universite universite : universites ){
            getuniversites.add(universiteRepository.save(universite));
        }
        return  getuniversites;
    }

    @Override
    public  ResponseEntity<Universite> update( Universite UniversiteDetails){
        Universite updateEmployee = universiteRepository.findById(UniversiteDetails.getIdUniv()).
                orElseThrow(()-> new RessourceNotFoundException("Universite not existe with id : "+UniversiteDetails.getIdUniv()))  ;
        updateEmployee.setNomUniv(UniversiteDetails.getNomUniv());
        //updateEmployee.setDepartement(employeeDetails.getDepartement());
        universiteRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    @Override
    public crudStruct MultiUpdate ( List<Universite> UniversitesDetails){
        crudStruct update = new crudStruct();
        for (Universite Universite : UniversitesDetails) {
            boolean verify = universiteRepository.existsById(Universite.getIdUniv());
            update.Ids.add(update.Ids.size() + 1);
            update.StatusIds.add(verify);
            if (verify) {
                Universite updateUniversite = universiteRepository.findById(Universite.getIdUniv()).get();
                updateUniversite.setNomUniv(Universite.getNomUniv());
                universiteRepository.save(updateUniversite);
            }
        }
        return update;
    }

    @Override
    public  ResponseEntity<HttpStatus> delete(  Integer id ){
        Universite deleteUniversite = universiteRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Universite not existe with id : "+id))  ;
        universiteRepository.delete(deleteUniversite);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public crudStruct MultiDelete( crudStruct delete ){
        if(delete.Ids.size() == 0){delete.status = "Failed"; return delete; };
        for (int id:delete.Ids){
            boolean verify = universiteRepository.existsById(id);
            delete.StatusIds.add(verify);
            if (verify) {
                Universite deleteUniversite = universiteRepository.findById(id).get() ;
                universiteRepository.delete(deleteUniversite);
            }
        }
        delete.status = "Success";
        return delete;//new ResponseMessageStruct("Delete","success","");
    }
}
