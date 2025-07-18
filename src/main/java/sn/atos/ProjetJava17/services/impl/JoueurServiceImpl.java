package sn.atos.ProjetJava17.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.atos.ProjetJava17.dto.JoueurDto;
import sn.atos.ProjetJava17.entites.Joueur;
import sn.atos.ProjetJava17.mappers.JoueurMapper;
import sn.atos.ProjetJava17.repository.JoueurRepository;
import sn.atos.ProjetJava17.services.JoueurService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JoueurServiceImpl implements JoueurService {
    private final Logger log = LoggerFactory.getLogger(JoueurServiceImpl.class);
    private final JoueurMapper joueurMapper;
    private  final JoueurRepository joueurRepository;

    public JoueurServiceImpl(JoueurMapper joueurMapper, JoueurRepository joueurRepository) {
        this.joueurMapper = joueurMapper;
        this.joueurRepository = joueurRepository;
    }

    @Override
    public Optional<JoueurDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<JoueurDto> findAll() {
        return List.of();
    }

    @Override
    public JoueurDto save(JoueurDto joueurDto) {
        this.log.debug("Save joueur: {}", joueurDto);
        Joueur joueur= joueurRepository.save(joueurMapper.toEntity(joueurDto));
        return joueurMapper.toDto(joueur);
    }

    @Override
    public void delete(Integer id) {

    }
}
