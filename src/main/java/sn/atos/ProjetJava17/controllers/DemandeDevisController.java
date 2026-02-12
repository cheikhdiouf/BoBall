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
import sn.atos.ProjetJava17.dto.DemandeDevisDto;
import sn.atos.ProjetJava17.entites.response.BaseResponse;
import sn.atos.ProjetJava17.entites.response.BaseResponseStatut;
import sn.atos.ProjetJava17.exception.BadRequestAlertException;
import sn.atos.ProjetJava17.filter.service.Criteria.DemandeDevisCriteria;
import sn.atos.ProjetJava17.filter.service.DemandeDevisQueryService;
import sn.atos.ProjetJava17.services.impl.DemandeDevisServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"http://localhost:3000"})
public class DemandeDevisController {
    private final Logger log = LoggerFactory.getLogger(DemandeDevisController.class);

    private  final DemandeDevisServiceImpl demandeDevisService;
    private  final DemandeDevisQueryService demandeDevisQueryService;

    public DemandeDevisController(DemandeDevisServiceImpl demandeDevisService, DemandeDevisQueryService demandeDevisQueryService) {
        this.demandeDevisService = demandeDevisService;
        this.demandeDevisQueryService = demandeDevisQueryService;
    }
    @Operation(summary = "Ajouter un demande devis ")
    @PostMapping("/demandeDevis")
    public BaseResponse<DemandeDevisDto> createDemandeDevis(@Valid @RequestBody  DemandeDevisDto demandeDevisDto) {
        log.debug("REST request to save DemandeDevis : {}", demandeDevisDto);
        if (demandeDevisDto.id() != null) {
            throw new BadRequestAlertException("un nouveau demandeDevis ne peut pas avoir un ID identifiant déjà défini", 1112);
        }
        DemandeDevisDto result = null;
        result = demandeDevisService.save(demandeDevisDto);

        return new BaseResponse<DemandeDevisDto>()
                .statut(BaseResponseStatut.SUCCESS)
                .codeRetour(0)
                .content("demandeDevis", result);
    }

    @GetMapping("/demandeDevis")
    public BaseResponse<List<DemandeDevisDto>> getAllTransactions(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable,
            @ParameterObject DemandeDevisCriteria criteria
    ) {
        log.debug("REST Request to get all transactions : {}", pageable);
        Page<DemandeDevisDto> page = demandeDevisQueryService.findByCriteria(criteria, pageable);
        return new BaseResponse<List<DemandeDevisDto>>()
                .statut(BaseResponseStatut.SUCCESS)
                .codeRetour(0)
                .totalItems(page.getTotalElements())
                .content("transactions", page.getContent());
    }


}
