package sn.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sn.dev.entites.Joueur;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Integer> , JpaSpecificationExecutor<Joueur> {
}
