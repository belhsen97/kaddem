package tn.spring.kaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Departement")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepart")
    int idDepart;

    @Column(name = "nomDepart")
    @NotNull
    String nomDepart;

    @OneToMany( cascade = {CascadeType.PERSIST}, mappedBy = "departement")
    @JsonIgnore // boucle infini error 500 dans Asign Etudiant
    Set<Etudiant> etudiants;







    @ManyToOne()// Association entre many Departement et one Universite
    Universite universite;
}
