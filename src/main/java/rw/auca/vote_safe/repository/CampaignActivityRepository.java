package rw.auca.vote_safe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.auca.vote_safe.models.CampaignActivity;
import java.util.*;
public interface CampaignActivityRepository extends JpaRepository<CampaignActivity,UUID> {
    @Query("SELECT a FROM Campaign WHERE a.nomination.candidate.studentNumber = :studentNumber ORDER BY a.heldDate DESC ")
    List<CampaignActivity>findHistoryByStudentNumber(@Param ("studentNumber") String studentNumber );
    boolean existsByNominationId(UUID nominationId);
}

