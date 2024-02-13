package com.gml.semi_project.Controller;

import com.gml.semi_project.DTO.BoardDTO;
import com.gml.semi_project.Enum.BoardCategory;
import com.gml.semi_project.Service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class boardController {

    private final BoardService boardService;


    @GetMapping("/{category}") //게시판 목록 조회
    public String list(@PathVariable String category, Model model) {
        //List<BoardDTO> boardDTOList = boardService.findBoard(category);
        //model.addAttribute("boardList", boardDTOList);
        return "/board/list";
    }

    @GetMapping("/{category}/write") //게시판 글 쓰기
    public String writeForm(@PathVariable String category, HttpSession session, Model model) {
        //
        return "/board/write";
    }

    @PostMapping("/{category}/write")
    public String write(@PathVariable String category, HttpSession session, @ModelAttribute BoardDTO boardDTO) {
        //
        return "redirect:/board/" + category;
    }

    @GetMapping("/{category}/{id}") //게시글 글 조회
    public String detail(@PathVariable String category, @PathVariable Long boardId, Model model, HttpSession session) {
        //
        return "/board/detail";
    }

    @GetMapping("/{category}/update/{id}") //게시글 글 수정
    public String updateForm(@PathVariable String category, @PathVariable Long boardId, Model model, HttpSession session){
        //
        return "/board/update";
    }

    @PostMapping("/{category}/update/{id}")
    public String update(@PathVariable String category, @PathVariable Long boardId, @ModelAttribute BoardDTO boardDTO){
        //
        return "/board/detail";
    }

    @GetMapping("/{category}/delete/{id}") //게시글 삭제
    public String delete(@PathVariable String category, @PathVariable Long boardId, HttpSession session){
        return "redirect:/board/" + category;
    }

}
