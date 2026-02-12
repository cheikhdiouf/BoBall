package sn.atos.ProjetJava17.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


import java.time.ZonedDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record DemandeDevisDto(
        Integer id,
        String name,
        String email,
        String phone,
        String tripType,
        Integer people,
        ZonedDateTime date,
        String pays,
        String chambre,
        String budget,
        String message
) {}
