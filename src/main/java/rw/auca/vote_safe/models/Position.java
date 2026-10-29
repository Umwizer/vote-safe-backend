package rw.auca.vote_safe.models;

import jakarta.persistence.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity 
public class Position {
    @Id 
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;
    private  String name;
    @ManyToMany(mappedBy ="positions")
    @JsonIgnore 
    private List<ReturningOfficer> returningOfficers;
    
    public Position() {
    }
    public Position(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public UUID getId() {
        return id;
    }   
    public Position(List<ReturningOfficer> returningOfficers) {
        this.returningOfficers = returningOfficers;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ReturningOfficer> getReturningOfficers() {
        return returningOfficers;
    }
    public void setReturningOfficers(List<ReturningOfficer> returningOfficers) {
        this.returningOfficers = returningOfficers;
    }
    
}
