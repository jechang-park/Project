package com.kh.yagiyo.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

  @GetMapping("/search")
  public String search(@RequestParam String query, Model model) {

    // 검색어가 apple이면 fragments/apple의 header를 보여주고, 그렇지 않으면 fragments/orange의 header를 보여줍니다.
    if (query.equals("오메가")) {
      model.addAttribute("fragment", "fragments/apple :: header");
    } else {
      model.addAttribute("fragment", "fragments/apple :: footer");
    }

    // 검색어를 모델에 추가합니다.
    model.addAttribute("query", query);

    return "search";
  }

}
