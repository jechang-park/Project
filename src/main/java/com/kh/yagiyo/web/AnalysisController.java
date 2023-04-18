package com.kh.yagiyo.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AnalysisController {

  @GetMapping("/name")
  public String showNamePage(Model model, HttpSession session) {
    String name = (String) session.getAttribute("name");
    model.addAttribute("name", name);
    return "Analysis/name";
  }

  @PostMapping("/name")
  public String processNamePage(@RequestParam("name") String name,
                                @RequestParam("gender") String gender,
                                HttpSession session) {
    session.setAttribute("name", name);
    session.setAttribute("gender", gender);
    return "redirect:/age";
  }
  // Code for other survey pages goes here

  @GetMapping("/gender")
  public String showGenderPage(Model model, HttpSession session) {
    String gender = (String) session.getAttribute("gender");
    model.addAttribute("gender", gender);
    return "Analysis/gender";
  }

  @PostMapping("/gender")
  public String processGenderPage(@RequestParam("gender") String gender, HttpSession session) {
    session.setAttribute("gender", gender);
    return "redirect:/age";
  }

  @GetMapping("/age")
  public String showAgePage(Model model, HttpSession session) {
    String name = (String) session.getAttribute("name");
    String gender = (String) session.getAttribute("gender");
    String age = (String) session.getAttribute("age");
    model.addAttribute("name", name);
    model.addAttribute("gender", gender);
    model.addAttribute("age", age);
    return "Analysis/age";
  }

  @PostMapping("/age")
  public String processAgePage(@RequestParam("age") String age, HttpSession session) {
    session.setAttribute("age", age);
    return "redirect:/height";
  }

  @GetMapping("/illness")
  public String Analysis4(Model model, HttpSession session) {
    String illness = (String) session.getAttribute("illness");
    model.addAttribute("illness", illness);
    return "Analysis/illness";
  }

  @PostMapping("/illness")
  public String processIllnessPage(@RequestParam("illness") String illness, HttpSession session) {
    session.setAttribute("illness", illness);
    return "redirect:/dosage";
  }

  @GetMapping("/dosage")
  public String Analysis5(Model model, HttpSession session) {
    String dosage = (String) session.getAttribute("dosage");
    model.addAttribute("dosage", dosage);
    return "Analysis/dosageForm";
  }

  @PostMapping("/dosage")
  public String processDosagePage(@RequestParam("dosage") String dosage, HttpSession session) {
    session.setAttribute("dosage", dosage);
    return "redirect:/point";
  }
  @GetMapping("/point")
  public String Analysis6(Model model, HttpSession session) {
    String point = (String) session.getAttribute("point");
    model.addAttribute("point", point);
    return "Analysis/point";
  }
  @PostMapping("/point")
  public String processPointPage(@RequestParam("point") String point, HttpSession session) {
    session.setAttribute("point", point);
    return "redirect:/height";
  }

  @GetMapping("/height")
  public String Analysis7(Model model, HttpSession session) {
    String height = (String) session.getAttribute("height");
    model.addAttribute("height", height);
    return "Analysis/height";
  }
  @PostMapping("/height")
  public String processHeightPage(@RequestParam("height") String height, HttpSession session, Model model) {
    String name = (String) session.getAttribute("name");
    String gender = (String) session.getAttribute("gender");
    String age = (String) session.getAttribute("age");
// session.setAttribute("height", height);
    model.addAttribute("name", name);
    model.addAttribute("gender", gender);
    model.addAttribute("age", age);
    model.addAttribute("height", height);

    return "redirect:/weight";
  }

  @GetMapping("/weight")
  public String Analysis8(Model model, HttpSession session) {
    String weight = (String) session.getAttribute("weight");
    model.addAttribute("weight", weight);
    return "Analysis/weight";
  }
  @PostMapping("/weight")
  public String processWeightPage(@RequestParam("weight") String weight, HttpSession session, Model model) {
    session.setAttribute("weight", weight);
    String name = (String) session.getAttribute("name");
    String gender = (String) session.getAttribute("gender");
    String age = (String) session.getAttribute("age");
    String height = (String) session.getAttribute("height");

    model.addAttribute("name", name);
    model.addAttribute("gender", gender);
    model.addAttribute("age", age);
    model.addAttribute("height", height);
    model.addAttribute("weight", height);

    return "redirect:/result";
  }

//  @GetMapping("/health")
//  public String Analysis9(Model model, HttpSession session) {
//    String health = (String) session.getAttribute("health");
//    model.addAttribute("health", health);
//
//    return "Analysis/health";
//  }
//  @PostMapping("/health")
//  public String processHealthPage(@RequestParam("health") String health, HttpSession session) {
//    session.setAttribute("health", health);
//    return "redirect:/health2";
//  }
//
//  @GetMapping("/health2")
//  public String Analysis10(Model model, HttpSession session) {
//    String health2 = (String) session.getAttribute("health2");
//    model.addAttribute("health2", health2);
//
//    return "Analysis/health2";
//  }
//  @PostMapping("/health")
//  public String processHealth2Page(@RequestParam("health2") String health2, HttpSession session) {
//    session.setAttribute("health2", health2);
//    return "redirect:/sun";
//  }
//
//  @GetMapping("/sun")
//  public String Analysis11(Model model, HttpSession session) {
//    String sun = (String) session.getAttribute("sun");
//    model.addAttribute("sun", sun);
//    return "Analysis/sun";
//  }
//  @PostMapping("/sun")
//  public String processSunPage(@RequestParam("sun") String sun, HttpSession session) {
//    session.setAttribute("sun", sun);
//    return "redirect:/eat";
//  }
//
//  @GetMapping("/eat")
//  public String Analysis12(Model model, HttpSession session) {
//    String eat = (String) session.getAttribute("eat");
//    model.addAttribute("eat", eat);
//    return "Analysis/eat";
//  }
//  @PostMapping("/eat")
//  public String processEatPage(@RequestParam("aeatge") String eat, HttpSession session) {
//    session.setAttribute("eat", eat);
//    return "redirect:/smoking";
//  }
//
//  @GetMapping("/smoking")
//  public String Analysis13(Model model, HttpSession session) {
//    String smoking = (String) session.getAttribute("smoking");
//    model.addAttribute("smoking", smoking);
//    return "Analysis/smoking";
//  }
//  @PostMapping("/smoking")
//  public String processSmokingPage(@RequestParam("smoking") String smoking, HttpSession session) {
//    session.setAttribute("smoking", smoking);
//    return "redirect:/status";
//  }
//
//  @GetMapping("/status")
//  public String Analysis14(Model model, HttpSession session) {
//    String status = (String) session.getAttribute("status");
//    model.addAttribute("status", status);
//    return "Analysis/status";
//  }
//  @PostMapping("/status")
//  public String processStatusPage(@RequestParam("status") String status, HttpSession session) {
//    session.setAttribute("status", status);
//    return "redirect:/men";
//  }
//
//  @GetMapping("/men")
//  public String Analysis15(Model model, HttpSession session) {
//    String men = (String) session.getAttribute("men");
//    model.addAttribute("men", men);
//    return "Analysis/men";}
//  @PostMapping("/men")
//  public String processMenPage(@RequestParam("men") String men, HttpSession session) {
//    session.setAttribute("men", men);
//    return "redirect:/women";
//  }
//
//  @GetMapping("/women")
//  public String Analysis16(Model model, HttpSession session) {
//    String women = (String) session.getAttribute("women");
//    model.addAttribute("women", women);
//    return "Analysis/women";}
//  @PostMapping("/women")
//  public String processWomenPage(@RequestParam("women") String women, HttpSession session) {
//    session.setAttribute("women", women);
//    return "redirect:/chekUp";
//  }
//
//  @GetMapping("/checkUp")
//  public String Analysis17(Model model, HttpSession session) {
//
//    String checkUp = (String) session.getAttribute("checkUp");
//    model.addAttribute("checkUp", checkUp);
//    return "Analysis/checkUp";
//  }
//  @PostMapping("/checkUp")
//  public String processCheckUpPage(@RequestParam("checkUp") String checkUp, HttpSession session) {
//    session.setAttribute("checkUp", checkUp);
//    return "redirect:/drug";
//  }
//
//  @GetMapping("/drug")
//  public String Analysis18(Model model, HttpSession session) {
//    String drug = (String) session.getAttribute("drug");
//    model.addAttribute("drug", drug);
//    return "Analysis/drug";}
//
//  @PostMapping("/drug")
//  public String processDrugPage(@RequestParam("drug") String drug, HttpSession session) {
//    session.setAttribute("drug", drug);
//    return "redirect:/result";
//  }

//  @GetMapping("/result")
//  public ModelAndView showResultPage(@RequestParam("name") String name,
//                                     ModelAndView modelAndView) {
//    modelAndView.setViewName("Analysis/result");
//    modelAndView.addObject("pageTitle", "Result");
//    modelAndView.addObject("resultName", name);
////      modelAndView.addObject("resultGender", gender);
//    // add other model attributes as necessary
//    return modelAndView;
//  }

  @GetMapping("/result")
  public ModelAndView showResultPage(@RequestParam("name") String name,
                                     @RequestParam("gender") String gender,
                                     @RequestParam("age") String age,
                                     @RequestParam("height") String height,
                                     ModelAndView modelAndView) {
    modelAndView.setViewName("Analysis/result");
    modelAndView.addObject("pageTitle", "Result");
    modelAndView.addObject("resultName", name);
    modelAndView.addObject("resultGender", gender);
    modelAndView.addObject("resultAge", age);
    modelAndView.addObject("resultHeight", height);
    // add other model attributes as necessary
    return modelAndView;
  }
}