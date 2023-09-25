package tn.spring.kaddem.otherUsed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.otherUsed.ex;
import tn.spring.kaddem.struct.crudStruct;

import java.util.List;

@Controller
@RestController
@RequestMapping("/exController")
public class excontroller {
    private ex Ex;

    @Autowired
    public excontroller(@Qualifier("ex") ex Ex){
        this.Ex = Ex;
    }

    @GetMapping
    public List<Etudiant> SelectAll () {return (List<Etudiant>) Ex.SelectAll();  }
    //GET http://localhost:8080/kaddem/UniversiteController


    @GetMapping("/SelectByMultiIDs")
    public List<Etudiant> SelectMultiByIDs (@RequestBody crudStruct select){
        return (List<Etudiant>) Ex.SelectMultiBy(select);}

    @PostMapping("/MultiInsert")
    public List<Etudiant> MultiInsert(@RequestBody List<Etudiant> etudiant) {return (List<Etudiant>) Ex.multiInsert(etudiant);}
    //post http://localhost:8080/kaddem/UniversiteController/MultiInsert    [{"nomUniv":"ISET Rades"},{"nomUniv":"ISET Siliana"},{"nomUniv":"ISET Nabeul"}]


}
