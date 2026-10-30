package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.models.PollingStation;
import rw.auca.vote_safe.repository.PollingStationRepository;
import java.util.*;
@Service 
public class PollingStationServices {
    private final PollingStationRepository pollingStationRepository;
   
    public PollingStationServices(PollingStationRepository  pollingStationRepository){
        this.pollingStationRepository = pollingStationRepository;
    }
    public String savePollingStation(PollingStation pollingStation){
        pollingStationRepository.save(pollingStation);
        return "Polling station saved successfully";
    }
    public List<PollingStation> all(){
        return pollingStationRepository.findAll();
    }
    public Optional<PollingStation>byId(UUID id){
     return pollingStationRepository.findById(id);
    }
    public List<PollingStation>findFreeStations(){
        return pollingStationRepository.findFreePollingStations();
    }
    public String updatePollingStation(UUID id ,PollingStation pollingStation){
        if(pollingStationRepository.existsById(id)){
            pollingStation.setId(id);
            pollingStationRepository.save(pollingStation);
            return "Polling station updated successfully";
        }
        return "The polling station with that id does not exist";
    }
    public String delete(UUID id){
        if(pollingStationRepository.existsById(id)){
            pollingStationRepository.deleteById(id);
            return "Polling station deleted successfully";
        }
        return "The polling station with that id does not exist";
    }
}
