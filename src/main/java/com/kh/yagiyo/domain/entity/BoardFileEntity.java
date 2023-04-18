package com.kh.yagiyo.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "board_file_table")
public class BoardFileEntity extends Base{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ID;

  @Column
  private String ORIGINAL_FILENAME;

  @Column
  private String STORED_FILENAME;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id")
  private Board board;

  public static BoardFileEntity toBoardFileEntity(Board board, String originalFilename, String storedFilename) {
    BoardFileEntity boardFileEntity = new BoardFileEntity();
    boardFileEntity.setORIGINAL_FILENAME(originalFilename);
    boardFileEntity.setSTORED_FILENAME(storedFilename);
    boardFileEntity.setBoard(board);
    return boardFileEntity;
  }
}
