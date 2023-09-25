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
@Table(name = "Universite")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUniv")
    int idUniv;

    @Column(name = "nomUniv")
    @NotNull
    String nomUniv;




    @OneToMany(mappedBy = "universite")// Association entre one Universite et many Departement
    @JsonIgnore // boucle infini error 500 dans Asign Etudiant
    Set<Departement> Departements;
}
