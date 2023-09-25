package tn.spring.kaddem.configuration;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import tn.spring.kaddem.otherUsed.Performance;

@Aspect//AOP
@Component// pour dire cest bean spring cest composant sping nest pas un service ou controller ou ..
@Slf4j
public class performanceAspect {



    //L’aspect de gestion de la supervision
    @Around("@annotation(performance)")
    public Object performance(ProceedingJoinPoint joinPoint, Performance performance) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            long end = System.currentTimeMillis();
            long duree = end - start;
            log.info("\u001B[32m"+"Performance de "+  joinPoint.toShortString() +" "
                    +" egale : "+String.valueOf(duree) +"ms" +"\u001B[0m");
            log.debug("AAAAAAAAAAAAAAAAAAA");
        }
    }



}
