package com.kh.yagiyo.domain.board.dao;

import com.kh.yagiyo.domain.entity.Board;
import com.kh.yagiyo.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <CommentEntity,Long> {
  //select * from comment_table where board_id=? order by id desc;

  List<CommentEntity> findAllByBoardOrderByIdDesc(Board board);
}
