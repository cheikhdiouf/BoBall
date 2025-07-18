package sn.atos.ProjetJava17.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.atos.ProjetJava17.enums.Gender;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="MODELIS_PLAYER" )
public class PlayerEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private  String playerName;

     private String email;
     private Boolean selected;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_COUNTRY")
    private CountryEntity countryEntity;


}
