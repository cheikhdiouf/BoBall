package sn.atos.ProjetJava17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sn.atos.ProjetJava17.entites.DemandeDevis;

@Repository
public interface DemandeDevisRepository extends JpaRepository<DemandeDevis,Integer> , JpaSpecificationExecutor<DemandeDevis> {
}
