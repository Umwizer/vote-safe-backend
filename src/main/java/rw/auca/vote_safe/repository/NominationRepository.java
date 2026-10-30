package rw.auca.vote_safe.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.models.NominationStatus;
public interface NominationRepository extends JpaRepository<Nomination, UUID>{
    List<Nomination>findByStatusOrderByNominationDateAsc(NominationStatus status);
    List<Nomination>findByNominationDateBetweenOrderByNominationDateAsc(LocalDate start ,LocalDate end);
    boolean existsByReturningOfficerIdAndNominationDateAndStatusNot(UUID officerId,LocalDate date,NominationStatus status);
}
