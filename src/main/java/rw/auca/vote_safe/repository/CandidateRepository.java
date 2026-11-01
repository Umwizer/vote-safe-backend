package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
import  rw.auca.vote_safe.models.Candidate;
public interface  CandidateRepository extends JpaRepository<Candidate, UUID> {
    List<Candidate>findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    @Query("SELECT DISTINCT c FROM Nomination n JOIN n.candidate c WHERE n.returningOfficer.id = :id")
    List<Candidate>findCandidatesByReturningOfficer(@Param("id") UUID id);
}
