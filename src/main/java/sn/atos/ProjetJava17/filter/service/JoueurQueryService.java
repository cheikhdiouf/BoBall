package sn.atos.ProjetJava17.filter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.atos.ProjetJava17.dto.JoueurDto;
import sn.atos.ProjetJava17.entites.Joueur;
import sn.atos.ProjetJava17.entites.Joueur_;
import sn.atos.ProjetJava17.filter.service.Criteria.JoueurCriteria;
import sn.atos.ProjetJava17.mappers.JoueurMapper;
import sn.atos.ProjetJava17.repository.JoueurRepository;
import sn.atos.ProjetJava17.services.QueryService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class JoueurQueryService extends QueryService<Joueur> {

    private final Logger log = LoggerFactory.getLogger(JoueurQueryService.class);
    private final JoueurRepository joueurRepository;
    private final JoueurMapper joueurMapper;

    public JoueurQueryService(JoueurRepository joueurRepository, JoueurMapper joueurMapper) {
        this.joueurRepository = joueurRepository;
        this.joueurMapper = joueurMapper;
    }

    @Transactional(readOnly = true)
    public List<JoueurDto> findByCriteria(JoueurCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Joueur> specification = createSpecification(criteria);
        return joueurRepository.findAll(specification)
                .stream()
                .map(joueurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<JoueurDto> findByCriteria(JoueurCriteria criteria, Pageable pageable) {
        log.debug("find page : {} by criteria : {}", pageable, criteria);
        final Specification<Joueur> specification = createSpecification(criteria);
        return joueurRepository.findAll(specification, pageable)
                .map(joueurMapper::toDto);
    }

protected Specification<Joueur> createSpecification(JoueurCriteria criteria) {
    Specification<Joueur> specification = Specification.where(null);
    if (criteria != null) {
        if (criteria.getDistinct() != null) {
            specification = specification.and(distinct(criteria.getDistinct()));
        }
        if (criteria.getNom() != null) {
            specification = specification.and(buildStringSpecification(criteria.getNom(), Joueur_.nom));
        }
        if (criteria.getPrenom() != null) {
            specification = specification.and(buildStringSpecification(criteria.getPrenom(), Joueur_.prenom));
        }
    }
    return specification;
}



}
