package com.kh.yagiyo.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

  @GetMapping("/search")
  public String search(@RequestParam String query, Model model) {



    return "search";
  }

}
