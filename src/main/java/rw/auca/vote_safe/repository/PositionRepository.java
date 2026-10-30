package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;
import rw.auca.vote_safe.models.Position;
public interface  PositionRepository extends JpaRepository<Position, UUID> {
    
    @Query("SELECT p FROM Position p WHERE p.returningOfficers IS EMPTY")
    List<Position>findUnusedPositions();
}
