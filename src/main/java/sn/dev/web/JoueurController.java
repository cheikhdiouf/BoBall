package sn.dev.web;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sn.dev.apiClient.Post;
import sn.dev.dto.JoueurDto;
import sn.dev.entites.response.BaseResponse;
import sn.dev.entites.response.BaseResponseStatut;
import sn.dev.exception.BadRequestAlertException;
import sn.dev.filter.service.Criteria.JoueurCriteria;
import sn.dev.filter.service.JoueurQueryService;
import sn.dev.services.impl.JoueurServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JoueurController {
    private final Logger log = LoggerFactory.getLogger(JoueurController.class);

    private  final JoueurServiceImpl joueurService;
    private  final JoueurQueryService joueurQueryService;
    private  final RestClient restClient;

    public JoueurController(JoueurServiceImpl joueurService, JoueurQueryService joueurQueryService, RestClient.Builder restClient) {
        this.joueurService = joueurService;
        this.joueurQueryService = joueurQueryService;
        this.restClient = restClient
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
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

    @Operation(summary = "Liste des joueur")
    @GetMapping("/joueurs")
    public BaseResponse<List<JoueurDto>> getJoueur(
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


    @Operation(summary = "Liste des utilisateurs Api placeholder")
    @GetMapping("/users")
    public List<Post> getPosts() {
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>() {});

    }





}
