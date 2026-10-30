package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.models.ReturningOfficer;
import rw.auca.vote_safe.repository.PollingStationRepository;
import rw.auca.vote_safe.repository.PositionRepository;
import rw.auca.vote_safe.repository.ReturningOfficerRepository;
import rw.auca.vote_safe.models.PollingStation;
import rw.auca.vote_safe.models.Position;
import java.util.*;
@Service 
public class ReturningOfficerServices {
    private final ReturningOfficerRepository returningOfficerRepository;
    private final PositionRepository positionRepository; 
    private final PollingStationRepository pollingStationRepository;
    public ReturningOfficerServices(ReturningOfficerRepository returningOfficerRepository,PositionRepository positionRepository,PollingStationRepository pollingStationRepository) {
        this.returningOfficerRepository = returningOfficerRepository;
        this.positionRepository = positionRepository;
        this.pollingStationRepository = pollingStationRepository;
    }
    public String saveReturningOfficer(ReturningOfficer r){
        returningOfficerRepository.save(r);
        return "ReturningOfficer saved successfully";
    }
    public String addPosition(UUID officerId,UUID positionId){
        Optional<ReturningOfficer> existingOfficer = returningOfficerRepository.findById(officerId);
        if (existingOfficer.isEmpty()) {
            return  "The returning  officer with that id does not exist";
        }
        Optional<Position> existingPosition = positionRepository.findById(positionId);
        if (existingPosition.isEmpty()) {
           return "That position does not exist";
        }
        ReturningOfficer officer = existingOfficer.get();
        Position position = existingPosition.get();
        boolean alreadyHasIt = false;
        for(Position currentPosition : officer.getPositions()){
            if (currentPosition.getId().equals(positionId)) {
                alreadyHasIt = true;
                break;
            }
        }if (alreadyHasIt) {
            return  "The returning officer already has that position";
        }
        officer.getPositions().add(position);
        returningOfficerRepository.save(officer);
        return "Position added to the returning officer";
    }
    public String changePollingStation(UUID officerId,int stationNumber){
        Optional<ReturningOfficer>existingOfficer = returningOfficerRepository.findById(officerId);
        if (existingOfficer.isEmpty()) {
            return "The returning officer with that id does not exist";
        }Optional<PollingStation> existingStation = pollingStationRepository.findByStationNumber(stationNumber);
        if (existingStation.isEmpty()) {
            return "The polling station with that stationNumber does not exist ";
        }ReturningOfficer officer = existingOfficer.get();
        PollingStation newStation = existingStation.get();
        if (returningOfficerRepository.existsByPollingStationId(newStation.getId()) && (officer.getPollingStation() == null || !officer.getPollingStation().getId().equals(newStation.getId()))) {
           return "That polling station is taken by another returnig officer"; 
        }officer.setPollingStation(newStation);
        returningOfficerRepository.save(officer);
        return "Polling station changed successfully";
    }
    public List<ReturningOfficer>all(){
        return returningOfficerRepository.findAll();
    }
    public Optional<ReturningOfficer>byId(UUID id){
        return returningOfficerRepository.findById(id);
    }
    public List<ReturningOfficer>findByPosition(String name){
        return returningOfficerRepository.findOfficersByPositionName(name);
    }
    public String updateReturningOfficer(UUID id,ReturningOfficer returningOfficer){
        if(returningOfficerRepository.existsById(id)){
            returningOfficer.setId(id);
            returningOfficerRepository.save(returningOfficer);
            return "Returning officer updated successfully";
        }
        return  "The returning officer with that id does not exist";
    }
    public String delete(UUID id){
        if (returningOfficerRepository.existsById(id)) {
            returningOfficerRepository.deleteById(id);
            return "Returning officer deleted successfully";
        }return "Returning officer with that id does not exists";
    }
}
