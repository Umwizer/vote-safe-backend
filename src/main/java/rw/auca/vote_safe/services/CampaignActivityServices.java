package rw.auca.vote_safe.services;

import org.springframework.stereotype.Service;
import rw.auca.vote_safe.repository.CampaignActivityRepository;
import rw.auca.vote_safe.repository.NominationRepository;
import java.util.*;
import rw.auca.vote_safe.models.CampaignActivity;
import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.models.NominationStatus;
import java.time.LocalDate;
@Service 
public class CampaignActivityServices {
    
    private final CampaignActivityRepository campaignActivityRepository;
    private final NominationRepository nominationRepository;
    public CampaignActivityServices(CampaignActivityRepository campaignActivityRepository,
            NominationRepository nominationRepository) {
        this.campaignActivityRepository = campaignActivityRepository;
        this.nominationRepository = nominationRepository;
    }
    public String saveActivity(UUID nominationId ,CampaignActivity activity){
        Optional<Nomination>  existingNomination = nominationRepository.findById(nominationId);
        if(existingNomination.isEmpty()){
            return "The nomination id does not exist";
        }Nomination nomination = existingNomination.get();
        if (nomination.getStatus() != NominationStatus.APPROVED) {
            return "Campaign activities are only for Approved nominations";
        }activity.setHeldDate(LocalDate.now());
        activity.setNomination(nomination);
        campaignActivityRepository.save(activity);
        return "Campaign activity saved successfully";
    }
}
