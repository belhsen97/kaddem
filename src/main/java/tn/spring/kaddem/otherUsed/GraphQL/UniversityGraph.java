package tn.spring.kaddem.otherUsed.GraphQL;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import tn.spring.kaddem.Services.IUniversiteService;
import tn.spring.kaddem.entities.Universite;

import java.util.List;

//@Controller
public class UniversityGraph {

    @Autowired
    private IUniversiteService universiteService;

   // @QueryMapping(name="allUniversites")
    public List<Universite> allUniversites(){
        System.out.println("AAAAAAAAAAAAAAA");
        return universiteService.SelectAll();
    }
}
