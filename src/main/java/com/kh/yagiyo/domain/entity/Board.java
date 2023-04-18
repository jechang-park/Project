package com.kh.yagiyo.domain.entity;

import com.kh.yagiyo.web.form.board.BoardForm;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// DB의 테이블 역활을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board")
@SequenceGenerator(
        name = "ID_SEQ",
        sequenceName = "ID_SEQ",
        initialValue = 1, allocationSize = 1)
public class Board extends Base{
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ")
  private Long ID;

  @Column(length = 20, nullable = false) // 크기 20. not null
  private String BOARD_WRITER;

  @Column // 크기 255 , null 가능
  private String BOARD_PASS;

  @Column
  private String BOARD_TITLE;

  @Column(length = 500)
  private String BOARD_CONTENTS;

  @Column
  private int BOARD_HITS;

  @Column
  private int FILEATTACHED; // 1 or 0

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
  private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

  @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY)
  private List<CommentEntity> commentEntityList = new ArrayList<>();


  public static Board toSaveEntity(BoardForm boardForm){
    Board board = new Board();
    board.setBOARD_WRITER(boardForm.getBoardWriter());
    board.setBOARD_PASS(boardForm.getBoardPass());
    board.setBOARD_TITLE(boardForm.getBoardTitle());
    board.setBOARD_CONTENTS(boardForm.getBoardContents());
    board.setBOARD_HITS(0);
    board.setFILEATTACHED(0); // 파일 없음.
    return board;
  }

  public static Board toUpdateEntity(BoardForm boardForm) {
    Board board = new Board();
    board.setID(boardForm.getId());
    board.setBOARD_WRITER(boardForm.getBoardWriter());
    board.setBOARD_PASS(boardForm.getBoardPass());
    board.setBOARD_TITLE(boardForm.getBoardTitle());
    board.setBOARD_CONTENTS(boardForm.getBoardContents());
    board.setBOARD_HITS(boardForm.getBoardHits());
    return board;
  }

  public static Board toSaveFileEntity(BoardForm boardForm) {
    Board board = new Board();
    board.setBOARD_WRITER(boardForm.getBoardWriter());
    board.setBOARD_PASS(boardForm.getBoardPass());
    board.setBOARD_TITLE(boardForm.getBoardTitle());
    board.setBOARD_CONTENTS(boardForm.getBoardContents());
    board.setBOARD_HITS(0);
    board.setFILEATTACHED(1); // 파일 있음.
    return board;
  }
}
