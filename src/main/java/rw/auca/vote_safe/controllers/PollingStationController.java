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
import rw.auca.vote_safe.models.PollingStation;
import rw.auca.vote_safe.services.PollingStationServices;
import java.util.*;
@RestController 
@RequestMapping("api/polling-stations")
public class PollingStationController{
    private final PollingStationServices pollingStationServices;
    
    private PollingStationController(PollingStationServices pollingStationServices){
        this.pollingStationServices=pollingStationServices;
    }
    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody PollingStation p){
        return new ResponseEntity<>(pollingStationServices.savePollingStation(p),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PollingStation>>all(){
        return ResponseEntity.ok(pollingStationServices.all());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable UUID id){
        Optional<PollingStation>p = pollingStationServices.byId(id);
        if (p.isPresent()) {
            return new ResponseEntity<>(p.get(),HttpStatus.OK);
        }return new ResponseEntity<>("The polling  with that id does exist",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/free")
    public ResponseEntity<List<PollingStation>>freeStations(){
        return ResponseEntity.ok(pollingStationServices.findFreeStations());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@PathVariable  UUID id,@RequestBody PollingStation p ){
        String msg=pollingStationServices.updatePollingStation(id, p);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(msg);
    }@DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable  UUID id){
        String msg = pollingStationServices.delete(id);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(msg);
    }
}