package com.golfTournamentSystem.rest.tournament;

import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.golfTournamentSystem.rest.member.Member;

import java.util.List;
import java.time.LocalDate;
import java.util.Set;

@RestController
@CrossOrigin
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    // Get all tournaments
    @GetMapping("/tournaments")
    public List<Tournament> getAllTournaments(){
        return tournamentService.getAllTournaments();
    }

    // Get tournament by id
    @GetMapping("/tournaments/{id}")
    public Tournament getTournamentById(@PathVariable long id){
        return tournamentService.getTournamentById(id);
    }

    // Create new tournament
    @PostMapping("/tournament")
    public Tournament createTournament(@RequestBody Tournament tournament){
        return tournamentService.createTournament(tournament);
    }

    // Update tournament
    @PutMapping("/tournament/{id}")
    public Tournament updateTournament(@PathVariable long id, @RequestBody Tournament tournament){
        return tournamentService.updateTournament(id, tournament);
    }

    // Delete tournament by id
    @DeleteMapping("/tournament/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable long id){
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }

    // Search tournaments by start date
    @GetMapping("/tournament/searchByStartDate")
    public ResponseEntity<List<Tournament>> searchTournamentsByStartDate(@RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate startDate){
        return ResponseEntity.ok(tournamentService.searchTournamentsByStartDate(startDate));
    }

    // Search tournaments by location
    @GetMapping("/tournament/searchByLocation")
    public ResponseEntity<List<Tournament>> searchTournamentsByLocation(@RequestParam String location){
        return ResponseEntity.ok(tournamentService.searchTournamentsByLocation(location));
    }

    // Add member to tournament
    @PostMapping("/tournament/{tournamentId}/addMember/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(@PathVariable long tournamentId, @PathVariable long memberId){
        return ResponseEntity.ok(tournamentService.addMemberToTournament(tournamentId, memberId));
    }

    // Get members in tournament
    @GetMapping("/tournament/{tournamentId}/members")
    public ResponseEntity<Set<Member>> getMembersInTournament(@PathVariable long tournamentId){
        return ResponseEntity.ok(tournamentService.getMembersInTournament(tournamentId));
    }


}
