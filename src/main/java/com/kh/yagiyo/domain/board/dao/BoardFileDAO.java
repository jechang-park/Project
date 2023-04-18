package com.kh.yagiyo.domain.board.dao;

import com.kh.yagiyo.domain.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileDAO extends JpaRepository<BoardFileEntity,Long> {
}
