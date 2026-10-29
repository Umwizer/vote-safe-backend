package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import rw.auca.vote_safe.models.ReturningOfficer;;
public interface  ReturningOfficerRepository extends JpaRepository<ReturningOfficer,UUID> {
    
}
