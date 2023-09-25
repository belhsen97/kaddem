package tn.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Equipe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter//@EqualsAndHashCode
@RequiredArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Equipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEquipe")
    int idEquipe;

    @Column(name = "nomEquipe")
    @NotNull
    String nomEquipe;

    @Column(name = "niveau")
    @Enumerated(EnumType.STRING)
    @NotNull
    Niveau niveau;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    DetailEquipe detailEquipe;
    //@JsonSetter("detailEquipe")
    //public void set_DetailEquipe(DetailEquipe detailEquipe) { this.detailEquipe=detailEquipe; }


    /*@ManyToMany(cascade = CascadeType.ALL) // @JsonIgnore // boucle infini error 500 dans Asign Etudiant
    private Set<Etudiant> etudiants;//private Set<Etudiant> etudiants;*/

















   //child
    // @ManyToMany(mappedBy = "equipes") //failed to lazily initialize a collection of role: entity , could not initialize proxy - no Session
    //@ManyToMany(mappedBy = "equipes",fetch = FetchType.EAGER)  //We need to either add fetch=FetchType.EAGER inside your ManyToMany annotations to automatically pull back child entities
   // @JsonIgnore // boucle infini error 500 dans Asign Etudiant
   // Set<Etudiant> etudiants;

   @ManyToMany(mappedBy = "equipes",cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER) //(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE},mappedBy = "equipes")//(fetch = FetchType.EAGER, mappedBy = "equipes")
   @JsonIgnore
   Set<Etudiant> etudiants = new HashSet<>();

   //@JsonSetter("etudiants")
   //public void set_Etudiants(Set<Etudiant> etudiants) { this.etudiants=etudiants; }
}
