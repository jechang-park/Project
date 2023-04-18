package com.kh.yagiyo.web.form.board;

import com.kh.yagiyo.domain.entity.Board;
import com.kh.yagiyo.domain.entity.BoardFileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든필드를 매개변수로 하는 생성자
public class BoardForm {

  private Long id;
  private String boardWriter;
  private String boardPass;
  private String boardTitle;
  private String boardContents;
  private int boardHits;
  private LocalDateTime boardCreatedTime;
  private LocalDateTime boardUpdatedTime;

  private List<MultipartFile> boardFile; // save.html->controller 파일 담는 용도
  private List<String> originalFileName; // 원본 파일 이름
  private List<String> storedFileName;// 서버 저장용 파일 이름
  private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

  public static BoardForm toBoardForm(Board board){
  BoardForm boardForm = new BoardForm();
  boardForm.setId(board.getID());
  boardForm.setBoardWriter(board.getBOARD_WRITER());
  boardForm.setBoardPass(board.getBOARD_PASS());
  boardForm.setBoardTitle(board.getBOARD_TITLE());
  boardForm.setBoardContents(board.getBOARD_CONTENTS());
  boardForm.setBoardHits(board.getBOARD_HITS());
  boardForm.setBoardCreatedTime(board.getCREATE_TIME());
  boardForm.setBoardUpdatedTime(board.getUPDATE_TIME());
  if(board.getFILEATTACHED() == 0){
    boardForm.setFileAttached(board.getFILEATTACHED()); //0
  }else{
    List<String> originalFileNameList = new ArrayList<>();
    List<String> storedFileNameList = new ArrayList<>();
    boardForm.setFileAttached(board.getFILEATTACHED()); //1
    for(BoardFileEntity boardFileEntity : board.getBoardFileEntityList()) {
     originalFileNameList.add(boardFileEntity.getORIGINAL_FILENAME());
     storedFileNameList.add(boardFileEntity.getSTORED_FILENAME());
    }
    boardForm.setOriginalFileName(originalFileNameList);
    boardForm.setStoredFileName(storedFileNameList);
  }
  return boardForm;
  }

  public BoardForm(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
    this.id = id;
    this.boardWriter = boardWriter;
    this.boardTitle = boardTitle;
    this.boardHits = boardHits;
    this.boardCreatedTime = boardCreatedTime;
  }
}
