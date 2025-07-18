package sn.atos.ProjetJava17.services;

import sn.atos.ProjetJava17.dto.CountryDto;
import sn.atos.ProjetJava17.dto.PlayerDto;

import java.util.List;

public interface PlayerService {


   List<PlayerDto> findAllPlayer();

    PlayerDto updatePlayer(PlayerDto playerDto);
  PlayerDto getPlayerById(Long id);
    void deleteCountry(Long id);


   PlayerDto savePlayer(PlayerDto playerDto);
}
