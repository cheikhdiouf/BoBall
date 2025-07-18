package sn.atos.ProjetJava17.services;

import sn.atos.ProjetJava17.dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<CountryDto> findAllCountry();

    CountryDto saveCountry(CountryDto countryDto);

    CountryDto updateContry(CountryDto countryDto);
  CountryDto getCountryById(Long id);
    void deleteCountry(Long id);
}
