package sn.atos.ProjetJava17.dto;

import lombok.Builder;
import lombok.Data;
import sn.atos.ProjetJava17.enums.Gender;

public record PlayerDto(Long id, String playerName, String email, Boolean selected, Gender gender , Long countryDtoId) {

    public Long getCountryDtoId() {
        return countryDtoId;
    }


}
