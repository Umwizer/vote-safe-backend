package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.models.Position;
import rw.auca.vote_safe.repository.PositionRepository;
import java.util.*;

@Service 
public class PositionServices{
  private final PositionRepository positionRepository;

  public PositionServices(PositionRepository positionRepository) {
    this.positionRepository = positionRepository;
  }
   public String savePosition(Position p){
    positionRepository.save(p);
    return "Position saved successfully";
   } 
   public List<Position>all(){
    return positionRepository.findAll();
   }
   public Optional<Position>byId(UUID id){
    return positionRepository.findById(id);
   }
   public List<Position>findUnused(){
    return positionRepository.findUnusedPositions();
   }
   public String updatePosition(UUID id,Position p){
    if(positionRepository.existsById(id)){
      p.setId(id);
      positionRepository.save(p);
      return "Position updated successfully";
    }
    return "The position with that id does not exist";
   }
   public String delete(UUID id){
    if(positionRepository.existsById(id)){
      positionRepository.deleteById(id);
      return "Position deleted successfully";
    }
    return "The position with that id does not exist";
   }
}