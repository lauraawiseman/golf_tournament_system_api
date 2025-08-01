package com.golfTournamentSystem.rest.member;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.golfTournamentSystem.rest.tournament.Tournament;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Member {

    @Id
    @SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "member_sequence")
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private LocalDate startDate;
    private Integer duration;

    @ManyToMany(mappedBy = "participatingMembers")
    @JsonBackReference
    private Set<Tournament> tournaments = new HashSet<>();

    // Constructors
    public Member(){}

    public Member(String name, String address, String email, String phoneNumber, LocalDate startDate, Integer duration){
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.duration = duration;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public void addTournament(Tournament tournament){
        this.tournaments.add(tournament);
    }
}

