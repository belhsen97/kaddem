package tn.spring.kaddem.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.spring.kaddem.entities.DetailEquipe;

import java.util.List;


public interface IDetailEquipeService {
    List<DetailEquipe> SelectAll ();
    DetailEquipe Insert(DetailEquipe equipe);
    ResponseEntity<DetailEquipe> SelectByID ( int id);
    DetailEquipe selectBy ( int id);
    ResponseEntity<DetailEquipe> update( DetailEquipe equipeDetails);
    ResponseEntity<HttpStatus> delete(  int id );
}
