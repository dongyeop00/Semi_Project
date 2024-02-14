package com.gml.semi_project.DTO;

import com.gml.semi_project.Entity.Board;
import com.gml.semi_project.Enum.BoardCategory;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String category;


    public static BoardDTO toBoardList(Board board, BoardCategory category) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContents(board.getBoardContents());
        boardDTO.setBoardHits(board.getBoardHits());
        boardDTO.setCategory(category.toString());
        return boardDTO;
    }

    public static BoardDTO toBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContents(board.getBoardContents());
        boardDTO.setBoardHits(board.getBoardHits());
        return boardDTO;
    }
}
