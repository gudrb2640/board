package com.ex.board.repository;

import com.ex.board.entity.Board;
import com.ex.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;



@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void insertBoard() {
        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    void readBoard1() {
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    void testReadWithWriter() {
        Object result = boardRepository.getBoardWithWriter(2l);
        Object[] arr = (Object[]) result;
        System.out.println("----------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void testGetBoardWithReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(1l);
        for (Object[] arr :
                result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Test
    void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(row ->{
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }

    @Test
    void testRead3() {

        Object result = boardRepository.getBoardByBno(100l);
        Object[] arr = (Object[]) result;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void deleteBoard(){
        boardRepository.deleteAll();
    }

    @Test
    void
    testSearch1() {

        boardRepository.search1();
    }

    @Test
    void testSearchPage() {
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending()
                .and(Sort.by("title").ascending()));
        Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
    }

}