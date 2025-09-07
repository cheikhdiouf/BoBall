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
import sn.atos.ProjetJava17.dto.JoueurDto;
import sn.atos.ProjetJava17.entites.Joueur;
import sn.atos.ProjetJava17.entites.response.BaseResponse;
import sn.atos.ProjetJava17.entites.response.BaseResponseStatut;
import sn.atos.ProjetJava17.exception.BadRequestAlertException;
import sn.atos.ProjetJava17.filter.service.Criteria.JoueurCriteria;
import sn.atos.ProjetJava17.filter.service.JoueurQueryService;
import sn.atos.ProjetJava17.services.impl.JoueurServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JoueurController {
    private final Logger log = LoggerFactory.getLogger(JoueurController.class);

    private  final JoueurServiceImpl joueurService;
    private  final JoueurQueryService joueurQueryService;

    public JoueurController(JoueurServiceImpl joueurService, JoueurQueryService joueurQueryService) {
        this.joueurService = joueurService;
        this.joueurQueryService = joueurQueryService;
    }
    @Operation(summary = "Ajouter un jouer")
    @PostMapping("/joueurs")
    public BaseResponse<JoueurDto> createJoueur(@Valid @RequestBody  JoueurDto joueurDto) {
        log.debug("REST request to save Joueur : {}", joueurDto);
        if (joueurDto.getId() != null) {
            throw new BadRequestAlertException("un nouveau joueur ne peut pas avoir un ID identifiant déjà défini", 1112);
        }
        JoueurDto result = null;
        result = joueurService.save(joueurDto);

        return new BaseResponse<JoueurDto>()
                .statut(BaseResponseStatut.SUCCESS)
                .codeRetour(0)
                .content("joueur", result);
    }
    @GetMapping("/transactions")
    public BaseResponse<List<JoueurDto>> getAllTransactions(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable,
            @ParameterObject JoueurCriteria criteria
    ) {
        log.debug("REST Request to get all transactions : {}", pageable);
        Page<JoueurDto> page = joueurQueryService.findByCriteria(criteria, pageable);
        return new BaseResponse<List<JoueurDto>>()
                .statut(BaseResponseStatut.SUCCESS)
                .codeRetour(0)
                .totalItems(page.getTotalElements())
                .content("transactions", page.getContent());
    }


}
