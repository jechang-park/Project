package com.kh.yagiyo.domain.board.svc;

import com.kh.yagiyo.web.form.board.BoardForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface boardSVC {
   public void save(BoardForm boardForm) throws IOException;

   List<BoardForm> findAll();

  void updateHits(Long id);

  BoardForm findById(Long id);

  BoardForm update(BoardForm boardForm);

  void delete(Long id);

  Page<BoardForm> paging(Pageable pageable);
}
