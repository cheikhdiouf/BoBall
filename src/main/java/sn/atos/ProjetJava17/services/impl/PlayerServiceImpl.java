package sn.atos.ProjetJava17.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.atos.ProjetJava17.dto.CountryDto;
import sn.atos.ProjetJava17.dto.PlayerDto;
import sn.atos.ProjetJava17.entites.CountryEntity;
import sn.atos.ProjetJava17.entites.PlayerEntity;
import sn.atos.ProjetJava17.mappers.CountryMapper;
import sn.atos.ProjetJava17.mappers.PlayerMapper;
import sn.atos.ProjetJava17.repository.CountryRepository;
import sn.atos.ProjetJava17.repository.PlayerRepository;
import sn.atos.ProjetJava17.services.CountryService;
import sn.atos.ProjetJava17.services.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    CountryService countryService;
    @Autowired
    CountryMapper countryMapper;
@Autowired
CountryRepository countryRepository;

    @Override
    public List<PlayerDto> findAllPlayer() {
        return  null;
    }

    @Override
    public PlayerDto updatePlayer(PlayerDto playerDto) {
        return null;
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        return null;
    }

    @Override
    public void deleteCountry(Long id) {

    }

    @Override
    public PlayerDto savePlayer(PlayerDto playerDto) {
        if (playerDto != null) {

            PlayerEntity operationFactureEntity = playerMapper.toEntity(playerDto);
            Long countryId = playerDto.getCountryDtoId();
            if (countryId != null) {
                CountryDto elementFactureDTO = countryService.getCountryById(countryId);
                if (elementFactureDTO == null) {
                    log.error("save() - Failed to create ElementFactureEntity. ElementFacture with id {} not found", countryId);
                    return null;
                }
                operationFactureEntity.setCountryEntity(countryMapper.toEntity(elementFactureDTO));

            }
            PlayerEntity createdOperationFactureEntity = playerRepository.save(operationFactureEntity);
            log.info("save() - Classe with id {} created successfully", createdOperationFactureEntity.getId());

            return playerMapper.fromEntity(createdOperationFactureEntity);
        }
        log.error("save() - Failed to create OperationFacture. OperationFacture is null");
        return null;
    }
}
