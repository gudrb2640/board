package com.ex.board.service;

import com.ex.board.dto.BoardDTO;
import com.ex.board.dto.PageRequestDTO;
import com.ex.board.dto.PageResultDTO;
import com.ex.board.entity.Board;
import com.ex.board.entity.Member;
import com.ex.board.repository.BoardRepository;
import com.ex.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Long register(BoardDTO dto) {



        Board board = dtoToEntity(dto);

        log.info(dto);

        boardRepository.save(board);

        log.info(dto);
        return board.getBno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        log.info(pageRequestDTO);

        Function<Object[], BoardDTO> fn = (en -> entityToDto((Board) en[0], (Member) en[1], (Long) en[2]));

//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );


        return new PageResultDTO<>(result,fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);

        Object[] arr = (Object[]) result;
        return entityToDto((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }


    @Override
    public void removeWithReply(Long bno) {

        replyRepository.deleteReplyByBno(bno);
        boardRepository.deleteById(bno);
    }

    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = null;
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        if (result.isPresent()) {
            board = result.get();
        }

        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());

        boardRepository.save(board);
    }
}
