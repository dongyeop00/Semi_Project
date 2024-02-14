package com.gml.semi_project.Service;

import com.gml.semi_project.DTO.BoardDTO;
import com.gml.semi_project.Entity.Board;
import com.gml.semi_project.Enum.BoardCategory;
import com.gml.semi_project.Repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardCategory category, BoardDTO boardDTO) {
        Board board = Board.toSaveEntity(category,boardDTO);
        boardRepository.save(board);
    }

    public List<BoardDTO> findBoard(BoardCategory category) {
        List<Board> boardList = boardRepository.findByCategory(category);
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(Board board : boardList){
            boardDTOList.add(BoardDTO.toBoardList(board,category));
        }
        return boardDTOList;
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long boardId) {
        Optional<Board> optionalBoardEntity = boardRepository.findById(boardId);

        if(optionalBoardEntity.isPresent()){
            Board board = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(board);
            return boardDTO;
        }
        else{
            return null;
        }
    }

    public void delete(Long id) {
        Optional<Board> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            boardRepository.deleteById(id);
        }
    }
}
