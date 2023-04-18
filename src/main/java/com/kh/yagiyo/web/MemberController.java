package com.kh.yagiyo.web;

import com.kh.yagiyo.domain.entity.Member;
import com.kh.yagiyo.domain.member.svc.MemberSVC;
import com.kh.yagiyo.domain.member.svc.RegisterMail;
import com.kh.yagiyo.web.form.member.JoinForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

  private final MemberSVC memberSVC;
  private final RegisterMail registerMail;


  @GetMapping("/Agree")
  public String agree() {
    return "/member/Agree";
  }

  @GetMapping("/Identity")
  public String identity() {
    return "/member/Identity";
  }

  //회원가입양식
  @GetMapping("add")
  public String joinForm(Model model) {
    model.addAttribute("joinForm", new JoinForm());
    return "/member/joinForm";
  }
  //이메일 인증
  @PostMapping("mailConfirm")
  @ResponseBody
  String mailConfirm(@RequestParam("email") String email) throws Exception {

    String code = registerMail.sendSimpleMessage(email);
    System.out.println("인증코드 : " + code);
    return code;
  }

  // 아이디 중복 체크
  @PostMapping("/add/check")
  public String idCkeck(
      @Valid @ModelAttribute JoinForm joinForm,
      BindingResult bindingResult,
      HttpServletRequest httpServletRequest,
      @RequestParam(value = "redirectUrl", required = false, defaultValue = "/") String redirectUrl
  ) {

    log.info("redirectUrl={}", redirectUrl);
    if (bindingResult.hasErrors()) {
      log.info("bindingResult={}", bindingResult);
      return "/member/joinForm";
    }

    //1)아이디 존재유무
    if (memberSVC.isExist(joinForm.getId())) {
      bindingResult.rejectValue("id", "isExsit", "사용중인 아이디 입니다.");
    }
      return "/member/joinForm";
  }


    //회원가입처리
    @PostMapping("add")
    public String join (@Valid @ModelAttribute JoinForm joinForm, BindingResult bindingResult){
      log.info("joinForm={}", joinForm);
      if (bindingResult.hasErrors()) {
        log.info("bindingResult={}", bindingResult);
        return "/member/joinForm";
      }

      //비밀번호 체크
      if (!joinForm.getPw().equals(joinForm.getPw_check())) {
        bindingResult.reject("pw", "비밀번호가 동일하지 않습니다.");
        log.info("bindingResult={}", bindingResult);
        return "/member/joinForm";
      }

      Member member = new Member();
      member.setId(joinForm.getId());
      member.setPw(joinForm.getPw());
      member.setEmail(joinForm.getEmail());
      member.setNick(joinForm.getNick());
      member.setGender(joinForm.getGender());
      member.setAge(joinForm.getAge());

      memberSVC.save(member);
      return "/member/joinSuccess";
    }
    //이메일 중복체크
@PostMapping("email-check")
  public @ResponseBody String emailCheck(@RequestParam("email") String email){
  System.out.println("email = " + email);
  String checkResult = memberSVC.emailCheck(email);
  return checkResult;
}
//아이디 중복체크
  @PostMapping("id-check")
  public @ResponseBody String idCheck(@RequestParam("id") String id){
    System.out.println("id = " + id);
    String checkResult = memberSVC.idCheck(id);
    return checkResult;
  }

  //닉네임 중복체크
  @PostMapping("nick-check")
  public @ResponseBody String nickCheck(@RequestParam("nick") String nick){
    System.out.println("nick = " + nick);
    String checkResult = memberSVC.nickCheck(nick);
    return checkResult;
  }

  }



