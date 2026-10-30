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

import java.util.*;
import rw.auca.vote_safe.models.Position;
import rw.auca.vote_safe.services.PositionServices;

@RestController 
@RequestMapping("api/positions")
public class PositionController {
    private final PositionServices positionServices;
    
    public PositionController(PositionServices positionServices){
        this.positionServices =positionServices;
    }
    @PostMapping("/save")
    public ResponseEntity<String>save(@RequestBody Position p){
        return new ResponseEntity<>(positionServices.savePosition(p),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Position>>all(){
     return  ResponseEntity.ok(positionServices.all());
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?>getById(@PathVariable UUID id){
        Optional<Position>position = positionServices.byId(id);
        if (position.isPresent()) {
           return new ResponseEntity<>(position.get(),HttpStatus.OK);
        }return new ResponseEntity<>("The position with that id does not exist",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/unused")
    public  ResponseEntity<List<Position>>unusedPositions(){
        return ResponseEntity.ok(positionServices.findUnused());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String>update(@PathVariable UUID id,@RequestBody Position p){
        String msg=positionServices.updatePosition(id, p);
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }
          return ResponseEntity.ok(msg);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable UUID id){
        String msg = positionServices.delete(id);
    if (msg.contains("does not exist")) {
        return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(msg);
    }
}
