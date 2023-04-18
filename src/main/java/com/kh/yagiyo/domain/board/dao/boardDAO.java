package com.kh.yagiyo.domain.board.dao;

import com.kh.yagiyo.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface boardDAO extends JpaRepository<Board,Long> {
  //update board set board_hits=board_hits+1 where id=?
  @Modifying
  @Query("update Board b set b.BOARD_HITS = b.BOARD_HITS + 1 where b.id = :id")
  void updateHits(@Param("id") Long id);

}
