package rw.auca.vote_safe.controllers;

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

import rw.auca.vote_safe.models.ReturningOfficer;
import rw.auca.vote_safe.services.ReturningOfficerServices;
import java.util.*;
@RestController 
@RequestMapping("api/returning-officers")

public class ReturningOfficerController {
    private final ReturningOfficerServices returningOfficerServices;

    public ReturningOfficerController(ReturningOfficerServices returningOfficerServices){
        this.returningOfficerServices=returningOfficerServices;
    }
    @PostMapping ("/save")
    public ResponseEntity<String>save(@RequestBody ReturningOfficer r){
        return new ResponseEntity<>(returningOfficerServices.saveReturningOfficer(r),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ReturningOfficer>>all(){
        return ResponseEntity.ok(returningOfficerServices.all());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable UUID id){
        Optional<ReturningOfficer>r = returningOfficerServices.byId(id);
        if (r.isPresent()) {
            return new ResponseEntity<>(r.get(),HttpStatus.OK);
        }return new ResponseEntity<>("The returning officer with that id does not exist",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/by-position")
    public ResponseEntity<List<ReturningOfficer>>byPosition(@RequestParam String name){
        return ResponseEntity.ok(returningOfficerServices.findByPosition(name));
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<String>update(@PathVariable UUID id ,@RequestBody ReturningOfficer r){
        String msg = returningOfficerServices.updateReturningOfficer(id, r);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(msg);
    }
    @PutMapping("/{returningOfficerId}/positions/{positionId}")
    public ResponseEntity<String>addPosition(@PathVariable UUID returningOfficerId,@PathVariable UUID positionId){
        String msg = returningOfficerServices.addPosition(returningOfficerId, positionId);
        if (msg.equals("The returning officer with that id does not exist") || msg.equals("That position does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }
        if (msg.equals("The returning officer already has that position")) {
            return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(msg);
    }
    @PatchMapping("/{returningOfficerId}/polling-station")
    public ResponseEntity<String>changeStation(@PathVariable UUID returningOfficerId,@RequestParam int stationNumber){
        String msg = returningOfficerServices.changePollingStation(returningOfficerId, stationNumber);
        if (msg.equals("The returning officer with that id does not exist")|| msg.equals("The pollign station with that stationNumber does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }if (msg.equals("That polling stations is taken by another returning officer")) {
            return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
        }return ResponseEntity.ok(msg);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable UUID id){
        String msg = returningOfficerServices.delete(id);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(msg);
    }
}
