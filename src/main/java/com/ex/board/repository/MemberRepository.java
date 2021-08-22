package com.ex.board.repository;

import com.ex.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MemberRepository extends JpaRepository<Member,String> {

}
