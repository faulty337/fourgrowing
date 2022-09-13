package com.example.fourgrowing.repository;

import com.example.fourgrowing.data.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByTitle(String title);
    Board findByTitleAndContent(String title, String content);
    List<Board> findByTitleLike(String title);
}
