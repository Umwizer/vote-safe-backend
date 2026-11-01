package rw.auca.vote_safe.models;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDate;
@Entity
public class CampaignActivity {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String activityType;
    private String venue;
    private int attendes;
    private LocalDate heldDate;
    
    @ManyToOne 
    @JoinColumn (name = "nomination_id")
    private Nomination nomination;

    public CampaignActivity() {
    }

    public CampaignActivity(UUID id, String activityType, String venue, int attendes, LocalDate heldDate,
            Nomination nomination) {
        this.id = id;
        this.activityType = activityType;
        this.venue = venue;
        this.attendes = attendes;
        this.heldDate = heldDate;
        this.nomination = nomination;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getAttendes() {
        return attendes;
    }

    public void setAttendes(int attendes) {
        this.attendes = attendes;
    }

    public LocalDate getHeldDate() {
        return heldDate;
    }

    public void setHeldDate(LocalDate heldDate) {
        this.heldDate = heldDate;
    }

    public Nomination getNomination() {
        return nomination;
    }

    public void setNomination(Nomination nomination) {
        this.nomination = nomination;
    }
    
}
