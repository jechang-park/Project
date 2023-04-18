package com.kh.yagiyo.domain.board.svc;

import com.kh.yagiyo.web.form.board.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface CommentSVC {

  public Long save(CommentDTO commentDTO);

  List<CommentDTO> findAll(Long boardId);
}
