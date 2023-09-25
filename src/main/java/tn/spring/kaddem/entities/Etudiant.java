package tn.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Data est l’annotation qui regroupe @Getter, @Setter, @ToString,@EqualsAndHashCode et @RequiredArgsConstructor.
//@EqualsAndHashCode

@Entity
@Table(name = "Etudiant")
@NoArgsConstructor //permet la génération de constructeur par défaut.
@AllArgsConstructor // généré un constructeur avec tous les attributs.
@Getter
@Setter
@RequiredArgsConstructor //  définit un constructeur avec seulement les attributs non nuls (@NonNull).   ex on final ajouter  final idEtudiant final prenomE final nomE  final option
@ToString //génère une implémentation pour la méthode toString()par défaut.
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtudiant")
    int idEtudiant ;

    @Column(name = "prenomE")
    @NotNull
    String prenomE ;

    @Column(name = "nomE")
    @NotNull
    String nomE ;

    @Column(name = "optionE")
    @Enumerated(EnumType.STRING)
    @NotNull
    Option option ;

    //@ManyToOne()
    //Departement departement;
    @ManyToOne(cascade = {CascadeType.PERSIST})//(cascade =CascadeType.PERSIST)//        pour ajouter au post man les deux etudiant et depardement   et inserer les deux                  (cascade =CascadeType.all)
    Departement departement;


    //   inverse eager cest lazy   ki yichargi etudiant mayijibch contrat qui eqale null
    @OneToMany(mappedBy = "etudiant",cascade = {CascadeType.PERSIST,CascadeType.REMOVE} , fetch = FetchType.EAGER) //    recherche la relation oubien recuperation            Association entre many contrats et one etudiant
    @JsonIgnore
    Set<Contrat> contrats;


    /*@ManyToMany(mappedBy="etudiants", cascade = CascadeType.ALL)// @JsonIgnore // boucle infini error 500 dans Asign Etudiant
    private Set<Equipe> equipes; //private List<Equipe> Equipes;*/





















    //Parent
    //@ManyToMany()
    //Set<Equipe> equipes;



    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)// (fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})//(fetch = FetchType.EAGER)
    @JoinTable(name = "etudiant_equipes",joinColumns = { @JoinColumn(name = "id_etudiant") },inverseJoinColumns = { @JoinColumn(name = "id_equipe") })
     Set<Equipe> equipes = new HashSet<>();// @JsonIgnore

    //@JsonGetter("equipes")
    //public  Set<Equipe> get_Equipes() { return this.equipes; }
}
