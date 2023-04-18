package com.kh.yagiyo.web.form.board;

import com.kh.yagiyo.domain.entity.CommentEntity;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class CommentDTO {
  private Long id;
  private String commentWriter;
  private String commentContents;
  private Long boardId;
  private LocalDate commentCreatedTime;

  public static CommentDTO toCommentDTO(CommentEntity commentEntity,Long boardId) {
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setId(commentEntity.getId());
    commentDTO.setCommentWriter(commentEntity.getCOMMENT_WRITER());
    commentDTO.setCommentContents(commentEntity.getCOMMENT_CONTENTS());
    commentDTO.setCommentCreatedTime(commentEntity.getCREATE_TIME().toLocalDate());
//    commentDTO.setBoardId(commentEntity.getBoard().getID()); // Service 메서드에 @Transactional
    commentDTO.setBoardId(boardId);
return commentDTO;
  }
}
