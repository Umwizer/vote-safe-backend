package rw.auca.vote_safe.models;

import jakarta.persistence.*;
import java.util.*;
@Entity 
public class Candidate {
   @Id 
   @GeneratedValue (strategy = GenerationType.UUID)
   private UUID id;
   private String firstName;
   private String lastName;
   @Column(unique = true)
   private String studentNumber;
   private String email;
   private String faculty;
   
   public Candidate() {
   }
   public Candidate(UUID id, String firstName, String lastName, String studentNumber, String email, String faculty) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.studentNumber = studentNumber;
    this.email = email;
    this.faculty = faculty;
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
   public String getStudentNumber() {
    return studentNumber;
   }
   public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
   }
   public String getEmail() {
    return email;
   }
   public void setEmail(String email) {
    this.email = email;
   }
   public String getFaculty() {
    return faculty;
   }
   public void setFaculty(String faculty) {
    this.faculty = faculty;
   }

}
