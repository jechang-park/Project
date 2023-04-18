package com.kh.yagiyo.web;

import com.kh.yagiyo.domain.board.svc.CommentSVC;
import com.kh.yagiyo.web.form.board.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentSVC commentSVC;

  @PostMapping("/save")
  public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {
    System.out.println("commentDTO = " + commentDTO);
    Long saveResult = commentSVC.save(commentDTO);
    if (saveResult != null) {
      List<CommentDTO> commentDTOList = commentSVC.findAll(commentDTO.getBoardId());
      return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
  }

}
