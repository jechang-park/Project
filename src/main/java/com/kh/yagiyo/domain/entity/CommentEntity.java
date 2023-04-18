package com.kh.yagiyo.domain.entity;

import com.kh.yagiyo.web.form.board.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends Base{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 20, nullable = false)
  private String COMMENT_WRITER;

  @Column
  private String COMMENT_CONTENTS;

  /* Board:Comment 1:N */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  public static CommentEntity toSaveEntity(CommentDTO commentDTO,Board board){
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setCOMMENT_WRITER(commentDTO.getCommentWriter());
    commentEntity.setCOMMENT_CONTENTS(commentDTO.getCommentContents());
    commentEntity.setBoard(board);
    return commentEntity;
  }
}
