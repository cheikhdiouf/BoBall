package sn.atos.ProjetJava17.filter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.atos.ProjetJava17.dto.DemandeDevisDto;
import sn.atos.ProjetJava17.entites.DemandeDevis;
import sn.atos.ProjetJava17.entites.DemandeDevis_;
import sn.atos.ProjetJava17.filter.service.Criteria.DemandeDevisCriteria;
import sn.atos.ProjetJava17.mappers.DemandeDevisMapper;
import sn.atos.ProjetJava17.repository.DemandeDevisRepository;
import sn.atos.ProjetJava17.services.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DemandeDevisQueryService extends QueryService<DemandeDevis> {

    private final Logger log = LoggerFactory.getLogger(DemandeDevisQueryService.class);
    private final DemandeDevisRepository demandeDevisRepository;
    private final DemandeDevisMapper demandeDevisMapper;

    public DemandeDevisQueryService(DemandeDevisRepository demandeDevisRepository,
                                    DemandeDevisMapper demandeDevisMapper) {
        this.demandeDevisRepository = demandeDevisRepository;
        this.demandeDevisMapper = demandeDevisMapper;
    }

    @Transactional(readOnly = true)
    public List<DemandeDevisDto> findByCriteria(DemandeDevisCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DemandeDevis> specification = createSpecification(criteria);
        return demandeDevisRepository.findAll(specification)
                .stream()
                .map(demandeDevisMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<DemandeDevisDto> findByCriteria(DemandeDevisCriteria criteria, Pageable pageable) {
        log.debug("find page : {} by criteria : {}", pageable, criteria);
        final Specification<DemandeDevis> specification = createSpecification(criteria);
        return demandeDevisRepository.findAll(specification, pageable)
                .map(demandeDevisMapper::toDto);
    }

    protected Specification<DemandeDevis> createSpecification(DemandeDevisCriteria criteria) {
        Specification<DemandeDevis> specification = Specification.where(null);

        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.name() != null) {
                specification = specification.and(buildStringSpecification(criteria.name(), DemandeDevis_.name));
            }
            if (criteria.email() != null) {
                specification = specification.and(buildStringSpecification(criteria.email(), DemandeDevis_.email));
            }
            if (criteria.phone() != null) {
                specification = specification.and(buildStringSpecification(criteria.phone(), DemandeDevis_.phone));
            }
            if (criteria.tripType() != null) {
                specification = specification.and(buildStringSpecification(criteria.tripType(), DemandeDevis_.tripType));
            }
            if (criteria.pays() != null) {
                specification = specification.and(buildStringSpecification(criteria.pays(), DemandeDevis_.pays));
            }
            if (criteria.chambre() != null) {
                specification = specification.and(buildStringSpecification(criteria.chambre(), DemandeDevis_.chambre));
            }
            if (criteria.budget() != null) {
                specification = specification.and(buildStringSpecification(criteria.budget(), DemandeDevis_.budget));
            }
            if (criteria.message() != null) {
                specification = specification.and(buildStringSpecification(criteria.message(), DemandeDevis_.message));
            }
        }

        return specification;
    }
}
