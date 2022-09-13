package com.example.fourgrowing.service;

import com.example.fourgrowing.data.dto.BoardResponseDTO;
import com.example.fourgrowing.data.entity.Board;
import com.example.fourgrowing.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }

    public BoardResponseDTO getBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setUsername(board.getUsername());
        boardResponseDTO.setTitle(board.getTitle());
        boardResponseDTO.setContent(board.getContent());
        boardResponseDTO.setCreateDate(board.getCreateDate());
        return boardResponseDTO;
    }
}
