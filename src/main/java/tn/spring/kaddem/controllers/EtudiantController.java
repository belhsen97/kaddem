package tn.spring.kaddem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.kaddem.Services.EtudiantService;
import tn.spring.kaddem.Services.IEtudiantService;
import tn.spring.kaddem.entities.Contrat;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.struct.AsignStruct;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;

//AllArgsConstractor      private final EtudiantRepository etudiantRepository ;
//RequiredArgsConstractor private  EtudiantRepository etudiantRepository ;

@Controller
@RestController
@RequestMapping("/EtudiantController")
public class EtudiantController {

    //Logger logger = LoggerFactory.getLogger(EtudiantController.class);

    private IEtudiantService etudiantService;

    @Autowired
    public EtudiantController(@Qualifier("etudiant") EtudiantService etudiantService){this.etudiantService = etudiantService;}

    @GetMapping
    public List<Etudiant> SelectAll () {
        return etudiantService.SelectAll ();}

    @GetMapping("{id}")
    public ResponseEntity<Etudiant> SelectOneByID (@PathVariable int id) {  return etudiantService.SelectOneByID (id);}

    @GetMapping("/SelectByMultiIDs")
    public List<Etudiant> SelectByMultiIDs (@RequestBody crudStruct select) {  return etudiantService.SelectByMultiIDs (select.Ids);}

    @PostMapping("/Insert")
    public Etudiant Insert( @RequestBody Etudiant etudiant){  return etudiantService.Insert( etudiant);}
    // json -> memoire student deceliriation

    @PostMapping("/MultiInsert")
    public List<Etudiant> MultiInsert(@RequestBody List<Etudiant> etudiants) {  return etudiantService.MultiInsert(etudiants);}

    @PutMapping
    public  ResponseEntity<Etudiant> update (@RequestBody Etudiant etudiantDetails){  return etudiantService.update (etudiantDetails);}

    @PutMapping("/MultiUpdate")
    public crudStruct MultiUpdate (@RequestBody List<Etudiant> etudiants){  return etudiantService.MultiUpdate (etudiants);}

    @DeleteMapping("{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable Integer id ){  return etudiantService.delete( id );}

    @DeleteMapping("/MultiDelete")
    public crudStruct MultiDelete(@RequestBody crudStruct delete ){  return etudiantService.MultiDelete( delete );}


    @PutMapping("/Asignid-Etudient-To-Departement")
    public Etudiant assignEtudiantToDepartement(@RequestBody AsignStruct Asign)
    {
        return etudiantService.assignEtudiantToDepartement( Asign.idEtudient,  Asign.IdDEpartement);
    }

    @PutMapping("/Asignid-Etudiant-To-Equipe")
    public Etudiant assignEtudiantToEquipe(@RequestBody AsignStruct Asign)
    {
        return etudiantService.assignEtudiantToEquipe( Asign.idEtudient,  Asign.IdEquipe);
    }

    @PostMapping("/Select-by-Departement")
    public List<Etudiant> getEtudiantsByDepartement (@RequestBody AsignStruct Asign)
    {
        return etudiantService.getEtudiantsByDepartement ( Asign.IdDEpartement);
    }

    @PutMapping("/Asign-Etudiant-To-Contrat")
    public Contrat assignEtudiantToContrat(@RequestBody AsignStruct Asign){
        return etudiantService.assignEtudiantToContrat( Asign.idContrat , Asign.idEtudient );
    }



    @PutMapping("/Affect-Etudiant-To-Contrat/{nomE}/{prenomE}")
    Contrat affectContratToEtudiant (@RequestBody Contrat ce, @PathVariable String nomE,@PathVariable String prenomE){
    return  etudiantService.affectContratToEtudiant ( ce,  nomE, prenomE);
    }






    @PostMapping("/Add-Assign-EtudiantToEquipeAndContract")
    public Etudiant addAndAssignEtudiantToEquipeAndContract (@RequestBody AsignStruct Asign)
    {
        return etudiantService.addAndAssignEtudiantToEquipeAndContract( Asign.etudiant, Asign.idContrat,  Asign.IdEquipe);
    }


}
/*
POST http://localhost:8080/kaddem/EtudiantController/Insert
{"prenomE":"Belhsen","nomE":"Bachouch","option":"SE"}    GAMIX,SE,SIM,NIDS


POST http://localhost:8080/kaddem/EtudiantController/MultiInsert
[{"prenomE":"Mohamed","nomE":"matri","option":"SE"},
{"prenomE":"Yasmine","nomE":"ben saad","option":"GAMIX"},
{"prenomE":"Yassine","nomE":"ben abdalh","option":"SIM"},
{"prenomE":"Nourhane","nomE":"chanoufi","option":"SIM"},
{"prenomE":"neder","nomE":"ben ahmed","option":"NIDS"},
{"prenomE":"seif","nomE":"aisawi","option":"SE"}]
* */