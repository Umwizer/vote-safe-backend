package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.models.Candidate;
import rw.auca.vote_safe.repository.CandidateRepository;
import java.util.*;
@Service 
public class CandidateServices {
    private final CandidateRepository candidateRepository;
    
    public CandidateServices(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public String saveCandidate(Candidate candidate){
        candidateRepository.save(candidate);
        return "Candidated saved successfully";
    }
    public List<Candidate>all(){
        return candidateRepository.findAll();
    }
    public  Optional<Candidate>byId(UUID id){
        return candidateRepository.findById(id);
    }
    public String updateCandidate(UUID id,Candidate candidate){
        if (candidateRepository.existsById(id)) {
            candidate.setId(id);
            candidateRepository.save(candidate);
            return "Candidate updated successfully";
        }
        return "The candidate with that id does not exist";
    }
    public String deleteCandidate(UUID id){
        if (candidateRepository.existsById(id)) {
           candidateRepository.deleteById(id); 
           return "Candidate deleted successfully";
        }return "The candidate with that id does not exist";
    }
}
