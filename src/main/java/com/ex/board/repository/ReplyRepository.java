package com.ex.board.repository;

import com.ex.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;;


public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
