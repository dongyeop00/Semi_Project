package com.gml.semi_project.Entity;

import com.gml.semi_project.DTO.BoardDTO;
import com.gml.semi_project.Enum.BoardCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardTitle;

    @Column(length=500, nullable = false)
    private String boardContents;

    @Column
    private int boardHits;

    @Enumerated(EnumType.STRING)
    private BoardCategory category; // 카테고리

    public static Board toSaveEntity(BoardCategory category, BoardDTO boardDTO) {
        Board board = new Board();
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardContents(boardDTO.getBoardContents());
        board.setBoardHits(0);
        board.setCategory(category);
        return board;
    }
}
