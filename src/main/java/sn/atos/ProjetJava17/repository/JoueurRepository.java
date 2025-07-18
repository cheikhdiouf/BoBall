package sn.atos.ProjetJava17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.atos.ProjetJava17.entites.Joueur;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Integer> {
}
