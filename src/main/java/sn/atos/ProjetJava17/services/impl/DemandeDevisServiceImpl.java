package sn.atos.ProjetJava17.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.atos.ProjetJava17.dto.DemandeDevisDto;
import sn.atos.ProjetJava17.entites.DemandeDevis;
import sn.atos.ProjetJava17.mappers.DemandeDevisMapper;
import sn.atos.ProjetJava17.repository.DemandeDevisRepository;
import sn.atos.ProjetJava17.services.DemandeDevisService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandeDevisServiceImpl implements DemandeDevisService {

    private final Logger log = LoggerFactory.getLogger(DemandeDevisServiceImpl.class);

    private final DemandeDevisRepository demandeDevisRepository;
    private final DemandeDevisMapper demandeDevisMapper;

    public DemandeDevisServiceImpl(
            DemandeDevisRepository demandeDevisRepository,
            DemandeDevisMapper demandeDevisMapper
    ) {
        this.demandeDevisRepository = demandeDevisRepository;
        this.demandeDevisMapper = demandeDevisMapper;
    }

    @Override
    public Optional<DemandeDevisDto> findById(Integer id) {
        log.debug("Find DemandeDevis by id : {}", id);
        return demandeDevisRepository
                .findById(id)
                .map(demandeDevisMapper::toDto);
    }

    @Override
    public List<DemandeDevisDto> findAll() {
        log.debug("Find all DemandeDevis");
        return demandeDevisRepository.findAll()
                .stream()
                .map(demandeDevisMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DemandeDevisDto save(DemandeDevisDto dto) {
        log.debug("Save DemandeDevis : {}", dto);
        DemandeDevis entity = demandeDevisMapper.toEntity(dto);
        entity = demandeDevisRepository.save(entity);
        return demandeDevisMapper.toDto(entity);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Delete DemandeDevis by id : {}", id);
        demandeDevisRepository.deleteById(id);
    }
}
