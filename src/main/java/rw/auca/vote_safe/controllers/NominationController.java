package rw.auca.vote_safe.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.models.NominationStatus;
import rw.auca.vote_safe.services.NominationServices;
import java.util.*;
import java.time.LocalDate;
@RestController 
@RequestMapping ("api/nominations")
public class NominationController{
  private final  NominationServices nominationServices;
  private NominationController(NominationServices nominationServices){
    this.nominationServices=nominationServices;
  }
  @PostMapping("/save")
  public ResponseEntity<String>save(@RequestBody Nomination n){
    String msg = nominationServices.saveNomination(n);
    if (msg.equals("Nomination date cannot be in the past")) {
        return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }if (msg.equals("Returning officer already vets a nomination on that date")) {
        return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>(msg,HttpStatus.CREATED);
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
  @GetMapping("/by-status")
  public ResponseEntity<List<Nomination>>byStatus(@RequestParam NominationStatus status){
    return ResponseEntity.ok(nominationServices.findByStatus(status));
  }
  @GetMapping("/between")
  public ResponseEntity<List<Nomination>>between(@RequestParam String start , @RequestParam  String end){
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    return ResponseEntity.ok(nominationServices.betweenDates(startDate, endDate));

  }
  @GetMapping("/stats/by-status")
  public ResponseEntity<List<Object[]>>getStats(){
    return ResponseEntity.ok(nominationServices.getStatsByStatus());
  }
  @GetMapping(value="/page",params = "page")
  public ResponseEntity<Page<Nomination>>getPage(@RequestParam int page,@RequestParam int size,@RequestParam String sort){
    String[] sortParts = sort.split(",");
    String field = sortParts[0];
    Sort.Direction direction = Sort.Direction.ASC;
    if (sortParts.length > 1) {
      direction = Sort.Direction.fromString(sortParts[1]);
    }
    Pageable pageable = PageRequest.of(page,size, Sort.by(direction,field));
    return ResponseEntity.ok(nominationServices.getPaginated(pageable));
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
    if (msg.equals("That nomination has campaign activities,delete them first")) {
      return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
    }
    if (msg.contains("does not exist")) {
        return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }return ResponseEntity.ok(msg);
  }
  @PatchMapping("/cancel-day")
  public ResponseEntity<String>cancelDay(@RequestParam UUID returningOfficerId,@RequestParam  String date){
    LocalDate localDate = LocalDate.parse(date);
    String msg = nominationServices.cancelDay(returningOfficerId, localDate);
    return ResponseEntity.ok(msg);
  }
  @PatchMapping("/{id}/status")
  public ResponseEntity<String>changeStatu(@PathVariable UUID id, @RequestParam NominationStatus status ){
    String msg = nominationServices.changeStatus(id, status);
    if (msg.equals("The nomiantion with that id does not exist")) {
      return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }if (msg.startsWith("Cannot change status from")) {
      return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
    }return ResponseEntity.ok(msg);
  }
}