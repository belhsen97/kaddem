package tn.spring.kaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.kaddem.Services.ContratService;
import tn.spring.kaddem.Services.DetailEquipeService;
import tn.spring.kaddem.Services.IDetailEquipeService;
import tn.spring.kaddem.entities.DetailEquipe;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.repositorys.EquipeRepository;
import java.util.List;

@Controller
@RestController
@RequestMapping("/DetailEquipeController")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DetailEquipeController {

    private IDetailEquipeService detailEquipeService;

    @Autowired
    public DetailEquipeController(@Qualifier("detailequipe") DetailEquipeService detailEquipeService){this.detailEquipeService = detailEquipeService;}

    @GetMapping
    public List<DetailEquipe> SelectAll () { return  detailEquipeService.SelectAll () ;}

    @PostMapping("/Insert")
    public DetailEquipe Insert( @RequestBody DetailEquipe detailEquipe) {return  detailEquipeService.Insert( detailEquipe) ; }

    @GetMapping("{id}")
    public ResponseEntity<DetailEquipe> SelectByID (@PathVariable int id) {return  detailEquipeService.SelectByID ( id);}

    @PutMapping("/Update")
    public  ResponseEntity<DetailEquipe> update(@RequestBody DetailEquipe detailEquipe){return  detailEquipeService.update( detailEquipe) ;}

    @DeleteMapping("/Delete/{id}")
    public  ResponseEntity<HttpStatus> delete(@PathVariable  Integer id ){return  detailEquipeService.delete( id ) ; }
}
