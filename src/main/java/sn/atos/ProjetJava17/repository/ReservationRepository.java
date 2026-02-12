package sn.atos.ProjetJava17.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sn.atos.ProjetJava17.entites.Reservation;

@Repository
public interface ReservationRepository
        extends JpaRepository<Reservation, Integer>,
        JpaSpecificationExecutor<Reservation> {
}
