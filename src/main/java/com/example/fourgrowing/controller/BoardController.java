package com.example.fourgrowing.controller;

import com.example.fourgrowing.data.dto.BoardResponseDTO;
import com.example.fourgrowing.data.entity.Board;
import com.example.fourgrowing.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private  final BoardService boardService;
    @GetMapping
    public String boardView(Model model){
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
        return "front/blog";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        BoardResponseDTO board = new BoardResponseDTO();
        board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "board_temp/board_detail";
    }
}
