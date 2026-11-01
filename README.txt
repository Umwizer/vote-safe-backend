
# VoteSafe Backend
**Student:** Umwizerwa Ruth
**Student Id:** 28235
**Project:** VoteSafe
**Course:** INSY 8322 Web Technology and Internet (Mid-Term Examination)
**Lecturere:** Patrick Dushimimana

## Context
VoteSafe runs student elections transparenlty from nomination to campaign.A candidate files
a nomination that is vetted by a returning officer in charge of open polling station.
The nomination moves from SUBMITTED to VETTED and end APPROVED or DISQUALIFIED.Once a nomination is approved,
the returning officer records the campaign activities the candidate holds.

##Technologies
-Java 21 
-Spring Boot 3.5.8(Spring web ,Spring DATA JPA)
-PostgreSQL 18
-Maven
-Postman

##Steps to run the API
1.**Prerequisites** JDK 21,Maven ,PostgreSQL,and Git.
2.**Cloen the repository:**git clone https://github.com/Umwizer/vote-safe-backend.git
cd vote-safe-backend
3.**Create the database in PosstgreSQL:**
CREATE DATABASE vote_safe;
4.**Configure the database**
Open src/main/resources/appication.propertiies and update it with your credentials
spring.appication.name=vote-safe
spring.datasource.url=jdbc:postgresql://localhost:5423/vote_safe
spring.datasource.password=YOUR_PASSWORD
sprign.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
5.**Start the Spring Boot Application:**
mvn spring-boot:run
The Api will be available at http://localhost:8080.hibernate will automatically create the table

## Postman Collection
The file vote-safe-midterm.postman_collection.json is located in root of the repository.
import it into Postman to test al endpoints. It contains one request for each requirement,named by its ID(A2,B1,C4,D2 etc).

## Entities and Endpoints
Entity    tabename   Basepoint 
Position  postion    /api/positions
PollinsStation  polling_stations /api/polling-stations
ReturningOfficer returnig_officer /api/returnig_officers
Candidate       candidate         /api/candidates
Nomination      nomination       /api/nominations
CampaignActivity campaign_activity /api/campaign_activities
