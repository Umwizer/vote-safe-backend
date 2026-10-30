package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.repository.NominationRepository;
import java.util.*;
@Service 
public class NominationServices {
    private final NominationRepository nominationRepository;

    public NominationServices(NominationRepository nominationRepository) {
        this.nominationRepository = nominationRepository;
    }
    public String saveNomination(Nomination nomination){
        nominationRepository.save(nomination);
        return "Nomination saved successfully";
    }
    public List<Nomination> all(){
        return nominationRepository.findAll();
    }
    public Optional<Nomination>byId(UUID id){
        return nominationRepository.findById(id);
    }
    public String updateNomination(UUID id, Nomination nomination){
        if (nominationRepository.existsById(id)) {
            nomination.setId(id);
            nominationRepository.save(nomination);
            return "Nomination updated successfully";
        }
        return "The nomination with that id does not exist";
    }
    public String delete(UUID id){
        if (nominationRepository.existsById(id)) {
           nominationRepository.deleteById(id);
           return  "Nomination deleted successfully";
        }
        return "The nomination with that id does not exist";
    }
}