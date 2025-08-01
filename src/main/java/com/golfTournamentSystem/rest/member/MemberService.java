package com.golfTournamentSystem.rest.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers(){
        return (List<Member>) memberRepository.findAll();
    }

    public Member getMemberById(long id){
        Optional<Member> optionalMember = memberRepository.findById(id);

        return optionalMember.orElse(null);
    }

    public List<Member> searchMembersByName(String name){
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteMemberById(long id){
        memberRepository.deleteById(id);
    }

    public Member createMember(Member newMember){
        return memberRepository.save(newMember);
    }

    public Member updateMember(long id, Member updatedMember){
        Optional<Member> memberToUpdateOptional = memberRepository.findById(id);

        if (memberToUpdateOptional.isPresent()){
            Member memberToUpdate = memberToUpdateOptional.get();

            memberToUpdate.setName(updatedMember.getName());
            memberToUpdate.setAddress(updatedMember.getAddress());
            memberToUpdate.setEmail(updatedMember.getEmail());
            memberToUpdate.setPhoneNumber(updatedMember.getPhoneNumber());
            memberToUpdate.setStartDate(updatedMember.getStartDate());
            memberToUpdate.setDuration(updatedMember.getDuration());

            // Save updated member to the database
            return memberRepository.save(memberToUpdate);
        }else{
            throw new RuntimeException("Member not found with id: "+ id);
        }
    }

    public List<Member> searchMembersByPhoneNumber(String phoneNumber){
        return memberRepository.findByPhoneNumberContainingIgnoreCase(phoneNumber);
    }

    public List<Member> searchMembersByStartDate(LocalDate startDate){
        return memberRepository.findByStartDate(startDate);
    }
}
