package com.golfTournamentSystem.rest.member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @GetMapping("/member/search")
    public List<Member> searchMembersByName(@RequestParam String name){
        return memberService.searchMembersByName(name);
    }

    @GetMapping("/member/{id}")
    public Member getMemberById(@PathVariable long id){
        return memberService.getMemberById(id);
    }

    @PostMapping("/member")
    public Member createMember(@RequestBody Member member){
        return memberService.createMember(member);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody Member member){
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

    @DeleteMapping("/member/{id}")
    public void deleteMemberById(@PathVariable long id){
        memberService.deleteMemberById(id);
    }

    // Search members by phone number
    @GetMapping("/members/searchByPhoneNumber")
    public ResponseEntity<List<Member>> searchMembersByPhoneNumber(@RequestParam String phoneNumber){
        return ResponseEntity.ok(memberService.searchMembersByPhoneNumber(phoneNumber));
    }

    // Search by member start date
    @GetMapping("/members/searchByStartDate")
    public ResponseEntity<List<Member>> searchMembersByStartDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate){
        return ResponseEntity.ok(memberService.searchMembersByStartDate(startDate));
    }

}
