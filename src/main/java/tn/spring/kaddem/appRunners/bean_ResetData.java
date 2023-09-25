package tn.spring.kaddem.appRunners;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import tn.spring.kaddem.Services.*;
import tn.spring.kaddem.entities.*;

import java.text.SimpleDateFormat;
import java.util.*;


@Order(value=1)//Register BeanRunnerOne bean
@Slf4j
@Component
public class bean_ResetData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Bean One run method Started !!" );
        //this.reset( );
        //  List<Etudiant> etudiants = new ArrayList<Etudiant>();
        //for ( int i = 0 ; i< 1000000; i++)
        // {
        //etudiantService.delete(i);
        // etudiants.add(  new Etudiant( "prenom-"+i , "nom"+i , Option.SE ));
        // crudStruct. etudiants.add(  new Etudiant( "prenom-"+i , "nom"+i , Option.SE ));
        // }
        //  etudiantService.MultiInsert(etudiants);
        // etudiantService.delete();
    }

    @Autowired
    private IContratService contratService;

    @Autowired
    private IEtudiantService etudiantService;

    @Autowired
    private IUniversiteService universiteService;

    @Autowired
    private IDepartementService departementService;

    @Autowired
    private IEquipeService equipeService;

    @Autowired
    private IDetailEquipeService detailEquipeService;

    private Date convertCalendarToDate (int Year , int Mounth , int day) {
        Calendar cal = Calendar.getInstance();
        Mounth--;
        cal.set(Year, Mounth, day);
        Date date = cal.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String date1 = format.format(date);
        Date getDate = null;
        try {
            getDate = format.parse(date1);
        }
        catch (Exception e){System.out.println(e.getMessage());}
        return  getDate;
    }





    private void reset( ){
        log.info(" Bean One run ->  reset method");
        // contratService.getClosestDate();

        departementService.MultiInsert(departements);//8
        universiteService.MultiInsert(universites);//20
        departementService.assignUniversiteToDepartement(17,1);
        departementService.assignUniversiteToDepartement(18,2);
        departementService.assignUniversiteToDepartement(3,3);
        departementService.assignUniversiteToDepartement(15,4);
        departementService.assignUniversiteToDepartement(5,5);
        departementService.assignUniversiteToDepartement(11,6);
        departementService.assignUniversiteToDepartement(16,7);
        departementService.assignUniversiteToDepartement(2,8);
        etudiantService.MultiInsert(etudiants);
        etudiantService.assignEtudiantToDepartement(1,1);
        etudiantService.assignEtudiantToDepartement(2,2);
        etudiantService.assignEtudiantToDepartement(3,3);
        etudiantService.assignEtudiantToDepartement(4,4);
        etudiantService.assignEtudiantToDepartement(5,5);
        etudiantService.assignEtudiantToDepartement(6,6);
        etudiantService.assignEtudiantToDepartement(7,7);
        etudiantService.assignEtudiantToDepartement(8,8);
        etudiantService.assignEtudiantToDepartement(9,1);
        etudiantService.assignEtudiantToDepartement(10,2);
        etudiantService.assignEtudiantToDepartement(12,3);
        etudiantService.assignEtudiantToDepartement(13,4);
        etudiantService.assignEtudiantToDepartement(14,5);
        etudiantService.assignEtudiantToDepartement(15,6);
        etudiantService.assignEtudiantToDepartement(16,7);
        etudiantService.assignEtudiantToDepartement(17,8);
        etudiantService.assignEtudiantToDepartement(18,1);
        etudiantService.assignEtudiantToDepartement(19,2);
        etudiantService.assignEtudiantToDepartement(20,3);
        etudiantService.assignEtudiantToDepartement(21,4);
        etudiantService.assignEtudiantToDepartement(22,5);
        etudiantService.assignEtudiantToDepartement(23,6);
        etudiantService.assignEtudiantToDepartement(24,7);
        etudiantService.assignEtudiantToDepartement(25,8);
        detailEquipeService.Insert(DetailEquipe1);
        detailEquipeService.Insert(DetailEquipe2);
        detailEquipeService.Insert(DetailEquipe3);
        detailEquipeService.Insert(DetailEquipe4);
        detailEquipeService.Insert(DetailEquipe5);
        // equipeService.Insert( Equipe1 );
        // etudiantService.assignEtudiantToEquipe( 2,  Equipe1.getIdEquipe());
        // etudiantService.assignEtudiantToEquipe( 2,  Equipe1.getIdEquipe());
        //equipeService.Insert(Equipe1);


        // etudiantService.assignEtudiantToEquipe(1,1);


        ArrayList<Integer> Etudiants_de_Equipe1 = new ArrayList<Integer>(Arrays.asList(1,2,3));
        etudiantService.assignEtudiantsToEquipe ( Equipe1 , Etudiants_de_Equipe1);
        ArrayList<Integer> Etudiants_de_Equipe2 = new ArrayList<Integer>(Arrays.asList(4, 5, 6));
        etudiantService.assignEtudiantsToEquipe ( Equipe2 , Etudiants_de_Equipe2);
        ArrayList<Integer> Etudiants_de_Equipe3 = new ArrayList<Integer>(Arrays.asList(7, 8, 9));
        etudiantService.assignEtudiantsToEquipe ( Equipe3 , Etudiants_de_Equipe3);
        ArrayList<Integer> Etudiants_de_Equipe4 = new ArrayList<Integer>(Arrays.asList(11, 12, 13 ));
        etudiantService.assignEtudiantsToEquipe ( Equipe4 , Etudiants_de_Equipe4);
        ArrayList<Integer> Etudiants_de_Equipe5 = new ArrayList<Integer>(Arrays.asList(14, 15, 16));
        etudiantService.assignEtudiantsToEquipe ( Equipe5 , Etudiants_de_Equipe5);
        ArrayList<Integer> Etudiants_de_Equipe6 = new ArrayList<Integer>(Arrays.asList(17, 18, 19 , 15 ,16 , 13));
        etudiantService.assignEtudiantsToEquipe ( Equipe6 , Etudiants_de_Equipe6);
        ArrayList<Integer> Etudiants_de_Equipe7 = new ArrayList<Integer>(Arrays.asList(20, 21, 22));
        etudiantService.assignEtudiantsToEquipe ( Equipe7 , Etudiants_de_Equipe7);
        ArrayList<Integer> Etudiants_de_Equipe8 = new ArrayList<Integer>(Arrays.asList(1, 4, 7));
        etudiantService.assignEtudiantsToEquipe ( Equipe8 , Etudiants_de_Equipe8);
        ArrayList<Integer> Etudiants_de_Equipe9 = new ArrayList<Integer>(Arrays.asList(11, 14, 17));
        etudiantService.assignEtudiantsToEquipe ( Equipe9 , Etudiants_de_Equipe9);
        ArrayList<Integer> Etudiants_de_Equipe10 = new ArrayList<Integer>(Arrays.asList(1, 2, 3 ));
        etudiantService.assignEtudiantsToEquipe ( Equipe10 , Etudiants_de_Equipe10);
        equipeService.assignEquipeToDetailEquipe(1,1);
        equipeService.assignEquipeToDetailEquipe(2,2);
        equipeService.assignEquipeToDetailEquipe(3,3);
        equipeService.assignEquipeToDetailEquipe(4,4);
        equipeService.assignEquipeToDetailEquipe(5,5);


        equipeService.assignEquipeToDetailEquipe(6,1);
        equipeService.assignEquipeToDetailEquipe(7,2);
        equipeService.assignEquipeToDetailEquipe(8,3);
        equipeService.assignEquipeToDetailEquipe(9,4);
        equipeService.assignEquipeToDetailEquipe(10,5);

        contratService.ajouteNouveauContrat(contrat1,1 );
        contratService.ajouteNouveauContrat(contrat2,2 );
        contratService.ajouteNouveauContrat(contrat3,3 );
        contratService.ajouteNouveauContrat(contrat4,4 );
        contratService.ajouteNouveauContrat(contrat5,5 );
        contratService.ajouteNouveauContrat(contrat6,6 );
        contratService.ajouteNouveauContrat(contrat7,7 );
        contratService.ajouteNouveauContrat(contrat8,8 );
        contratService.ajouteNouveauContrat(contrat9,9 );
        contratService.ajouteNouveauContrat(contrat10,10 );
        contratService.ajouteNouveauContrat(contrat11,11 );
        contratService.ajouteNouveauContrat(contrat12,12 );
        contratService.ajouteNouveauContrat(contrat13,13 );
        contratService.ajouteNouveauContrat(contrat14,14 );
        contratService.ajouteNouveauContrat(contrat15,15 );
        contratService.ajouteNouveauContrat(contrat16,16 );

    }


    public Etudiant etudiant1 = new Etudiant( "Mohamed" , "Zakaria" , Option.SE );
    public Etudiant etudiant2 = new Etudiant( "Jalil" , "Skander" , Option.SE );
    public Etudiant etudiant3 = new Etudiant( "Hassen" , "Malek" , Option.SE );
    public Etudiant etudiant4 = new Etudiant( "Lilia" , "Slim" , Option.SE );
    public Etudiant etudiant5 = new Etudiant( "Célina" , "Yassine" , Option.SE );
    public Etudiant etudiant6 = new Etudiant( "Sarah" , "Sami" , Option.SE );
    public Etudiant etudiant7 =  new Etudiant( "Kmar" , "Fedi" , Option.GAMIX );
    public Etudiant etudiant8 =  new Etudiant( "Mina" , "Jad" , Option.GAMIX );
    public Etudiant etudiant9 =  new Etudiant( "Lena" , "Arslène" , Option.GAMIX );
    public Etudiant etudiant10 = new Etudiant( "Lydia" , "Elyes" , Option.GAMIX );
    public Etudiant etudiant11 = new Etudiant( "Hela" , "Adam" , Option.GAMIX );
    public Etudiant etudiant12 = new Etudiant( "Camilia" , "Mehdi" , Option.SIM );
    public Etudiant etudiant13 = new Etudiant( "Sabrina" , "Bilel" , Option.SIM );
    public Etudiant etudiant14 = new Etudiant( "Sofia" , "Zied" , Option.SIM );
    public Etudiant etudiant15 = new Etudiant( "Baya" , "Hedi" , Option.SIM );
    public Etudiant etudiant16 = new Etudiant( "Amel" , "Ben Ali" , Option.SIM );
    public Etudiant etudiant17 = new Etudiant( "Elyssa" , "Youssef" , Option.SIM );
    public Etudiant etudiant18 = new Etudiant( "Leila" , "Feres" , Option.NIDS );
    public Etudiant etudiant19 = new Etudiant( "Salma" , "Khalil" , Option.NIDS );
    public Etudiant etudiant20 = new Etudiant( "Ines" , "Mahjoub" , Option.NIDS );
    public Etudiant etudiant21 = new Etudiant( "Cyrine" , "Ben Ali" , Option.NIDS );
    public Etudiant etudiant22 = new Etudiant( "Rayan" , "Marzouk" , Option.NIDS );
    public Etudiant etudiant23 = new Etudiant( "Amir" , "Khaled" , Option.NIDS );
    public Etudiant etudiant24 = new Etudiant( "Soad" , "Saad" , Option.NIDS );
    public Etudiant etudiant25 = new Etudiant( "Lamia" , "Lahmer" , Option.NIDS );
    public List<Etudiant> etudiants = Arrays.asList(etudiant1,etudiant2,etudiant3,etudiant4,etudiant5,etudiant6,etudiant7,etudiant8,etudiant9,etudiant10,etudiant11,etudiant12,etudiant13,etudiant14,etudiant15,etudiant16,etudiant17,etudiant18,etudiant19,etudiant20,etudiant21,etudiant22,etudiant23,etudiant24,etudiant25);

    public Universite universite1 = new Universite("Institut supérieur des sciences infirmières de Kairouan");// 25
    public Universite universite2 = new Universite("SUPCOM"); // 137
    public Universite universite3 = new Universite("École supérieure des études administratives et commerciales");  //12456
    public Universite universite4 = new Universite("École polytechnique internationale privée de Tunis "); //024
    public Universite universite5 = new Universite("École privée de technologies de l'information et de management de l'entreprise");//1245
    public Universite universite6 = new Universite("École supérieure de génie informatique et de technologie");//0157
    public Universite universite7 = new Universite("Ecole Supérieure Privée d’Ingénierie et de Technologie appliquée- Sousse");//016
    public Universite universite8 = new Universite("Université de technologie et de management");//045
    public Universite universite9 = new Universite("Université privée des sciences, arts et techniques de Tunis");//235
    public Universite universite10 = new Universite("Ecole Supérieure Privée de Technologie, d’Informatique et de Gestion de Sousse");//123456
    public Universite universite11 = new Universite("Tunis El Manar University");//01567
    public Universite universite12 = new Universite("Faculté Privée des sciences de l’informatique et de management de Nabeul");//12345
    public Universite universite13 = new Universite("Ecole Méditerranéenne Supérieure Privée des Sciences Informatiques, d’Economie et de Gestion de Nabeul");//012456
    public Universite universite14 = new Universite("Ecole Supérieure Privée des Technologies de l’Information et de Management de Nabeul");//134567
    public Universite universite15 = new Universite("Ecole Supérieure Privée d’ingénieurs de Monastir");//013
    public Universite universite16 = new Universite("Université de Monastir Ecole Supérieure Polytechnique Privée de Monastir");///0135
    public Universite universite17 = new Universite("Ecole Nationale d'Ingénieurs de Tunis");//0357
    public Universite universite18 = new Universite("ESPRIT");//17
    public Universite universite19 = new Universite("Ecole Nationale des Sciences et Technologies Avancées de Borj Cédria - ENSTAB");//01347
    public Universite universite20 = new Universite("École nationale supérieure d'ingénieurs de Tunis (ENSIT)");//01367
    public List<Universite> universites = Arrays.asList(universite1,universite2,universite3,universite4,universite5,universite6,universite7,universite8,universite9,universite10,universite11,universite12,universite13,universite14,universite15,universite16,universite17,universite18,universite19,universite20);


    public Departement departement1 =  new Departement("genie-electrique");
    public Departement departement2 =  new Departement("genie-informatique");
    public Departement departement3 =  new Departement("commerciale");
    public Departement departement4 =  new Departement("Technologie d’Informations et des Communications");
    public Departement departement5 =  new Departement("genie-Industriel");
    public Departement departement6 =  new Departement("Mathématique");
    public Departement departement7 =  new Departement("Mathématiques et Informatique");
    public Departement departement8 =  new Departement("Architecture, Systèmes et Réseaux");
    public List<Departement> departements = Arrays.asList(departement1,departement2,departement3,departement4,departement5,departement6,departement7,departement8);


    public Contrat contrat1 = new Contrat (Specialite.RESEAUX , this.convertCalendarToDate (2005,05,18) ,this.convertCalendarToDate (2006,05,17)   , false , 350);
    public Contrat contrat2 = new Contrat (Specialite.RESEAUX , this.convertCalendarToDate (2010,02,22) ,this.convertCalendarToDate (2025,05,24)   , false , 350);
    public Contrat contrat3 = new Contrat (Specialite.RESEAUX , this.convertCalendarToDate (2022,01,02) ,this.convertCalendarToDate (2022,06,30)   , false , 350);
    public Contrat contrat4 = new Contrat (Specialite.RESEAUX , this.convertCalendarToDate (2022,12,10) ,this.convertCalendarToDate (2028,12,9)   , false , 350);
    public Contrat contrat5 = new Contrat (Specialite.IA , this.convertCalendarToDate (1994,01,15) ,this.convertCalendarToDate (2030,01,14)   , false , 300);
    public Contrat contrat6 = new Contrat (Specialite.IA , this.convertCalendarToDate (2000,02,16) ,this.convertCalendarToDate (2002,02,15)   , false , 300);
    public Contrat contrat7 = new Contrat (Specialite.IA , this.convertCalendarToDate (2017,03,17) ,this.convertCalendarToDate (2023,03,16)   , false , 300);
    public Contrat contrat8 = new Contrat (Specialite.IA , this.convertCalendarToDate (2018,04,18) ,this.convertCalendarToDate (2023,04,17)   , false , 300);
    public Contrat contrat9 = new Contrat (Specialite.CLOUD , this.convertCalendarToDate (2016,05,19) ,this.convertCalendarToDate (2030,05,18)   , false , 400);
    public Contrat contrat10 = new Contrat (Specialite.CLOUD , this.convertCalendarToDate (2003,06,20) ,this.convertCalendarToDate (2020,06,19)   , false , 400);
    public Contrat contrat11 = new Contrat (Specialite.CLOUD , this.convertCalendarToDate (2020,07,21) ,this.convertCalendarToDate (2026,07,20)   , false , 400);
    public Contrat contrat12 = new Contrat (Specialite.CLOUD , this.convertCalendarToDate (2021,8,22) ,this.convertCalendarToDate (2024,8,21)   , false , 400);
    public Contrat contrat13 = new Contrat (Specialite.SECURITE , this.convertCalendarToDate (1999,9,23) ,this.convertCalendarToDate (2036,9,22)   , false , 450);
    public Contrat contrat14 = new Contrat (Specialite.SECURITE , this.convertCalendarToDate (2022,10,24) ,this.convertCalendarToDate (2026,10,23)   , false , 450);
    public Contrat contrat15 = new Contrat (Specialite.SECURITE , this.convertCalendarToDate (2022,11,25) ,this.convertCalendarToDate (2042,11,24)   , false , 450);
    public Contrat contrat16 = new Contrat (Specialite.SECURITE , this.convertCalendarToDate (2022,12,26) ,this.convertCalendarToDate (2032,12,25)   , false , 450);
    public List<Contrat> contrats = Arrays.asList(contrat1,contrat2,contrat3,contrat4,contrat5,contrat6,contrat7,contrat8,contrat9,contrat10,contrat11,contrat12,contrat13,contrat14,contrat15,contrat16);

    public Equipe Equipe1 =  new Equipe("Corporation" , Niveau.JUNIOR);
    public Equipe Equipe2 =  new Equipe("NetIQ" , Niveau.JUNIOR);
    public Equipe Equipe3 =  new Equipe("Unisys" , Niveau.JUNIOR);
    public Equipe Equipe4 =  new Equipe("Thirdwave" , Niveau.JUNIOR);
    public Equipe Equipe5 =  new Equipe("Fusion-IO" , Niveau.JUNIOR);
    public Equipe Equipe6 =  new Equipe("Digisonics" , Niveau.JUNIOR);
    public Equipe Equipe7 =  new Equipe("Communications" , Niveau.JUNIOR);
    public Equipe Equipe8 =  new Equipe("Logiciel" , Niveau.JUNIOR);
    public Equipe Equipe9 =  new Equipe("Dealogic" , Niveau.JUNIOR);
    public Equipe Equipe10 =  new Equipe("Blueswitch" , Niveau.JUNIOR);
    public List<Equipe> equipes = Arrays.asList(Equipe1,Equipe2,Equipe3,Equipe4,Equipe5,Equipe6,Equipe7,Equipe8,Equipe9,Equipe10);

    public DetailEquipe DetailEquipe1 =  new DetailEquipe(105,"thematique-1");
    public DetailEquipe DetailEquipe2 =  new DetailEquipe(219,"thematique-2");
    public DetailEquipe DetailEquipe3 =  new DetailEquipe(405,"thematique-3");
    public DetailEquipe DetailEquipe4 =  new DetailEquipe(18,"thematique-4");
    public DetailEquipe DetailEquipe5 =  new DetailEquipe(364,"thematique-5");
    public List<DetailEquipe> detailEquipes = Arrays.asList(DetailEquipe1,DetailEquipe2,DetailEquipe3,DetailEquipe4,DetailEquipe5);
}