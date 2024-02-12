package com.gml.semi_project.Controller;

import com.gml.semi_project.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class boardController {
    private final BoardService boardService;

    @GetMapping("/{category}")
    public String list(@PathVariable String category, Model model){
        return "/board/list";
    }
}
