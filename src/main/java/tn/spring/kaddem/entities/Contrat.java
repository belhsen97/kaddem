package tn.spring.kaddem.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import java.util.List;

@Entity
@Table(name = "Contrat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString //@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contrat implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContrat")
    int idContrat;

    @Column(name = "dateDebutContrat")
    @Temporal(TemporalType.DATE)
    @NotNull
    Date dateDebutContrat;

    @Column(name = "dateFinContrat")
    @Temporal(TemporalType.DATE)
    @NotNull
    Date dateFinContrat;

    @Column(name = "specialite")
    @Enumerated(EnumType.STRING)
    @NotNull
    Specialite specialite;

    @Column(name = "archive")//@NotNull
    Boolean archive;

    @Column(name = "montantContrat")
    @NotNull
    int montantContrat;

    @ManyToOne(cascade = {CascadeType.PERSIST})// Association entre many contrats et one etudiant
    Etudiant etudiant;


    public Contrat (Etudiant etudiant ,Specialite specialite , Date dateDebutContrat,Date dateFinContrat , Boolean archive ,int montantContrat)
    { this.etudiant= etudiant;   this.specialite=specialite;  this.dateDebutContrat=dateDebutContrat;
        this.dateFinContrat=dateFinContrat;   this.archive=archive;  this.montantContrat=montantContrat;}
    public Contrat (Specialite specialite , Date dateDebutContrat,Date dateFinContrat , Boolean archive ,int montantContrat)
    {this.specialite=specialite;  this.dateDebutContrat=dateDebutContrat;
     this.dateFinContrat=dateFinContrat;   this.archive=archive;  this.montantContrat=montantContrat;}
}
