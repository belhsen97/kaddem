package tn.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DetailEquipe")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetailEquipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetailEquipe")
    int idDetailEquipe;

    @Column(name = "salle")
    @NotNull
    int salle;

    @Column(name = "thematique")
    @NotNull
    String thematique;

    //@OneToOne(cascade = {cascadetype.persist ,cascadetype. remove })
    @OneToOne(mappedBy="detailEquipe")
    @JsonIgnore
    Equipe equipe;
}
