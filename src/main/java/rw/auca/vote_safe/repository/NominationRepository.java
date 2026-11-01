package rw.auca.vote_safe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.models.NominationStatus;
public interface NominationRepository extends JpaRepository<Nomination, UUID>{
    List<Nomination>findByStatusOrderByNominationDateAsc(NominationStatus status);
    List<Nomination>findByNominationDateBetweenOrderByNominationDateAsc(LocalDate start ,LocalDate end);
    boolean existsByReturningOfficerIdAndNominationDateAndStatusNot(UUID officerId,LocalDate date,NominationStatus status);
    @Query("SELECT n.status,COUNT(n) FROM Nomination n GROUP BY n.status")
    List <Object[]> getNominationStasByStatus();
    @Modifying 
    @Transactional 
    @Query("UPDATE Nomination n SET n.status = NominationStatus.DISQUALIFIED WHERE n.returningOfficer.id = :OfficerId AND n.nominationDate = :date AND n.status <> NominationStatus.APPROVED")
     int cancelNominationForDay(@Param("officerId") UUID officerId,@Param("date") LocalDate date);
    long countByNominationDate(LocalDate date);
    long countByStatus(NominationStatus status);
}
