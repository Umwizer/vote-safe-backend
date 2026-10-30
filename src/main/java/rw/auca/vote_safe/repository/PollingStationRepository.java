package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import rw.auca.vote_safe.models.PollingStation;
public interface PollingStationRepository extends JpaRepository<PollingStation, UUID> {
    
    @Query("SELECT ps FROM PollingStation ps WHERE NOT EXISTS (SELECT ro FROM ReturningOfficer ro WHERE ro.pollingStation = ps) ORDR BY ps.stationNumber")
    List<PollingStation>findFreePollingStations();
    Optional<PollingStation>findByStationNumber(int stationNumber);
}
