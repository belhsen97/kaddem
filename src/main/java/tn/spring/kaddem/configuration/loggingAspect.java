package tn.spring.kaddem.configuration;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
@Aspect//AOP
@Component// pour dire cest bean spring cest composant sping nest pas un service ou controller ou ..
@Slf4j
public class loggingAspect {//package            //.Class         //methodes  (..) tous les parameters
    @Before(" execution(* tn.spring.kaddem.Services.EtudiantService.*(..)) ")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method " + name + " : ");
    }
    @After(" execution(* tn.spring.kaddem.Services.*.*(..)) ")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info(" Out of method " + name + " : ");
    }
    @AfterReturning(" execution(* tn.spring.kaddem.Services.*.*(..)) ")
    public void logMethodReturning(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info(" AfterReturning of method " + name + " : ");
    }
    @AfterThrowing(" execution(* tn.spring.kaddem.Services.*.*(..)) ")
    public void logMethodThrowing(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info(" AfterThrowing of method " + name + " : ");
    }
}
