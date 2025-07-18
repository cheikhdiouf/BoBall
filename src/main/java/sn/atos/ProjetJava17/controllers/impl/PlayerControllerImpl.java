package sn.atos.ProjetJava17.controllers.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import sn.atos.ProjetJava17.controllers.CountryController;
import sn.atos.ProjetJava17.controllers.PlayerController;
import sn.atos.ProjetJava17.dto.CountryDto;
import sn.atos.ProjetJava17.dto.PlayerDto;
import sn.atos.ProjetJava17.services.CountryService;
import sn.atos.ProjetJava17.services.PlayerService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class PlayerControllerImpl implements PlayerController {
@Autowired
    PlayerService playerService;
    @Override
    public ResponseEntity<PlayerDto> SavePlayer(PlayerDto playerDto) {
       /* try {
            log.debug("REST request to create a Country object : {} ", playerDto);
            PlayerDto savedCountry =playerService.savePlayer(playerDto);
            return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log ou gestion d'erreur approprié ici
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/
        log.debug("REST request to create a ElementFactureDTO object : {} ", playerDto);
        PlayerDto aElementFactureDTO = playerService.savePlayer(playerDto);
        return new ResponseEntity<>(aElementFactureDTO, HttpStatus.CREATED);
    }
}
