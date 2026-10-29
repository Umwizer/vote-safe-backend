package rw.auca.vote_safe.models;

import jakarta.persistence.*;
import java.util.*;
@Entity 
public class ReturningOfficer {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne 
    @JoinColumn (name ="polling_station_id")
    private PollingStation pollingStation;
    @ManyToMany 
    @JoinTable (name="returning_officer_position",joinColumns =@JoinColumn (name="returning_officer_id"),
                 inverseJoinColumns = @JoinColumn (name = "position_id"))
    private List<Position> positions;
    
    public ReturningOfficer() {
    }
    public ReturningOfficer(UUID id, String firstName, String lastName, String email, PollingStation pollingStation,
            List<Position> positions) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pollingStation = pollingStation;
        this.positions = positions;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public PollingStation getPollingStation() {
        return pollingStation;
    }
    public void setPollingStation(PollingStation pollingStation) {
        this.pollingStation = pollingStation;
    }
    public List<Position> getPositions() {
        return positions;
    }
    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
    
}
