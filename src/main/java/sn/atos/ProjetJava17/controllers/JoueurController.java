package sn.atos.ProjetJava17.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.atos.ProjetJava17.services.impl.JoueurServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class JoueurController {
    private final Logger log = LoggerFactory.getLogger(JoueurController.class);

    private  final JoueurServiceImpl joueurService;

    public JoueurController(JoueurServiceImpl joueurService) {
        this.joueurService = joueurService;
    }
}
