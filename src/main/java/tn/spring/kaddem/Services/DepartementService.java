package tn.spring.kaddem.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.kaddem.entities.Departement;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.repositorys.DepartementRepository;
import tn.spring.kaddem.repositorys.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;

//@RequiredArgsConstructor


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("departement")
public class DepartementService implements IDepartementService {
    private DepartementRepository departementRepository;
    @Autowired
    public DepartementService (DepartementRepository departementRepository){this.departementRepository = departementRepository;}

    @Autowired
    private UniversiteRepository universiteRepository;




    @Override
    public List<Departement> SelectAll () {
        return departementRepository.findAll(); //fetch a list of Departement from database   return list
    }

    @Override
    public Departement Insert(  Departement departement) { return departementRepository.save(departement); }//this annotation will convert a json into java object d

    @Override
    public List<Departement> MultiInsert(  List<Departement> departements)  //this annotation will convert a json into java object d
    {
        List<Departement> getdepartements = new ArrayList<>();
        for (Departement departement:departements ){
            getdepartements.add(departementRepository.save(departement));
        }
        return  getdepartements;
    }



    @Override
    public ResponseEntity<Departement> SelectByID ( int id) {
        Departement departementSelectByID = departementRepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("Utilisateur not existe with id : "+id))  ;
        return ResponseEntity.ok(departementSelectByID);
    }

    @Override
    public  ResponseEntity<Departement> update( Departement departementDetails){
        Departement departementUpdate = departementRepository.findById(departementDetails.getIdDepart()).
                orElseThrow(()-> new RessourceNotFoundException("Employee not existe with id : "+departementDetails.getIdDepart()))  ;
        departementUpdate.setNomDepart(departementDetails.getNomDepart());
        //departementUpdate.setEtudiants(departementDetails.getEtudiants());
        departementRepository.save(departementUpdate);
        return ResponseEntity.ok(departementUpdate);
    }

    @Override
    public  ResponseEntity<HttpStatus> delete(  Integer id ){
        Departement deleteDepartement = departementRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Employee not existe with id : "+id))  ;

        departementRepository.delete(deleteDepartement);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Departement assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement){
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        Departement departement = departementRepository.findById(idDepartement).orElse(null);
        departement.setUniversite(universite);
        return departementRepository.save(departement);
    }

    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite){
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        List<Departement> getDepartements = new ArrayList<Departement>();
        for (Departement departement:departementRepository.findAll()){
            if (universite == departement.getUniversite())
            {
                getDepartements.add(departement);
            }
        }
        return getDepartements;
    }
}
