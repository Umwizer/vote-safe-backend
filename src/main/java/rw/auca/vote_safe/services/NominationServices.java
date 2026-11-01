package rw.auca.vote_safe.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
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
    public String changeStatus(UUID id ,NominationStatus newStatus){
        Optional<Nomination>found = nominationRepository.findById(id);
        if (found.isEmpty()) {
            return "The nomination with that id does not exist";
        }Nomination n = found.get();
        NominationStatus current = n.getStatus();
        boolean allowed = false;
        if (current == NominationStatus.SUBMITTED && (newStatus == NominationStatus.VETTED || newStatus == NominationStatus.DISQUALIFIED)) {
            allowed=true;
        }else if (current == NominationStatus.VETTED && (newStatus == NominationStatus.APPROVED || newStatus == NominationStatus.DISQUALIFIED)) {
            allowed=true;
        }if (!allowed) {
            return "Cannot change status from" + current + "to" + newStatus;
        }n.setStatus(newStatus);
        nominationRepository.save(n);
        return  "Nomination status updated successfully";
    }
    public List<Object[]> getStatsByStatus(){
        return nominationRepository.getNominationStasByStatus();
    }
    public String updateNomination(UUID id, Nomination nomination){
        Optional<Nomination>existingNomination =nominationRepository.findById(id);
        if (existingNomination.isPresent()) {
            Nomination found = existingNomination.get();
            nomination.setStatus(found.getStatus());
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
    @Transactional 
    public String cancelDay(UUID officerId,LocalDate date){
        int count = nominationRepository.cancelNominationForDay(officerId, date);
        return count + " nomination cancelled ";
    }
    public Page<Nomination> getPaginated(Pageable pageable){
        return nominationRepository.findAll(pageable);
    }
}