package sn.atos.ProjetJava17.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "demande_devis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDevis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private Integer id;

    @Column(name = "dev_nom")
    private String name;

    @Column(name = "dev_email")
    private String email;

    @Column(name = "dev_telephone",nullable = false)
    private String phone;

    @Column(name = "dev_type_voyage")
    private String tripType;

    @Column(name = "dev_nombre_personnes")
    private Integer people;

    @Column(name = "dev_date_voyage")
    private ZonedDateTime date;

    @Column(name = "dev_pays")
    private String pays;

    @Column(name = "dev_type_chambre")
    private String chambre;

    @Column(name = "dev_budget")
    private String budget;

    @Column(name = "dev_message", length = 1000)
    private String message;

}
