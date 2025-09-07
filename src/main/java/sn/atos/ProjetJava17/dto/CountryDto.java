package sn.atos.ProjetJava17.dto;

import sn.atos.ProjetJava17.entites.PlayerEntity;

import java.util.List;

public record CountryDto(Long id, String countryName, List<PlayerEntity> playerEntityList) {
}
