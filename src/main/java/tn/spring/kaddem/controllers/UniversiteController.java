package tn.spring.kaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.kaddem.Services.IUniversiteService;
import tn.spring.kaddem.Services.UniversiteService;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;


@Controller
@RestController
@RequestMapping("/UniversiteController")
public class UniversiteController {

    private IUniversiteService universiteService;

    @Autowired
    public UniversiteController(@Qualifier("Universite") UniversiteService universiteService){
        this.universiteService = universiteService;
    }

    @GetMapping
    public List<Universite> SelectAll () {return universiteService.SelectAll();  }
   //GET http://localhost:8080/kaddem/UniversiteController

    @GetMapping("{id}")
    public ResponseEntity<Universite> SelectByID (@PathVariable int id) {return  universiteService.SelectByID(id);}

    @GetMapping("/SelectByMultiIDs")
    public List<Universite> SelectMultiByIDs (@RequestBody crudStruct select){return universiteService.SelectMultiByIDs(select);}

    @PostMapping("/Insert")
    public Universite Insert( @RequestBody Universite universite) {return universiteService.Insert(universite); }
    //post http://localhost:8080/kaddem/UniversiteController/Insert   {"nomUniv":"ISET Jandouba"}

    @PostMapping("/MultiInsert")
    public List<Universite> MultiInsert(@RequestBody List<Universite> universites) {return  universiteService.MultiInsert(universites);}
    //post http://localhost:8080/kaddem/UniversiteController/MultiInsert    [{"nomUniv":"ISET Rades"},{"nomUniv":"ISET Siliana"},{"nomUniv":"ISET Nabeul"}]

    @PutMapping("/Update")
    public  ResponseEntity<Universite> update(@RequestBody Universite UniversiteDetails){return  universiteService.update(UniversiteDetails); }

    @PutMapping("/MultiUpdate")
    public crudStruct MultiUpdate (@RequestBody List<Universite> UniversitesDetails){return  universiteService.MultiUpdate(UniversitesDetails); }

    @DeleteMapping("/Delete/{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return   universiteService.delete(id);}

    @DeleteMapping("/MultiDelete")
    public crudStruct MultiDelete(@RequestBody crudStruct delete ){return universiteService.MultiDelete(delete);}
}
