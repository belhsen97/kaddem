package tn.spring.kaddem.Services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@ConditionalOnProperty(name="Scheduler.enabled", matchIfMissing = true) ajouter Scheduler.enabled = true au propertie

@Slf4j
@Component
@EnableAsync
public class SchedulerService {

    @Qualifier("contrat")
    @Autowired
    private IContratService contratService;

    @Qualifier("equipe")
    @Autowired
    IEquipeService equipeService;




   //https://www.freeformatter.com/cron-expression-generator-quartz.html
  @Async
    @Scheduled(cron = "0 0 0 15 * *")//@Scheduled(fixedDelay = 60*60*1000, initialDelay = 5*1000)//The @Scheduled annotation is used to trigger the scheduler for a specific time period.
    public void StatusContrat_scheduleFixedDelayTask(){//System.out.println("Fixed Delay task - " + System.currentTimeMillis() / 1000);


      log.info(" retrieveAndUpdateStatusContrat called  ");
      log.info(contratService.retrieveAndUpdateStatusContrat());
    }

   /* @Async
    @Scheduled(fixedDelay = 60*60*1000, initialDelay = 6*1000)//The @Scheduled annotation is used to trigger the scheduler for a specific time period.
    public void EvoluerEquipes_scheduleFixedDelayTask()throws InterruptedException {
        log.info(" faire Evoluer Equipes ");
        equipeService.faireEvoluerEquipes();
    }*/




    private String getStringDateNow()
        {SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return formatter.format(new Date(System.currentTimeMillis()));}






}
