package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.auca.vote_safe.models.CampaignActivity;
import java.util.*;
public interface CampaignActivityRepository extends JpaRepository<CampaignActivity,UUID> {
    
}
