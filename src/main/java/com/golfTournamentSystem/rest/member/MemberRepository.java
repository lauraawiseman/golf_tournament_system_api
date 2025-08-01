package com.golfTournamentSystem.rest.member;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long>{
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhoneNumberContainingIgnoreCase(String phoneNumber);
    List<Member> findByStartDate(LocalDate startDate);

}

