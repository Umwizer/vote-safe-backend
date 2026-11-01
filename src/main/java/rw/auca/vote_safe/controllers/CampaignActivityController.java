package rw.auca.vote_safe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.vote_safe.models.CampaignActivity;
import rw.auca.vote_safe.models.Nomination;
import rw.auca.vote_safe.services.CampaignActivityServices;
import rw.auca.vote_safe.services.NominationServices;
import java.util.*;
@RestController 
@RequestMapping("api/campaign-activity")
public class CampaignActivityController {
    private final CampaignActivityServices campaignActivityServices;
    private final NominationServices nominationServices;
    public CampaignActivityController(CampaignActivityServices campaignActivityServices,
            NominationServices nominationServices) {
        this.campaignActivityServices = campaignActivityServices;
        this.nominationServices = nominationServices;
    }
    @PostMapping("/save")
        public ResponseEntity<String>save(@RequestParam  UUID nominationId,@RequestBody CampaignActivity c){
        String msg = campaignActivityServices.saveActivity(nominationId,c);
        if (msg.equals("The nomination with that id does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }if (msg.equals("Campaign activities are only APPROVED  nominations")) {
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST)
        }return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }

    
}
