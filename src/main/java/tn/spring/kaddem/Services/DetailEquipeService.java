package tn.spring.kaddem.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.kaddem.entities.DetailEquipe;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.repositorys.DetailEquipeRepository;

import java.util.List;

//@RequiredArgsConstructor


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("detailequipe")
public class DetailEquipeService implements IDetailEquipeService {
    private DetailEquipeRepository detailEquipeRepository;
    @Autowired // Methode 2
    public DetailEquipeService (DetailEquipeRepository detailEquipeRepository){  this.detailEquipeRepository = detailEquipeRepository; }






    @Override
    public List<DetailEquipe> SelectAll () {
        return detailEquipeRepository.findAll();
    }

    @Override
    public DetailEquipe Insert(  DetailEquipe detailEquipe)
    {
        return detailEquipeRepository.save(detailEquipe);
    }

    @Override
    public ResponseEntity<DetailEquipe> SelectByID ( int id) {
        DetailEquipe equipeSelectByID = detailEquipeRepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("Utilisateur not existe with id : "+id))  ;

        return ResponseEntity.ok(equipeSelectByID);
    }

    @Override
    public DetailEquipe selectBy ( int id) {return detailEquipeRepository.findById(id).orElse(null);}

    @Override
    public  ResponseEntity<DetailEquipe> update( DetailEquipe detailEquipeDetails){
        DetailEquipe updateEquipe = detailEquipeRepository.findById(detailEquipeDetails.getIdDetailEquipe()).
                orElseThrow(()-> new RessourceNotFoundException("Employee not existe with id : "+detailEquipeDetails.getIdDetailEquipe()))  ;
        updateEquipe.setSalle(detailEquipeDetails.getSalle());
        updateEquipe.setThematique(detailEquipeDetails.getThematique());
        //updateEquipe.setDetailEquipe(equipeDetails.getDetailEquipe());
        //updateEquipe.setEtudiants(equipeDetails.getEtudiants());
        detailEquipeRepository.save(updateEquipe);
        return ResponseEntity.ok(updateEquipe);
    }

    @Override
    public  ResponseEntity<HttpStatus> delete(  int id ){
        DetailEquipe deleteEquipe = detailEquipeRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Employee not existe with id : "+id))  ;

        detailEquipeRepository.delete(deleteEquipe);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
