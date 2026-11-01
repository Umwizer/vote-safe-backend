package rw.auca.vote_safe.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.auca.vote_safe.models.CampaignActivity;
import rw.auca.vote_safe.services.CampaignActivityServices;
import java.util.*;
@RestController 
@RequestMapping("api/campaign-activities")
public class CampaignActivityController {
    private final CampaignActivityServices campaignActivityServices;
    public CampaignActivityController(CampaignActivityServices campaignActivityServices) {
        this.campaignActivityServices = campaignActivityServices;
    }
    @PostMapping("/save")
        public ResponseEntity<String>save(@RequestParam  UUID nominationId,@RequestBody CampaignActivity c){
        String msg = campaignActivityServices.saveActivity(nominationId,c);
        if (msg.equals("The nomination with that id does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }if (msg.equals("Campaign activities are only APPROVED  nominations")) {
            return new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
        }return new ResponseEntity<>(msg,HttpStatus.CREATED);
    }
    @GetMapping("/candidate/{studentNumber}")
       public ResponseEntity<List<CampaignActivity>> getHistory(@PathVariable String studentNumber){
        List<CampaignActivity> history = campaignActivityServices.getHistory(studentNumber);
        return ResponseEntity.ok(history);
       }
    @DeleteMapping("/delete{id}")
       public ResponseEntity<String>delete(@PathVariable UUID id ){
        String msg = campaignActivityServices.delete(id);
        if (msg.equals("That nomination has campaign activities ,delete them first")) {
            return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
        }
        if (msg.contains("does not exist")) {
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        }return ResponseEntity.ok(msg);
       }
}
