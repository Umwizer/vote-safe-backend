package rw.auca.vote_safe.services;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import rw.auca.vote_safe.models.NominationStatus;
import rw.auca.vote_safe.repository.CampaignActivityRepository;
import rw.auca.vote_safe.repository.CandidateRepository;
import rw.auca.vote_safe.repository.NominationRepository;
import rw.auca.vote_safe.repository.ReturningOfficerRepository;

@Service 
public class DashboardServices {
    private final CandidateRepository candidateRepository;
    private final ReturningOfficerRepository returningOfficerRepository;
    private final NominationRepository nominationRepository;
    private final CampaignActivityRepository campaignActivityRepository;
    private final PollingStationServices pollingStationServices;
    public DashboardServices(CandidateRepository candidateRepository,
            ReturningOfficerRepository returningOfficerRepository, NominationRepository nominationRepository,
            CampaignActivityRepository campaignActivityRepository,
            rw.auca.vote_safe.services.PollingStationServices pollingStationServices) {
        this.candidateRepository = candidateRepository;
        this.returningOfficerRepository = returningOfficerRepository;
        this.nominationRepository = nominationRepository;
        this.campaignActivityRepository = campaignActivityRepository;
        this.pollingStationServices = pollingStationServices;
    }
    public Map<String,Long>getSummary(){
    Map<String,Long> map = new LinkedHashMap<>();
    map.put("candidates",candidateRepository.count());
    map.put("returningOfficers",returningOfficerRepository.count());
    map.put("freePollingStations",(long) pollingStationServices.findFreeStations().size());
    map.put("nominationsToday",nominationRepository.countByNominationDate(LocalDate.now()));
    map.put("submitted", nominationRepository.countByStatus(NominationStatus.SUBMITTED));
    map.put("campaignActivities", campaignActivityRepository.count());
    return map;
    }
}