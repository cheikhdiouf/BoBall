package sn.atos.ProjetJava17.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.atos.ProjetJava17.dto.CountryDto;
import sn.atos.ProjetJava17.entites.CountryEntity;
import sn.atos.ProjetJava17.mappers.CountryMapper;
import sn.atos.ProjetJava17.repository.CountryRepository;
import sn.atos.ProjetJava17.services.CountryService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j

@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CountryRepository countryRepository;



    @Override
    public List<CountryDto> findAllCountry() {
        return countryRepository.findAll().stream()
                .map(countryMapper::fromEntity).collect(Collectors.toList());

    }

    @Override
    public CountryDto saveCountry(CountryDto countryDto) {
        try {

    // Convertir le DTO en entité
    CountryEntity countryEntity = countryMapper.toEntity(countryDto);

    // Enregistrer l'entité dans le repository
    countryEntity = countryRepository.save(countryEntity);

    // Retourner le DTO résultant
    return countryMapper.fromEntity(countryEntity);
}
catch (Exception e) {
    // Gérer les exceptions ici (log, renvoyer une erreur personnalisée, etc.)
    // Vous pouvez également renvoyer une réponse d'erreur, par exemple :
    throw new RuntimeException("Erreur lors de l'enregistrement du pays", e);
}


    }

    @Override
    public CountryDto  updateContry(CountryDto countryDto) {
        CountryDto updateFormateur = getCountryById(countryDto.id());
        if (updateFormateur == null) {
            throw new EntityNotFoundException("Cette formateur n'existe pas");
        }
        return countryMapper.fromEntity(
                countryRepository.save(countryMapper.toEntity(countryDto))
        );
    }

    @Override
    public CountryDto getCountryById(Long id) {
        CountryEntity countryEntity=countryRepository.findById(id).get();
        return countryMapper.fromEntity(countryEntity);
    }

    @Override
    public void deleteCountry(Long id) {
        if (id == null) {
            log.error("country id ne peut pas etre null");

        }
         else {
            countryRepository.deleteById(id);
        }

    }


}
