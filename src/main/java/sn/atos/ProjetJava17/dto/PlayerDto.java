package sn.atos.ProjetJava17.dto;

import sn.atos.ProjetJava17.entites.enums.Gender;

public record PlayerDto(Long id, String playerName, String email, Boolean selected, Gender gender , Long countryDtoId) {

    public Long getCountryDtoId() {
        return countryDtoId;
    }


}
