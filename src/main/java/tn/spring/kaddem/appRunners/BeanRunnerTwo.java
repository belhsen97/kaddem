package tn.spring.kaddem.appRunners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


@Order(value=2)
@Slf4j
@Component
public class BeanRunnerTwo implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Bean Two run method Started !!" );

    }






    // @Scheduled(cron = "*/15 * * * * *")
    public void hello(){
        log.debug("log debug");
        log.info("log info");
        log.error("log error");
        log.warn("log warn");
    }

    // Timer t = new Timer();
    //t.schedule(tt, new Date(),1000); ;
    public  class Runnabletask implements Runnable{
        private String message;

        public Runnabletask(String message){
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println(new Date()+" Runnable Task with "+message+" on thread "+Thread.currentThread().getName());
        }
    }
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }


  /*  TimerTask tt = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Task is on");
        };
    };*/
}