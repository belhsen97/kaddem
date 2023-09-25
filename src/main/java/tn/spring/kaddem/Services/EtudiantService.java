package tn.spring.kaddem.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.kaddem.entities.*;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.otherUsed.Performance;
import tn.spring.kaddem.repositorys.*;
import tn.spring.kaddem.struct.crudStruct;

import java.util.*;
//@RequiredArgsConstructor


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("etudiant")
public class EtudiantService implements IEtudiantService {

    private EtudiantRepository etudiantRepository;
    @Autowired // Methode 2
    public EtudiantService (EtudiantRepository etudiantRepository){ this.etudiantRepository = etudiantRepository;}

    @Lazy  //The dependencies of some of the beans in the application context form a cycle
    @Autowired
    private IContratService contratService;

    @Lazy
    @Autowired
    private IEquipeService equipeService;



    @Performance()
    @Override
    public List<Etudiant> SelectAll () {  return etudiantRepository.findAll();  }

    @Override
    public ResponseEntity<Etudiant> SelectOneByID ( int id) {
        Etudiant etudiantSelectByID = etudiantRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Etudiant not existe with id : "+id))  ;
        return ResponseEntity.ok(etudiantSelectByID);
    }

    @Override
    public Etudiant SelectBy ( int id) {
        Etudiant etudiantSelectByID = etudiantRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Etudiant not existe with id : "+id))  ;
        return etudiantSelectByID ;
    }

    @Override
    public List<Etudiant> SelectByMultiIDs (ArrayList<Integer> Ids) {
        List<Etudiant> getEtudiants = new ArrayList<Etudiant>();
        for (int id:Ids){
            boolean verify = etudiantRepository.existsById(id);
            if (verify) {getEtudiants.add(etudiantRepository.findById(id).get());}
        }
        return getEtudiants;
    }

    @Override
    public List<Etudiant> SelectBy ( Option option) {
        return etudiantRepository.findByOption ( option);
    }

    @Override
    public Etudiant Insert(  Etudiant etudiant)
    {
        return etudiantRepository.save(etudiant);
    }
    // json -> memoire student deceliriation

    @Override
    public List<Etudiant> MultiInsert( List<Etudiant> etudiants)  //this annotation will convert a json into java object d
    {
        List<Etudiant> getEtudiants = new ArrayList<>();
        for (Etudiant etudiant:etudiants ){
            getEtudiants.add(etudiantRepository.save(etudiant));
        }
        return  getEtudiants;
    }

    @Override
    public  ResponseEntity<Etudiant> update ( Etudiant etudiantDetails){
        Etudiant updateEtudiant = etudiantRepository.findById(etudiantDetails.getIdEtudiant())
                .orElseThrow(()-> new RessourceNotFoundException("Etudiant not existe with id : "+etudiantDetails.getIdEtudiant()))  ;
        updateEtudiant.setNomE(etudiantDetails.getNomE());
        updateEtudiant.setPrenomE(etudiantDetails.getPrenomE());
        updateEtudiant.setOption(etudiantDetails.getOption());

        //updateEtudiant.setContrat(etudiantDetails.getContrat());
        //updateEtudiant.setDepartement(etudiantDetails.getDepartement());
        //updateEtudiant.setEquipes(etudiantDetails.getEquipes());

        etudiantRepository.save(updateEtudiant);
        return ResponseEntity.ok(updateEtudiant);
    }

    @Override
    public crudStruct MultiUpdate (List<Etudiant> etudiants){
        crudStruct update = new crudStruct();
        for (Etudiant etudiant: etudiants) {
            boolean verify = etudiantRepository.existsById(etudiant.getIdEtudiant());
            update.Ids.add(update.Ids.size() + 1);
            update.StatusIds.add(verify);
            if (verify) {
                Etudiant updateEtudiant = etudiantRepository.findById(etudiant.getIdEtudiant()).get();
                updateEtudiant.setNomE(etudiant.getNomE());
                updateEtudiant.setPrenomE(etudiant.getPrenomE());
                updateEtudiant.setOption(etudiant.getOption());
                etudiantRepository.save(updateEtudiant);
            }
        }
        return update;
    }

    @Override
    public  ResponseEntity<HttpStatus> delete( Integer id ){
        Etudiant deleteEmployee = etudiantRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Etudiant not existe with id : "+id))  ;
        etudiantRepository.delete(deleteEmployee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public crudStruct MultiDelete( crudStruct delete ){
        //List<Etudiant> getEtudiantsDeleted = new ArrayList<>();
        if(delete.Ids.size() == 0){delete.status = "Failed"; return delete; };
        for (int id:delete.Ids){
            boolean verify = etudiantRepository.existsById(id);
            delete.StatusIds.add(verify);
            if (verify) {
                Etudiant deleteEtudiant = etudiantRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Etudiant not existe with id : "+id))  ;
                System.out.println("AAAAAAAAAA");
                //getEtudiantsDeleted.add(deleteEtudiant);
                etudiantRepository.delete(deleteEtudiant);
                //etudiantRepository.deleteById(id);
            }
        }
        delete.status = "Success";
        return delete;//new ResponseMessageStruct("Delete","success","");
    }

    @Autowired
    private DepartementRepository departementRepository;

    @Override
    public Etudiant assignEtudiantToDepartement(Integer idEtudient, Integer IdDEpartement)
    {
        Etudiant etudiant = etudiantRepository.findById(idEtudient).orElse(null);
        Departement departement = departementRepository.findById(IdDEpartement).orElse(null);
        etudiant.setDepartement(departement);
        //Etudiant etudiant2 = etudiantRepository.save(etudiant);
        return etudiantRepository.save(etudiant);
    }

    @Autowired
    private EquipeRepository equipeRepository;

    @Override
    public Etudiant assignEtudiantToEquipe(Integer idEtudient, Integer IdEquipe)
    {
        Etudiant etudiant = etudiantRepository.findById(idEtudient).orElse(null);
        Equipe equipe = equipeRepository.findById(IdEquipe).orElse(null);

        if (etudiant.getEquipes() == null){ //creation de collection
            //System.out.println("Null");
            Set<Equipe> equipes = new HashSet<Equipe>();
            equipes.add(equipe);
            etudiant.setEquipes(equipes);
        }
        else {
            etudiant.getEquipes().add(equipe);
        }
        //etudiant.setEquipes(etudiant.getEquipes());
        return etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> assignEtudiantsToEquipe (  Equipe equipe ,  ArrayList<Integer> idEtudients)
    {
        Equipe newEquipe = equipeService.Insert(equipe);
        for (int idEtudient : idEtudients){this.assignEtudiantToEquipe( idEtudient,  newEquipe.getIdEquipe());}
        List<Etudiant> getEtudiants =  this.SelectByMultiIDs(idEtudients);
        return getEtudiants;
    }



    @Override
    public List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
        Departement departement = departementRepository.findById(idDepartement).orElse(null);
        List<Etudiant> getEtudiants = new ArrayList<Etudiant>();
        for (Etudiant etudiant:etudiantRepository.findAll()){
            if (departement == etudiant.getDepartement())
            {
                getEtudiants.add(etudiant);
            }
        }
        return getEtudiants;
    }












   // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!   not finich !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //@Autowired
    //private EtudiantRepository etudiantRepository;

    @Override
    public Contrat assignEtudiantToContrat(Integer idContrat, Integer idEtudiant)
    {
        Contrat contrat =   contratService.selectBy(idContrat);
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
        contrat.setEtudiant(etudiant);
        return contratService.Insert(contrat);
    }
    /*    @Override
    public Contrat ajouteNouveauContrat(Contrat contrat, int IdEtudiant)
    {
        Etudiant etudiant = this.SelectBy (IdEtudiant);
        Contrat newContrat = contratService.Insert(contrat);
        return  this.assignEtudiantToContrat(newContrat.getIdContrat(),etudiant.getIdEtudiant());
    }
*/

    @Override
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant etudiant, Integer idContrat, Integer idEquipe)
    {
        Etudiant EtudiantAdded = this.Insert( etudiant);
        int idEtudiant = EtudiantAdded.getIdEtudiant();
        Etudiant x = this.assignEtudiantToEquipe(idEtudiant,  idEquipe);
        Contrat contrat =  this.assignEtudiantToContrat( idContrat,  idEtudiant);
        EtudiantAdded = etudiantRepository.findById(idEtudiant).orElse(null);//.get();
        return EtudiantAdded;

        /*  //methode 2
        etudiant = etudiantRepository.save(etudiant);
        Contrat contrat = contratRepository.findById(idContrat).orElse(null);
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);
        contrat.setEtudiant(etudiant);
        contratRepository.save(contrat);
        if (etudiant.getEquipes() == null){ //creation de collection
            Set<Equipe> equipes = new HashSet<Equipe>();
            equipes.add(equipe);
            etudiant.setEquipes(equipes);
        }
        else {
            etudiant.getEquipes().add(equipe);
        }
        return etudiantRepository.save(etudiant);*/
    }










    @Override
    public Contrat affectContratToEtudiant (Contrat ce, String nomE,String prenomE)
    {
        List<Etudiant>  etudiants = etudiantRepository.findAll();
        if ( !contratService.isExistContrat( ce)){ return ce;}
        for (Etudiant etudiant  : etudiants )
        {
            if ((etudiant.getNomE().equals(nomE))&&(etudiant.getPrenomE().equals(prenomE)))
            {
                if ( contratService.CountContrat(etudiant) <= 5 ){
                    ce.setEtudiant(etudiant);
                    ce = contratService.Insert(ce);
                }
            }
        }
        return ce;
    }











}
