package tn.spring.kaddem.otherUsed.GraphQL;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import org.springframework.beans.factory.annotation.Autowired;
import tn.spring.kaddem.entities.Universite;
import tn.spring.kaddem.repositorys.UniversiteRepository;


public class Mutation implements GraphQLRootResolver {
    @Autowired
    private UniversiteRepository universiteRepository;
    public Mutation() { }


    public Boolean insert( int idUniv,String nomUniv ){
        Universite universite = new Universite(nomUniv);
        universiteRepository.save(universite);
        return true;
    }
}
