package tn.spring.kaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.kaddem.Services.ContratService;
import tn.spring.kaddem.Services.DepartementService;
import tn.spring.kaddem.Services.IDepartementService;
import tn.spring.kaddem.entities.Departement;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.repositorys.DepartementRepository;
import tn.spring.kaddem.struct.AsignStruct;

import java.util.ArrayList;
import java.util.List;


@Controller
@RestController
@RequestMapping("/DepartementController")
public class DepartementController {
    private IDepartementService departementService;

    @Autowired
    public DepartementController(@Qualifier("departement") DepartementService departementService){this.departementService = departementService;}

    @GetMapping
    public List<Departement> SelectAll () {return departementService.SelectAll ();}

    @GetMapping("{id}")
    public ResponseEntity<Departement> SelectByID (@PathVariable int id) { return departementService.SelectByID ( id);}

    @PostMapping("/Insert")
    public Departement Insert( @RequestBody Departement departement) {return departementService.Insert( departement);}

    @PostMapping("/MultiInsert")
    public List<Departement> MultiInsert( @RequestBody List<Departement> departements) { return departementService.MultiInsert( departements);}

    @PutMapping("/Update")
    public  ResponseEntity<Departement> update(@RequestBody Departement departementDetails){ return departementService.update( departementDetails);}

    @DeleteMapping("/Delete/{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ) { return departementService.delete(  id );}


    @PutMapping("/Asignid-Universite-To-Departement")
    public Departement assignUniversiteToDepartement(@RequestBody AsignStruct Asign)
    {return departementService.assignUniversiteToDepartement( Asign.idUniversite,  Asign.IdDEpartement); }

    @PostMapping("/Select-by-Universite")
    public List<Departement> retrieveDepartementsByUniversite (@RequestBody AsignStruct Asign)
    {
        return departementService.retrieveDepartementsByUniversite ( Asign.idUniversite);
    }
}



   /* @PostMapping
    public String  Insert( @RequestBody Departement departement)  //this annotation will convert a json into java object d
    {
        departementRepository.save(departement) ;
        return  new ResponseTransfer("success").getJSON();
    }*/



/*
POST http://localhost:8080/kaddem/DepartementController/Insert
{"nomDepart": "Technologie d’Informations et des Communications"}


POST http://localhost:8080/kaddem/DepartementController/MultiInsert
[{"nomDepart": "Technologie d’Informations et des Communications"},
{"nomDepart": "Génie Civil"},
{"nomDepart": "Génie hydraulique et environnement"},
{"nomDepart": "Génie Electrique"},
{"nomDepart": "Génie Industriel"},
{"nomDepart": "Génie Mécanique"}]
* */