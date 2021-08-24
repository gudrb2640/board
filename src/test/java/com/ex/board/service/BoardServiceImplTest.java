package com.ex.board.service;

import com.ex.board.dto.BoardDTO;
import com.ex.board.dto.PageRequestDTO;
import com.ex.board.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    void testRegister() {

        BoardDTO dto = BoardDTO.builder()
                .title("레지스터 테스트")
                .content("Test...")
                .writerEmail("user55@aaa.com")
                .build();

        Long bno = boardService.register(dto);
        System.out.println(bno);
    }

    @Test
    void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO boarddto :
                result.getDtoList()) {
            System.out.println(boarddto);
        }
    }

    @Test
    void testGet() {
        Long bno = 100L;
        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO);
    }

    @Test
    void testDelete() {
        Long bno =1l;
        boardService.removeWithReply(bno);
    }

    @Test
    void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경1")
                .content("내용 변경1")
                .build();

        boardService.modify(boardDTO);
    }
    
}