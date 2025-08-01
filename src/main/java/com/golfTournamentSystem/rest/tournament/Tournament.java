package com.golfTournamentSystem.rest.tournament;


import com.golfTournamentSystem.rest.member.Member;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tournament {

    @Id
    @SequenceGenerator(name = "tournament_sequence", sequenceName = "tournament_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "tournament_sequence")
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private Double entryFee;
    private Double cashPrizeAmount;

    @ManyToMany
    @JoinTable(
            name = "tournament_members",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Set<Member> participatingMembers = new HashSet<>();

    // Constructors

    public Tournament(){}

    public Tournament(LocalDate startDate, LocalDate endDate, String location, Double entryFee, Double cashPrizeAmount){
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrizeAmount = cashPrizeAmount;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(Double entryFee) {
        this.entryFee = entryFee;
    }

    public Double getCashPrizeAmount() {
        return cashPrizeAmount;
    }

    public void setCashPrizeAmount(Double cashPrizeAmount) {
        this.cashPrizeAmount = cashPrizeAmount;
    }

    public Set<Member> getParticipatingMembers() {
        return participatingMembers;
    }

    public void setParticipatingMembers(Set<Member> participatingMembers) {
        this.participatingMembers = participatingMembers;
    }

    // Helper method to add Member
    public void addMember(Member member){
        this.participatingMembers.add(member);
    }
}

