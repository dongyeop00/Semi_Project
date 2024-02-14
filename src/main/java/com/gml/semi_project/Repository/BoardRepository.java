package com.gml.semi_project.Repository;

import com.gml.semi_project.Entity.Board;
import com.gml.semi_project.Enum.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByCategory(BoardCategory category);

    @Modifying //@Query 사용할 때 필수로 붙이는 어노테이션
    @Query(value = "update Board b set b.boardHits=b.boardHits+1 where b.id=:id")
    void updateHits(@Param("id") Long id); //id 값이 id123으로 바뀌면 쿼리문 id는 id123으로 바뀜

}

