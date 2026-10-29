package rw.auca.vote_safe.models;

import jakarta.persistence.*;
import java.util.*;
@Entity 
public class PollingStation {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private int stationNumber;
    private String name;
    
    public PollingStation() {
    }
    public PollingStation(UUID id, int stationNumber, String name) {
        this.id = id;
        this.stationNumber = stationNumber;
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public int getStationNumber() {
        return stationNumber;
    }
    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
