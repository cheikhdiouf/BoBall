package sn.atos.ProjetJava17.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import sn.atos.ProjetJava17.dto.ReservationDto;
import sn.atos.ProjetJava17.entites.response.BaseResponse;
import sn.atos.ProjetJava17.entites.response.BaseResponseStatut;
import sn.atos.ProjetJava17.exception.BadRequestAlertException;
import sn.atos.ProjetJava17.filter.service.Criteria.ReservationCriteria;
import sn.atos.ProjetJava17.filter.service.ReservationQueryService;
import sn.atos.ProjetJava17.services.impl.ReservationServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ReservationController {

    private final Logger log = LoggerFactory.getLogger(ReservationController.class);

    private final ReservationServiceImpl reservationService;
    private final ReservationQueryService reservationQueryService;

    public ReservationController(
            ReservationServiceImpl reservationService,
            ReservationQueryService reservationQueryService
    ) {
        this.reservationService = reservationService;
        this.reservationQueryService = reservationQueryService;
    }

    @Operation(summary = "Ajouter une réservation")
    @PostMapping("/reservations")
    public BaseResponse<ReservationDto> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        log.debug("REST request to save Reservation : {}", reservationDto);
        if (reservationDto.id() != null) {
            throw new BadRequestAlertException(
                    "Une nouvelle réservation ne peut pas avoir un ID déjà défini", 1113
            );
        }
        ReservationDto result = reservationService.save(reservationDto);

        return new BaseResponse<ReservationDto>()
                .statut(BaseResponseStatut.SUCCESS)
                .codeRetour(0)
                .content("reservation", result);
    }

    @GetMapping("/reservations")
    public BaseResponse<List<ReservationDto>> getAllReservations(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable,
            @ParameterObject ReservationCriteria criteria
    ) {
        log.debug("REST request to get all reservations : {}", pageable);
        Page<ReservationDto> page = reservationQueryService.findByCriteria(criteria, pageable);

        return new BaseResponse<List<ReservationDto>>()
                .statut(BaseResponseStatut.SUCCESS)
                .codeRetour(0)
                .totalItems(page.getTotalElements())
                .content("reservations", page.getContent());
    }
}
