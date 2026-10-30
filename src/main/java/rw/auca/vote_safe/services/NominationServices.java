package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.models.NominationStatus;
import rw.auca.vote_safe.repository.NominationRepository;
import java.util.*;
import java.time.LocalDate;
@Service 
public class NominationServices {
    private final NominationRepository nominationRepository;

    public NominationServices(NominationRepository nominationRepository) {
        this.nominationRepository = nominationRepository;
    }
    public String saveNomination(Nomination nomination){
        if (nomination.getNominationDate().isBefore(LocalDate.now())) {
            return  "Nomination date cannot be in the past";  
        }
        boolean exists = nominationRepository.existsByReturningOfficerIdAndNominationDateAndStatusNot(nomination.getReturningOfficer().getId(),nomination.getNominationDate(),NominationStatus.DISQUALIFIED);
        if (exists) {
            return "Returning officer already vets a nomination on that date";
        }
        nomination.setStatus(NominationStatus.SUBMITTED);
        nominationRepository.save(nomination);
        return "Nomination saved successfully";
    }
    public List<Nomination> all(){
        return nominationRepository.findAll();
    }
    public Optional<Nomination>byId(UUID id){
        return nominationRepository.findById(id);
    }
    public List<Nomination>findByStatus(NominationStatus status){
        return nominationRepository.findByStatusOrderByNominationDateAsc(status);
    }
    public List<Nomination>betweenDates(LocalDate start , LocalDate end){
        return nominationRepository.findByNominationDateBetweenOrderByNominationDateAsc(start, end);
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