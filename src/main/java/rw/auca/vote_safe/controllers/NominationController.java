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
import org.springframework.web.bind.annotation.RestController;

import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.services.NominationServices;
import java.util.*;
@RestController 
@RequestMapping ("api/nominations")
public class NominationController{
  private final  NominationServices nominationServices;
  private NominationController(NominationServices nominationServices){
    this.nominationServices=nominationServices;
  }
  @PostMapping("/save")
  public ResponseEntity<String>save(@RequestBody Nomination n){
    return new ResponseEntity<>(nominationServices.saveNomination(n),HttpStatus.CREATED);
  }
  @GetMapping("/all")
  public ResponseEntity<List<Nomination>>all(){
    return ResponseEntity.ok(nominationServices.all());
  }
  @GetMapping("/{id}")
  public ResponseEntity<?>getById(@PathVariable  UUID id){
    Optional<Nomination>nomination = nominationServices.byId(id);
    if (nomination.isPresent()) {
        return new ResponseEntity<>(nomination.get(),HttpStatus.OK);
    }return new ResponseEntity<>("The nomination with that id does not exist",HttpStatus.NOT_FOUND);
  }
  @PutMapping("/update/{id}")
  public ResponseEntity<String>update(@PathVariable  UUID id, @RequestBody  Nomination n){
    String msg=nominationServices.updateNomination(id, n);
    if (msg.contains("does not exist")) {
        return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }return ResponseEntity.ok(msg);
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String>delete(@PathVariable UUID id){
    String msg = nominationServices.delete(id);
    if (msg.contains("does not exist")) {
        return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }return ResponseEntity.ok(msg);
  }
}