package tn.spring.kaddem.otherUsed.GraphQL;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import tn.spring.kaddem.Services.IUniversiteService;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.repositorys.UniversiteRepository;

public class Query implements GraphQLRootResolver {
    @Autowired
    private UniversiteRepository universiteRepository;
    public Query() {}

    public List<Universite> allUniversites(){   return universiteRepository.findAll();  }
    /* POST http://localhost:8080/GraphQLLogement/graphql
{
    allRendezVousList
    {
        id,date,heure,numTel,
        logement {
                   reference,adresse,delegation,gouvernorat,type,description,prix
                 }
    }
}*/

    
}
 