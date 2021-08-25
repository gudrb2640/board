package com.ex.board.repository;

import com.ex.board.entity.Board;
import com.ex.board.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository repository;

    @Test
    void insertReply() {

        IntStream.rangeClosed(1,300).forEach(i -> {

            Long bno = (long)(Math.random() * 100) +1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply..." + i)
                    .board(board)
                    .replyer("guest")
                    .build();

            repository.save(reply);
        });
    }

    @Test
    void testListByBoard() {

        List<Reply> replyList = repository.getRepliesByBoardOrderByRno(Board.builder()
                .bno(97L).build());

        replyList.forEach(reply -> {
            System.out.println(reply);
        });
    }

}