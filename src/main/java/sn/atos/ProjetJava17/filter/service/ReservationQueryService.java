package sn.atos.ProjetJava17.filter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.atos.ProjetJava17.dto.ReservationDto;
import sn.atos.ProjetJava17.entites.Reservation;
import sn.atos.ProjetJava17.entites.Reservation_;
import sn.atos.ProjetJava17.filter.service.Criteria.ReservationCriteria;
import sn.atos.ProjetJava17.mappers.ReservationMapper;
import sn.atos.ProjetJava17.repository.ReservationRepository;
import sn.atos.ProjetJava17.services.QueryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ReservationQueryService extends QueryService<Reservation> {

    private final Logger log = LoggerFactory.getLogger(ReservationQueryService.class);
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationQueryService(ReservationRepository reservationRepository,
                                   ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Transactional(readOnly = true)
    public List<ReservationDto> findByCriteria(ReservationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Reservation> specification = createSpecification(criteria);
        return reservationRepository.findAll(specification)
                .stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ReservationDto> findByCriteria(ReservationCriteria criteria, Pageable pageable) {
        log.debug("find page : {} by criteria : {}", pageable, criteria);
        final Specification<Reservation> specification = createSpecification(criteria);
        return reservationRepository.findAll(specification, pageable)
                .map(reservationMapper::toDto);
    }

    protected Specification<Reservation> createSpecification(ReservationCriteria criteria) {
        Specification<Reservation> specification = Specification.where(null);

        if (criteria != null) {
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            // Infos personnelles
            if (criteria.firstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.firstName(), Reservation_.firstName));
            }
            if (criteria.lastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.lastName(), Reservation_.lastName));
            }
            if (criteria.email() != null) {
                specification = specification.and(buildStringSpecification(criteria.email(), Reservation_.email));
            }
            if (criteria.phone() != null) {
                specification = specification.and(buildStringSpecification(criteria.phone(), Reservation_.phone));
            }
            if (criteria.passportNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.passportNumber(), Reservation_.passportNumber));
            }

            // Choix du voyage
            if (criteria.packageType() != null) {
                specification = specification.and(buildStringSpecification(criteria.packageType(), Reservation_.packageType));
            }
            if (criteria.roomType() != null) {
                specification = specification.and(buildStringSpecification(criteria.roomType(), Reservation_.roomType));
            }
            if (criteria.paymentMethod() != null) {
                specification = specification.and(buildStringSpecification(criteria.paymentMethod(), Reservation_.paymentMethod));
            }

            // Paiement
            if (criteria.mobileMoneyProvider() != null) {
                specification = specification.and(buildStringSpecification(criteria.mobileMoneyProvider(), Reservation_.mobileMoneyProvider));
            }
            if (criteria.mobileMoneyNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.mobileMoneyNumber(), Reservation_.mobileMoneyNumber));
            }
            if (criteria.bankName() != null) {
                specification = specification.and(buildStringSpecification(criteria.bankName(), Reservation_.bankName));
            }

            // Infos supplémentaires
            if (criteria.specialRequests() != null) {
                specification = specification.and(buildStringSpecification(criteria.specialRequests(), Reservation_.specialRequests));
            }
        }

        return specification;
    }
}
