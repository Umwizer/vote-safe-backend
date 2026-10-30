package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.models.ReturningOfficer;
import rw.auca.vote_safe.repository.ReturningOfficerRepository;
import java.util.*;
@Service 
public class ReturningOfficerServices {
    private final ReturningOfficerRepository returningOfficerRepository;

    public ReturningOfficerServices(ReturningOfficerRepository returningOfficerRepository) {
        this.returningOfficerRepository = returningOfficerRepository;
    }
    public String saveReturningOfficer(ReturningOfficer returningOfficer){
        returningOfficerRepository.save(returningOfficer);
        return "Returning officer added successfully";
    }
    public List<ReturningOfficer>all(){
        return returningOfficerRepository.findAll();
    }
    public Optional<ReturningOfficer>byId(UUID id){
        return returningOfficerRepository.findById(id);
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
