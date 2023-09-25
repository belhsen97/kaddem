package tn.spring.kaddem.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.spring.kaddem.entities.Equipe;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;


public interface IEquipeService {
    List<Equipe> SelectAll ();
    ResponseEntity<Equipe> SelectByID ( int id);
    List<Equipe> SelectByMultiIDs ( crudStruct select);
    Equipe Insert(Equipe equipe);
    List<Equipe> MultiInsert(List<Equipe> equipes);
    ResponseEntity<Equipe> update( Equipe equipeDetails);
    crudStruct MultiUpdate (List<Equipe> equipes);
    ResponseEntity<HttpStatus> delete( Integer id );
    crudStruct MultiDelete(crudStruct delete );
    Equipe assignEquipeToDetailEquipe(Integer idEquipe, Integer IdDetailEquipe);
    void faireEvoluerEquipes();
    public Equipe AddAfect_EquipeEtudiantsDetailEquipe(  Equipe equipe);
    void upgradeNiveau( Equipe equipe);

}
