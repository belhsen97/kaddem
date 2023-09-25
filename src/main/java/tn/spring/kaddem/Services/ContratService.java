package tn.spring.kaddem.Services;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.spring.kaddem.entities.Contrat;
import tn.spring.kaddem.entities.DetailEquipe;
import tn.spring.kaddem.entities.Equipe;
import tn.spring.kaddem.entities.Etudiant;
import tn.spring.kaddem.exceptions.RessourceNotFoundException;
import tn.spring.kaddem.otherUsed.Performance;
import tn.spring.kaddem.repositorys.ContratRepository;
import tn.spring.kaddem.repositorys.EtudiantRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//on peut utilise @RequiredArgsConstructor // Methode 1  comme injection de depondance  @Autowired // Methode 2


@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("contrat")
public class ContratService implements IContratService {

    private ContratRepository contratRepository;
    @Autowired // Methode 2
    public ContratService (ContratRepository contratRepository){this.contratRepository = contratRepository;}




    @Autowired
    IEtudiantService etudiantService; //https://stackoverflow.com/questions/41608705/the-dependencies-of-some-of-the-beans-in-the-application-context-form-a-cycle



    @Performance()
    @Override
    public List<Contrat> SelectAll () //fetch a list of employees from database   return list
    { return contratRepository.findAll(); }


    public Contrat Insert(  Contrat contrat)  //this annotation will convert a json into java object d
    { return contratRepository.save(contrat);}

    @Override
    public ResponseEntity<Contrat> SelectByID ( int id) {
        Contrat contratSelectByID = contratRepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("Utilisateur not existe with id : "+id))  ;

        return ResponseEntity.ok(contratSelectByID);
    }

    @Override
    public Contrat selectBy ( int id) {
        Contrat contrat= contratRepository.findById(id).orElse(null) ;
        return contrat; }

    @Override
    public  ResponseEntity<Contrat> update(  Contrat contratDetails){
        Contrat updateContrat = contratRepository.findById(contratDetails.getIdContrat()).
                orElseThrow(()-> new RessourceNotFoundException("Employee not existe with id : "+contratDetails.getIdContrat()))  ;
        updateContrat.setDateDebutContrat(contratDetails.getDateDebutContrat());
        updateContrat.setDateFinContrat(contratDetails.getDateFinContrat());
        updateContrat.setSpecialite(contratDetails.getSpecialite());
        updateContrat.setMontantContrat(contratDetails.getMontantContrat());
        updateContrat.setArchive(contratDetails.getArchive());
        contratRepository.save(updateContrat);
        return ResponseEntity.ok(updateContrat);

    }

    @Override
    public  ResponseEntity<HttpStatus> delete(  Integer id ){
        Contrat deleteContrat = contratRepository.findById(id).orElseThrow(()-> new RessourceNotFoundException("Employee not existe with id : "+id))  ;
        contratRepository.delete(deleteContrat);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public Integer nbContratsValides(Date startDate, Date endDate)
    {
     /*  int countNbContratsValides = 0;
        List<Contrat> Contrats = this.SelectAll ();
        for (Contrat contrat : Contrats)
        {
            if (((  startDate.compareTo(contrat.getDateDebutContrat()) <= 0 ) || (  endDate.compareTo(contrat.getDateFinContrat()) < 0)) &&
                ((  startDate.compareTo(contrat.getDateDebutContrat()) > 0  ) || (  endDate.compareTo(contrat.getDateFinContrat()) >= 0) ) && contrat.getArchive() )
            {countNbContratsValides++;
           // System.out.println(contrat.getIdContrat());
            }
        }
        return countNbContratsValides;*/
        return   contratRepository. countByArchiveIsFalseAndDateDebutContratBetween( startDate,  endDate);
    }


    @Override
    public Contrat ajouteNouveauContrat(Contrat contrat, int IdEtudiant)
    {
        Etudiant etudiant = etudiantService.SelectBy (IdEtudiant);
        Contrat newContrat = this.Insert(contrat);
        return  etudiantService.assignEtudiantToContrat(newContrat.getIdContrat(),etudiant.getIdEtudiant());
    }

























    @Override
    public String retrieveAndUpdateStatusContrat(){
        String resultat="";
        Date dateNow = new Date(System.currentTimeMillis());
        List<Contrat> Contrats = this.SelectAll ();
        for (Contrat contrat : Contrats)
        {
            if(( contrat.getArchive() )||( contrat.getEtudiant() == null )){continue;}
            if( contrat.getArchive() ){continue;}

            if ((  dateNow.compareTo(contrat.getDateFinContrat()) >= 0  ))
            {
                contrat.setArchive(true);
                this.update(contrat);
                resultat += "fin de contrat id :"+contrat.getIdContrat() +
                        " et fin date :"+contrat.getDateFinContrat()+
                        " pour etudiant  "+contrat.getEtudiant().getNomE()+" "+
                        contrat.getEtudiant().getPrenomE();
                resultat += "\n";
            }
        }
        return resultat;
    }










    @Override
    public boolean isExistContrat(Contrat ce)
    {
        List<Contrat>  contrats = contratRepository.findAll();
        ce = contratRepository.findById(ce.getIdContrat()).orElse(null);
        boolean verified = false;
        for (Contrat contrat  : contrats )
        {
            if (contrat.equals(ce)) {verified = true;}
        }
        return verified;
    }

    @Override
    public boolean isExistContrat(Etudiant etudiant)// $$$$$$$$$$$$$$$   failed    $$$$$$$$$$$$$$$$$$
    {
        boolean verified = false;
        if (  this.CountContrat( etudiant) ==0 ){ return verified; }
        if ( etudiant.getContrats() == null ){ return verified; }
        System.out.println("AAAAAAA" +etudiant.getIdEtudiant());

        Set<Contrat> etudiantContrats = etudiant.getContrats();
        for (Contrat etudiantcontrat  : etudiantContrats ) {
            verified |=  isExistContrat(etudiantcontrat);
        }
        return verified;
    }                                      // $$$$$$$$$$$$$$$   failed    $$$$$$$$$$$$$$$$$$

    @Override
    public int CountContrat(Etudiant etudiant)
    {
        List<Contrat>  contrats = this.SelectAll ();
        int count = 0;
        for (Contrat contrat  : contrats )
        {
           if ( contrat.getEtudiant() == null){continue;}
            //if (contrat.getEtudiant() == etudiant) {count++;}
           // System.out.println("AAAAAAA"+ contrat.getEtudiant().getIdEtudiant());
            if (contrat.getEtudiant().getIdEtudiant() == etudiant.getIdEtudiant()) {count++;}
        }
        return count;
    }

    @Override
    public boolean validate (Etudiant etudiant)// $$$$$$$$$$$$$$$   failed    $$$$$$$$$$$$$$$$$$
    {
        boolean verified = false;
        if (  this.CountContrat( etudiant) ==0 ){ return verified; }
        if ( etudiant.getContrats() == null ){ return verified; }
        Date dateNow =  new Date(System.currentTimeMillis());
        Set<Contrat> etudiantContrats = etudiant.getContrats();
        for (Contrat contrat  : etudiantContrats ) {
            if (  dateNow.compareTo(contrat.getDateFinContrat()) < 0     ) {
                verified = true;
                break;
            }
        }
        return verified;
    }








    @Override
    public float getClosestDate()
    {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        //String format =  formatter.format(new Date(System.currentTimeMillis()));
        //List<Integer>  IDs  =new ArrayList<Integer>();
        //List<Float> Diffs  =new ArrayList<Float>(); 
        List<Contrat> Vcontrats  =new ArrayList<Contrat>();
        Date dateNow = new Date(System.currentTimeMillis());
        float c = 0;
        List<Contrat>  contrats = this.SelectAll ();
        for ( Contrat contrat : contrats)
        {
         if (  dateNow.compareTo(contrat.getDateFinContrat()) < 0   ){
             //IDs.add(contrat.getIdContrat());
            // Diffs.add( ( this.findDifference( dateNow, contrat.getDateFinContrat() )/(1000l * 60 * 60 * 24 * 365)) );
             Vcontrats.add(  contrat ) ;
         }
        }
        Contrat contrat = null;
        if ( Vcontrats.size() > 0) {
            contrat = Vcontrats.get(0);
            for (int i = 1; i < Vcontrats.size(); i++) {
                if ( contrat.getDateFinContrat().compareTo(Vcontrats.get(i).getDateFinContrat()) > 0            ) {
                    contrat = Vcontrats.get(i); 
                }
            }
        }
        /*float Diff = Diffs.get(0); int ID = IDs.get(0);
        if ( IDs.size() > 2) {
            for (int i = 1; i < IDs.size(); i++) {
                if (Diff > Diffs.get(i)) {
                    Diff = Diffs.get(i);
                    ID = IDs.get(i);
                }
            }
        }
        System.out.println(IDs); System.out.println(Diffs);
        System.out.println(Diff +" "+ID);
        //System.out.println( getStringFromDate(Date date, String format) Diff +" "+ID);*/
        System.out.println(contrat.getIdContrat());
        return 0 ;
    }









    @Override
    public float getChiffreAffaireEntreDeuxDate(/*Date*/ String startDate, /*Date*/String  endDate)
    {//                                                                          2000-11-01        2030-11-30
       float somme = 0,countDays = 0 ;
        List<Contrat>  contrats = this.SelectAll ();
        for ( Contrat contrat :  contrats ) {
              countDays = this.getNbrDays(contrat ,  startDate,   endDate) ;
        somme += ( countDays >0 ?       ((countDays/365)*(contrat.getMontantContrat()*12 ))          : 0 );
        }
        /*Contrat contrat = this.selectByID(15);
        //somme += ( this.getNbrDays_Par_Contrat( contrat ,  startDate,   endDate)/ 365) *(contrat.getMontantContrat()*12 );
        countDays = this.getNbrDays_Par_Contrat(contrat ,  startDate,   endDate) ;
        somme += ( countDays >0 ?       (countDays/365)          : 0 );
        //somme += ( countDays >0 ?       ((countDays/365)*(contrat.getMontantContrat()*12 ))          : 0 );*/
        System.out.println((countDays/365));
        System.out.println(somme);
        return  somme;
    }
    private float getNbrDays(Contrat contrat , String startDate, String  endDate  )
    {  //                                                                          2000-11-01        2030-11-30
        final String FormatDate ="yyyy-MM-dd";
        float totalDays = 0 ;
        Date startDatecontrat = contrat.getDateDebutContrat();
        Date finDatecontrat = contrat.getDateFinContrat();
        Date startDateEnter = this.getDateFromString(startDate, FormatDate);
        Date finDateEnter = this.getDateFromString(endDate, FormatDate);
        //System.out.println( "start "+   ( (startDateEnter .compareTo( finDatecontrat ) >= 0 )   ? "true":"false"));
        //System.out.println( "Fin "+   ( ( startDatecontrat  .compareTo( finDateEnter  ) >= 0 ) ? "true":"false"));
      if ((startDateEnter .compareTo( finDatecontrat ) >= 0 ) || ( startDatecontrat  .compareTo( finDateEnter  ) >= 0 )){ return  totalDays;}
        //ex  id = 2  2010-02-22 08:00:00   2025-05-24 07:00:00
       if ((startDatecontrat.compareTo( startDateEnter ) >= 0 )&&(finDatecontrat.compareTo( finDateEnter ) <= 0 ) )
        {System.out.println("1-"); System.out.println(contrat.getIdContrat());
            totalDays = this.findDifference( startDatecontrat , finDatecontrat );
            totalDays = totalDays/(1000 * 60 * 60 * 24);

        }
        //ex  id = 5    1994-01-15   2030-01-14
        if ( (startDatecontrat.compareTo( startDateEnter ) < 0 ) &&(finDatecontrat.compareTo( finDateEnter ) <= 0 ) )
        {  System.out.println("2-");  System.out.println(contrat.getIdContrat());
            totalDays = this.findDifference(startDateEnter , finDatecontrat );
            totalDays = totalDays/(1000 * 60 * 60 * 24);
        }
        // ex id = 15      2022-11-25      2042-11-24
        if ((startDatecontrat.compareTo( startDateEnter ) >= 0 ) && (finDatecontrat.compareTo( finDateEnter ) > 0 ) )
        {System.out.println("3-");  System.out.println(contrat.getIdContrat());
            totalDays = this.findDifference(startDatecontrat ,finDateEnter );
            totalDays = totalDays/(1000 * 60 * 60 * 24);
        }
        // ex id = 13   1999-09-23 07:00:00     2036-09-22 07:00:00
        if ((startDatecontrat.compareTo( startDateEnter ) < 0 )&&(finDatecontrat.compareTo( finDateEnter ) > 0 ))
        {System.out.println("4-");  System.out.println(contrat.getIdContrat());
            System.out.println(contrat.getIdContrat());
            totalDays = this.findDifference( startDateEnter ,finDateEnter );
            totalDays = totalDays/(1000 * 60 * 60 * 24);
        }
       // else {System.out.println("ce id contrat = "+contrat.getIdContrat()+" n'est pas en intervale de deux dates "); }
        return  totalDays;
    }
    private String getStringFromDate(Date date, String format)
    {SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);}

    private Date getDateFromString(String date, String format)
    {
        Date D = null;
        try{
             SimpleDateFormat formatter = new SimpleDateFormat(format);
            D = formatter.parse(date);
        }
        // Catch the Exception
        catch (ParseException e) { e.printStackTrace(); }
        return D;
    }
    private float  findDifference( Date start_date, Date end_date )
    {
        float resultat = 0;

        //try {    // Try Block
            // Calucalte time difference -> in milliseconds
            long difference_In_Time = end_date.getTime() - start_date.getTime();
            resultat = difference_In_Time;
            // Calucalte time difference in seconds, minutes, hours, years,and days
            long difference_In_Seconds = (difference_In_Time / 1000) % 60;
            long difference_In_Minutes = (difference_In_Time/ (1000 * 60)) % 60;
            long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
            long difference_In_Years  = (difference_In_Time  / (1000l * 60 * 60 * 24 * 365));
            long difference_In_Days    = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

            // Print the date difference inyears, in days, in hours, in minutes, and in seconds
            System.out.print( "\n");
            System.out.print( "Difference " + "between two dates is: ");
            System.out.println( difference_In_Years  + " years, "+
                    difference_In_Days+ " days, "+
                    difference_In_Hours+ " hours, "+
                    difference_In_Minutes+ " minutes, "+
                    difference_In_Seconds+ " seconds");
            System.out.print( "\n");
       // }
        // Catch the Exception
       // catch (ParseException e) {e.printStackTrace(); }
        return resultat;///(1000 * 60 * 60 * 24) ;
    }

}





    /*public static long getDateDiff(Date date1, Date date2) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long duration = date2.getTime() - date1.getTime(); //en MILLISECONDS
        return timeUnit.toDays(duration);
    }*/