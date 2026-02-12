package sn.atos.ProjetJava17.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.atos.ProjetJava17.dto.ReservationDto;
import sn.atos.ProjetJava17.entites.Reservation;
import sn.atos.ProjetJava17.mappers.ReservationMapper;
import sn.atos.ProjetJava17.repository.ReservationRepository;
import sn.atos.ProjetJava17.services.EmailService;
import sn.atos.ProjetJava17.services.ReservationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final EmailService emailService;

    public ReservationServiceImpl(
            ReservationRepository reservationRepository,
            ReservationMapper reservationMapper, EmailService emailService
    ) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.emailService = emailService;
    }

    @Override
    public Optional<ReservationDto> findById(Integer id) {
        log.debug("Find Reservation by id : {}", id);
        return reservationRepository
                .findById(id)
                .map(reservationMapper::toDto);
    }

    @Override
    public List<ReservationDto> findAll() {
        log.debug("Find all Reservations");
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        log.debug("Delete Reservation by id : {}", id);
        reservationRepository.deleteById(id);
    }

    @Override
    public ReservationDto save(ReservationDto dto) {

        log.debug("Save Reservation : {}", dto);

        // 1️⃣ DTO → Entity
        Reservation entity = reservationMapper.toEntity(dto);

        // 2️⃣ Save en base
        entity = reservationRepository.save(entity);

        // 3️⃣ Envoi email
        try {
            emailService.sendReservationNotification(entity);
        } catch (Exception e) {
            log.error("Erreur lors de l'envoi du mail : ", e);
        }

        // 4️⃣ Entity → DTO
        return reservationMapper.toDto(entity);
    }
}
