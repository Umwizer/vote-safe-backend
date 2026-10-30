package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
import rw.auca.vote_safe.models.ReturningOfficer;;
public interface  ReturningOfficerRepository extends JpaRepository<ReturningOfficer,UUID> {
    
    @Query("SELECT DISCTINCT ro FROM ReturningOfficer ro JOIN ro.positions p WHERE LOWER(p.name) = LOWER(:name)")
    List<ReturningOfficer>findOfficersByPositionName(@Param ("name") String name);
}
