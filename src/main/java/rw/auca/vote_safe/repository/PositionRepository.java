package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import rw.auca.vote_safe.models.Position;
public interface  PositionRepository extends JpaRepository<Position, UUID> {
    
}
