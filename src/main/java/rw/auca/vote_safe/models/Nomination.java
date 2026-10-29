package rw.auca.vote_safe.models;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
@Entity 
public class Nomination {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate nominationDate;
   @Enumerated(EnumType.STRING)
   private NominationStatus status;
   private String manifesto;
   private int supporterSignatures;

   @ManyToOne 
   @JoinColumn(name = "candidate_id")
   private Candidate candidate;
   @ManyToOne 
   @JoinColumn (name ="returning_officer_id")
   private ReturningOfficer returningOfficer;
   
   public Nomination() {
}
   public Nomination(UUID id, LocalDate nominationDate, NominationStatus status, String manifesto, int supporterSignatures,
        Candidate candidate, ReturningOfficer returningOfficer) {
    this.id = id;
    this.nominationDate = nominationDate;
    this.status = status;
    this.manifesto = manifesto;
    this.supporterSignatures = supporterSignatures;
    this.candidate = candidate;
    this.returningOfficer = returningOfficer;
   }
   public UUID getId() {
    return id;
   }
   public void setId(UUID id) {
    this.id = id;
   }
   public LocalDate getNominationDate() {
    return nominationDate;
   }
   public void setNominationDate(LocalDate nominationDate) {
    this.nominationDate = nominationDate;
   }
   public NominationStatus getStatus() {
    return status;
   }
   public void setStatus(NominationStatus status) {
    this.status = status;
   }
   public String getManifesto() {
    return manifesto;
   }
   public void setManifesto(String manifesto) {
    this.manifesto = manifesto;
   }
   public int getSupporterSignatures() {
    return supporterSignatures;
   }
   public void setSupporterSignatures(int supporterSignatures) {
    this.supporterSignatures = supporterSignatures;
   }
   public Candidate getCandidate() {
    return candidate;
   }
   public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
   }
   public ReturningOfficer getReturningOfficer() {
    return returningOfficer;
   }
   public void setReturningOfficer(ReturningOfficer returningOfficer) {
    this.returningOfficer = returningOfficer;
   }
   
}
