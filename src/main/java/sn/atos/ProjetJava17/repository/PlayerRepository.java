package sn.atos.ProjetJava17.repository;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.atos.ProjetJava17.entites.CountryEntity;
import sn.atos.ProjetJava17.entites.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {

}
