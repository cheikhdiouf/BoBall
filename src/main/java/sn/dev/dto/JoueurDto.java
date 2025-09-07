package sn.dev.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JoueurDto {
    private Integer id;
    private String matricule;
    private String nom;
    private String prenom;
    private String telephone;
    private ZonedDateTime dateNaissance;
    private ZonedDateTime dateCreation;
}
