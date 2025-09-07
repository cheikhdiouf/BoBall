package sn.dev.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "joueur")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="jou_id")
    private Integer id;
    @Column(name="jou_matricule" ,unique = true,nullable = false)
    private String matricule;
    @Column(name="jou_nom")
    private String nom;
    @Column(name = "jou_prenom")
    private String prenom;
    @Column(name = "jou_telephone")
    private String telephone;
    @Column (name="date_naissance")
    private ZonedDateTime dateNaissance;
    @Column (name="date_creation")
    private ZonedDateTime dateCreation;
}
