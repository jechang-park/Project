package com.kh.yagiyo.domain.board.svc;

import com.kh.yagiyo.domain.board.dao.boardDAO;
import com.kh.yagiyo.domain.entity.Board;
import com.kh.yagiyo.domain.entity.CommentEntity;
import com.kh.yagiyo.domain.board.dao.CommentRepository;
import com.kh.yagiyo.web.form.board.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentSVCImpl implements CommentSVC {
  private final CommentRepository commentRepository;
  private final boardDAO boardDAO;

  public Long save(CommentDTO commentDTO) {
    Optional<Board> optionalBoardEntity = boardDAO.findById(commentDTO.getBoardId());
    if (optionalBoardEntity.isPresent()) {
      Board board = optionalBoardEntity.get();
      CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, board);
      return commentRepository.save(commentEntity).getId();
    } else {
      return null;
    }
  }

  @Override
  public List<CommentDTO> findAll(Long boardId) {
    Board board = boardDAO.findById(boardId).get();
    List<CommentEntity> commentEntityList = commentRepository.findAllByBoardOrderByIdDesc(board);
    /* EntityList -> DTOList */
    List<CommentDTO> commentDTOList = new ArrayList<>();
    for (CommentEntity commentEntity: commentEntityList) {
      CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, boardId);
      commentDTOList.add(commentDTO);
    }
    return commentDTOList;
  }

}
