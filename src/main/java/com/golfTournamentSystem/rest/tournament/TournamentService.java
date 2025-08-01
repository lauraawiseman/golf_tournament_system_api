package com.golfTournamentSystem.rest.tournament;

import com.golfTournamentSystem.rest.member.Member;
import com.golfTournamentSystem.rest.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    // Get all tournaments
    public List<Tournament> getAllTournaments(){
        return (List<Tournament>) tournamentRepository.findAll();
    }

    // Get tournament by id
    public Tournament getTournamentById(Long id){
        Optional<Tournament> optionalTournament = tournamentRepository.findById(id);
        return optionalTournament.orElse(null);
    }

    // Create new tournament
    public Tournament createTournament(Tournament newTournament){
        return tournamentRepository.save(newTournament);
    }

    // Update tournament
    public Tournament updateTournament(long id, Tournament updatedTournament){
        Optional<Tournament> tournamentToUpdateOptional = tournamentRepository.findById(id);

        if (tournamentToUpdateOptional.isPresent()){
            Tournament tournamentToUpdate = tournamentToUpdateOptional.get();

            tournamentToUpdate.setStartDate(updatedTournament.getStartDate());
            tournamentToUpdate.setEndDate(updatedTournament.getEndDate());
            tournamentToUpdate.setLocation(updatedTournament.getLocation());
            tournamentToUpdate.setEntryFee(updatedTournament.getEntryFee());
            tournamentToUpdate.setCashPrizeAmount(updatedTournament.getCashPrizeAmount());

            return tournamentRepository.save(tournamentToUpdate);
        } else{
            throw new RuntimeException("Tournament not found with id: " + id);
        }
    }

    // Delete tournament by id
    public void deleteTournament(Long id){
        tournamentRepository.deleteById(id);
    }

    // Search tournament by start date
    public List<Tournament> searchTournamentsByStartDate(LocalDate startDate){
        return tournamentRepository.findByStartDate(startDate);
    }

    // Search tournament by location
    public List<Tournament> searchTournamentsByLocation(String location){
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    // Add member to tournament
    public Tournament addMemberToTournament(long tournamentId, long memberId){
        Optional<Tournament> tournamentOptional = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOptional = memberRepository.findById(memberId);

        if (tournamentOptional.isPresent() && memberOptional.isPresent()){
                Tournament tournament = tournamentOptional.get();
                Member member = memberOptional.get();

                tournament.addMember(member);

                return tournamentRepository.save(tournament);
            } else{
                throw new RuntimeException("Tournament or Member not found with provided ids.");
            }
        }

    public Set<Member> getMembersInTournament(Long tournamentId){
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return tournament.getParticipatingMembers();
    }

}
