package tn.spring.kaddem.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.kaddem.entities.*;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.otherUsed.Performance;
import tn.spring.kaddem.repositorys.EquipeRepository;
import tn.spring.kaddem.struct.crudStruct;

import javax.transaction.Transactional;
import java.util.*;

//@RequiredArgsConstructor


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("equipe")
public class EquipeService implements IEquipeService {
    private EquipeRepository equipeRepository;
    @Autowired // Methode 2
    public EquipeService (EquipeRepository equipeRepository){
        this.equipeRepository = equipeRepository;
    }

    @Autowired
    private IContratService contratService;

    @Autowired
    private IDetailEquipeService detailEquipeService;

    @Autowired
    private IEtudiantService IEtudiantService;






    @Performance()
    @Override
    public List<Equipe> SelectAll () {
        return equipeRepository.findAll();
    }

    @Override
    public ResponseEntity<Equipe> SelectByID ( int id) {
        Equipe equipeSelectByID = equipeRepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("Utilisateur not existe with id : "+id))  ;
        return ResponseEntity.ok(equipeSelectByID);
    }

    @Override
    public List<Equipe> SelectByMultiIDs ( crudStruct select) {
        List<Equipe> getEquipes = new ArrayList<Equipe>();
        for (int id:select.Ids){
            boolean verify = equipeRepository.existsById(id);
            if (verify) {getEquipes.add(equipeRepository.findById(id).get());}
        }
        return getEquipes;
    }

    @Override
    public Equipe Insert(  Equipe equipe)
    {
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> MultiInsert(List<Equipe> equipes)  //this annotation will convert a json into java object d
    {
        List<Equipe> getEquipes = new ArrayList<>();
        for (Equipe equipe:equipes ){
            getEquipes.add(equipeRepository.save(equipe));
        }
        return  getEquipes;
    }

    @Override
    public  ResponseEntity<Equipe> update( Equipe equipeDetails){
        Equipe updateEquipe = equipeRepository.findById(equipeDetails.getIdEquipe()).
                orElseThrow(()-> new RessourceNotFoundException("Equipe not existe with id : "+equipeDetails.getIdEquipe()))  ;
        updateEquipe.setNomEquipe(equipeDetails.getNomEquipe());
        updateEquipe.setNiveau(equipeDetails.getNiveau());
        //updateEquipe.setDetailEquipe(equipeDetails.getDetailEquipe());
        //updateEquipe.setEtudiants(equipeDetails.getEtudiants());
        equipeRepository.save(updateEquipe);
        return ResponseEntity.ok(updateEquipe);
    }

    @Override
    public crudStruct MultiUpdate (List<Equipe> equipes){
        crudStruct update = new crudStruct();
        for (Equipe equipe: equipes) {
            boolean verify = equipeRepository.existsById(equipe.getIdEquipe());
            update.Ids.add(update.Ids.size() + 1);
            update.StatusIds.add(verify);
            if (verify) {
                Equipe updateequipe = equipeRepository.findById(equipe.getIdEquipe()).get();
                updateequipe.setNomEquipe(equipe.getNomEquipe());
                updateequipe.setNiveau(equipe.getNiveau());
                equipeRepository.save(updateequipe);
            }
        }
        return update;
    }

    @Override
    public  ResponseEntity<HttpStatus> delete(  Integer id ){
        Equipe deleteEquipe = equipeRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Employee not existe with id : "+id))  ;
        equipeRepository.delete(deleteEquipe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public crudStruct MultiDelete(crudStruct delete ){
        //List<Equipe> getEquipesDeleted = new ArrayList<Equipe>();
        if(delete.Ids.size() == 0){delete.status = "Failed"; return delete; };
        for (int id:delete.Ids){
            boolean verify = equipeRepository.existsById(id);
            delete.StatusIds.add(verify);
            if (verify) {
                Equipe deleteEquipe = equipeRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Equipe not existe with id : "+id))  ;
                equipeRepository.delete(deleteEquipe);
            }
        }
        delete.status = "Success";
        return delete;//new ResponseMessageStruct("Delete","success","");
    }






    @Override
    public Equipe assignEquipeToDetailEquipe(Integer idEquipe, Integer IdDetailEquipe)
    {
        Equipe equipe = equipeRepository.findById(idEquipe).orElse(null);
        DetailEquipe detailEquipe =  detailEquipeService.selectBy (IdDetailEquipe);
        equipe.setDetailEquipe(detailEquipe);
        return equipeRepository.save(equipe);
    }

    @Override
    public  void upgradeNiveau( Equipe equipe){
          Niveau niveau =  equipe.getNiveau();
          if(niveau == Niveau.JUNIOR){niveau = Niveau.SENIOR;}
          if(niveau == Niveau.SENIOR){niveau = Niveau.EXPERT;}
        equipe.setNiveau(niveau);
        equipeRepository.save(equipe);
    }




    @Override
    @Transactional // ya3mil commit ya role back    yihkmha kol yala
    public Equipe AddAfect_EquipeEtudiantsDetailEquipe(  Equipe equipe)  //Failed $$$$$$$$$$$$$$$$$$$$$$$$$
    {
        // add equipe et list etudiant et detail
        try {//System.out.println(equipe.getEtudiants().size());
            if (( equipe.getEtudiants() == null )||(equipe.getEtudiants().size()==0)) {return equipe;}
            if ( equipe.getDetailEquipe() == null ) {return equipe;}
            detailEquipeService.Insert(equipe.getDetailEquipe());
            Equipe newEquipe =  this.Insert(equipe);
            for (Etudiant etudiant : equipe.getEtudiants() ) {
                etudiant.getEquipes().add(equipe);
                IEtudiantService.Insert(etudiant);
            }
            equipe = newEquipe;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return equipe;
    }







    @Override
    public void faireEvoluerEquipes(){
        List<Equipe> equipes = this.SelectAll ();
        for (Equipe equipe : equipes)
        {//System.out.println( equipe.getEtudiants().size());
            if ( equipe.getEtudiants().size() >= 3)
            {
                for (Etudiant etudiant : equipe.getEtudiants()) {
                    if ( contratService. validate ( etudiant) ) {
                      this.upgradeNiveau(equipe);
                    }
                }
            }
        }
    }


}
























//   Set<Etudiant> etudiants = new HashSet<Etudiant>();
//   etudiants.add(etudiantRepository.findById(10).get());
//  equipe.setEtudiants(etudiants);
// if ( contratService. CountContrat( etudiant) >0 ) {
// System.out.println( contratService.checkContratDate(Contrat contrat) );}
// System.out.println("etudiant =" +etudiant.getIdEtudiant()  + " countcontrat = "+contratService. CountContrat( etudiant));
//  System.out.println("etudiant =" +etudiant.getNomE()  + " have contrat = "+ String.valueOf(contratService.isExistContrat( etudiant)));