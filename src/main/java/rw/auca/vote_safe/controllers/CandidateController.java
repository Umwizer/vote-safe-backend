package rw.auca.vote_safe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import rw.auca.vote_safe.models.Candidate;
import rw.auca.vote_safe.services.CandidateServices;
import rw.auca.vote_safe.services.ReturningOfficerServices;
@RestController
@RequestMapping("api/candidates") 
public class CandidateController {
    private final CandidateServices candidateServices;
    private final  ReturningOfficerServices returningOfficerServices;
    public  CandidateController(CandidateServices candidateServices,ReturningOfficerServices returningOfficerServices){
        this.candidateServices = candidateServices;
        this.returningOfficerServices=returningOfficerServices;
    }
    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody  Candidate c){
        return new ResponseEntity<>(candidateServices.saveCandidate(c),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Candidate>>all(){
        return ResponseEntity.ok(candidateServices.all());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable UUID id){
        Optional<Candidate>candidate =candidateServices.byId(id);
        if (candidate.isPresent()) {
            return new ResponseEntity<>(candidate.get(),HttpStatus.OK);
        }return new ResponseEntity<>("The candidate with that id does not exist",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/by-last-name")
    public ResponseEntity<List<Candidate>>byLastName(@RequestParam String lastName){
        return ResponseEntity.ok(candidateServices.findByLastName(lastName));
    }
    @GetMapping("/of-returning-officer/{returningOfficerId}")
    public ResponseEntity<?>byOfficer(@PathVariable UUID returningOfficerId){
        if (!returningOfficerServices.byId(returningOfficerId).isPresent()) {
            return new ResponseEntity<>("The returning officer with that id does not exist",HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(candidateServices.findByOfficer(returningOfficerId));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@PathVariable UUID id,@RequestBody Candidate c){
        String msg=candidateServices.updateCandidate(id, c);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(msg);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable  UUID id){
        String msg = candidateServices.deleteCandidate(id);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(msg);
    }
}
