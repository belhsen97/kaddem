package tn.spring.kaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.kaddem.Services.EquipeService;
import tn.spring.kaddem.Services.IEquipeService;
import tn.spring.kaddem.entities.Equipe;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;

@Controller
@RestController
@RequestMapping("/EquipeController")
public class EquipeController {

    private IEquipeService equipeService;

    @Autowired
    public EquipeController(@Qualifier("equipe") EquipeService equipeService){this.equipeService = equipeService;}

    @GetMapping
    public List<Equipe> SelectAll () { return  equipeService.SelectAll () ;}

    @GetMapping("/SelectByMultiIDs")
    public List<Equipe> SelectByMultiIDs (@RequestBody crudStruct select) {  return equipeService.SelectByMultiIDs (select);}

    @GetMapping("{id}")
    public ResponseEntity<Equipe> SelectByID (@PathVariable int id) {return  equipeService.SelectByID ( id);}

    @PostMapping("/Insert")
    public Equipe Insert( @RequestBody Equipe equipe) {return  equipeService.Insert( equipe) ; }

    @PostMapping("/MultiInsert")
    public List<Equipe> MultiInsert(@RequestBody List<Equipe> equipes) {  return equipeService.MultiInsert(equipes);}

    @PutMapping("/Update")
    public  ResponseEntity<Equipe> update(@RequestBody Equipe equipeDetails){return  equipeService.update( equipeDetails) ;}

    @PutMapping("/MultiUpdate")
    public crudStruct MultiUpdate (@RequestBody List<Equipe> equipesDetails){  return equipeService.MultiUpdate (equipesDetails);}

    @DeleteMapping("/Delete/{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return  equipeService.delete( id ) ; }

    @DeleteMapping("/MultiDelete")
    public crudStruct MultiDelete(@RequestBody crudStruct delete ){  return equipeService.MultiDelete( delete );}



    @PostMapping("/Add-Afect-Equipe-Etudiants-DetailEquipe")
    public Equipe AddAfect_EquipeEtudiantsDetailEquipe(@RequestBody Equipe equipe)
    { return  equipeService.AddAfect_EquipeEtudiantsDetailEquipe( equipe); }
}
