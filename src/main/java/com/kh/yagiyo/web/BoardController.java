package com.kh.yagiyo.web;


import com.kh.yagiyo.domain.board.svc.CommentSVC;
import com.kh.yagiyo.domain.board.svc.boardSVC;
import com.kh.yagiyo.web.form.board.BoardForm;
import com.kh.yagiyo.web.form.board.CommentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
private final boardSVC boardSVC;
private final CommentSVC commentSVC;
  @GetMapping("")
  public String boardForm(){
    return "/board/board";
  }

  @GetMapping("/save")
  public String boardSaveForm(){
    return "/board/boardSave";
  }
  @PostMapping("/save")
  public String save(@ModelAttribute BoardForm boardForm)throws IOException{
    System.out.println("boardForm = " + boardForm);
    boardSVC.save(boardForm);

    return "redirect:/board/paging";
  }
  @GetMapping("/list")
  public String findAll(Model model){
    List<BoardForm> boardList = boardSVC.findAll();
    model.addAttribute("boardList", boardList);
    return "/board/boardList";
  }
  @GetMapping("/{id}")
  public String findById(@PathVariable Long id,Model model,
                          @PageableDefault(page=1) Pageable pageable){
//    해당 게시글의 조회수를 하나 올리고
//    게시글 데이터를 가져와서 detail.html에 출력
    boardSVC.updateHits(id);
    BoardForm boardForm = boardSVC.findById(id);
    List<CommentDTO> commentDTOList = commentSVC.findAll(id);
    model.addAttribute("commentList" ,commentDTOList );
    model.addAttribute("board", boardForm);
    model.addAttribute("page", pageable.getPageNumber());
    return "/board/boardDetail";
  }

  @GetMapping("/update/{id}")
  public String updateForm(@PathVariable Long id,Model model) {
    BoardForm boardForm = boardSVC.findById(id);
    model.addAttribute("board", boardForm);
    return "/board/boardUpdate";
  }
  @PostMapping("/update/{id}")
  public String update(@ModelAttribute BoardForm boardForm,Model model) {
    BoardForm board = boardSVC.update(boardForm);
    model.addAttribute("board", board);
    return "redirect:/board/paging";
//    return "redirect:/board/" + boardForm.getId();
  }
  @GetMapping("/delete/{id}")
  public String delete(@PathVariable Long id){
    boardSVC.delete(id);
    return "redirect:/board/paging";
  }

  // /board/paging?page=1
  @GetMapping("/paging")
  public String Paging(@PageableDefault(page = 1)Pageable pageable,Model model){
//    pageable.getPageNumber();
    Page<BoardForm> boardList = boardSVC.paging(pageable);
    int blockLimit = 3;
    int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
    int endPage = ((startPage + blockLimit - 1) < boardList.getTotalPages()) ? startPage + blockLimit - 1 : boardList.getTotalPages();


    //page 갯수 20개
    // 현재 사용자가 3페이지
    // 1 2 3
    // 보여지는 페이지 갯수 3개
    // 총 페이지 갯수 8개

    model.addAttribute("boardList", boardList);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    return "/board/paging";

  }
}
