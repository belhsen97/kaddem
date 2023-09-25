package tn.spring.kaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


// @ComponentScan("")  avec Confeguration recherche les bens spring hors package ici
//@EnableSwagger2
//http://localhost:8080/kaddem/swagger-ui/index.html
//https://springdoc.org/


@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy // activer Aspect
public class KaddemApplication  {
	public static void main(String[] args) {SpringApplication.run(KaddemApplication.class, args);}}
