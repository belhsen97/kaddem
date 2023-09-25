package tn.spring.kaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.kaddem.Services.ContratService;
import tn.spring.kaddem.Services.IContratService;
import tn.spring.kaddem.entities.Contrat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RestController
@RequestMapping("/ContratController")
public class ContratController {

    private IContratService contratService;

    @Autowired
    public ContratController(@Qualifier("contrat") ContratService contratService){this.contratService = contratService;}

    @GetMapping
    public List<Contrat> SelectAll () {return  contratService. SelectAll () ;}

    @GetMapping("{id}")
    public ResponseEntity<Contrat> SelectByID (@PathVariable int id) {return  contratService.SelectByID ( id) ;}

    @PostMapping("/Insert")
    public Contrat Insert( @RequestBody Contrat contrat) {return  contratService.Insert(   contrat);}

    @PutMapping("/Update")
    public  ResponseEntity<Contrat> update( @RequestBody Contrat contratDetails){return  contratService.update(  contratDetails);}

    @DeleteMapping("/Delete/{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return  contratService.delete( id ); }


    @GetMapping("/Nbr-Contrats-Valides/{startDate}/{endDate}")
    public Integer nbContratsValides(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                     @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return contratService. nbContratsValides( startDate,  endDate);}

    @GetMapping("/Chiffre-Affaire-By-Two-Dates/{startDate}/{endDate}")
    public float getChiffreAffaireEntreDeuxDate(@PathVariable("startDate") /*@DateTimeFormat(pattern = "yyyy-MM-dd") Date*/String  startDate, @PathVariable("endDate") /*@DateTimeFormat(pattern = "yyyy-MM-dd") Date*/String endDate)
    {
        return contratService.getChiffreAffaireEntreDeuxDate( startDate,  endDate);
    }

    @GetMapping("/Retrieve-And-Update-Status-Contrat")
    public String retrieveAndUpdateStatusContrat(){
        return contratService.retrieveAndUpdateStatusContrat();
    }

}
















       /* SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse(startDate);
        Date d2 = sdformat.parse(endDate);
        return contratService. nbContratsValides( d1,  d2);*/