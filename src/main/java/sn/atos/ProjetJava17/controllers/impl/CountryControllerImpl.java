package sn.atos.ProjetJava17.controllers.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import sn.atos.ProjetJava17.controllers.CountryController;

import sn.atos.ProjetJava17.dto.CountryDto;
import sn.atos.ProjetJava17.services.CountryService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class CountryControllerImpl implements CountryController {
    @Autowired
    CountryService countryService;

    @Override
    public ResponseEntity<List<CountryDto>> findAllCountry() {
        //log.debug("findAll() - REST request to get ALL Personnel");

        return ResponseEntity.ok(countryService.findAllCountry());
    }

    @Override
    public ResponseEntity<CountryDto> SaveCountry(CountryDto countryDto) {
        try {
            log.debug("REST request to create a Country object : {} ", countryDto);
            CountryDto savedCountry = countryService.saveCountry(countryDto);
            return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log ou gestion d'erreur approprié ici
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<CountryDto> updateCountry(CountryDto countryDto) {
        CountryDto countryDTO = countryService.updateContry(countryDto);
        return new ResponseEntity<>(countryDTO, HttpStatus.OK);
    }

    @Override
    public void deleteCountry(Long id) {
        countryService.deleteCountry(id);
    }

    @Override
    public ResponseEntity<CountryDto> getCountryById(Long id) {
        CountryDto countryDto = countryService.getCountryById(id);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }
}
