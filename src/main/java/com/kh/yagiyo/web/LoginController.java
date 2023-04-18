package com.kh.yagiyo.web;

import com.kh.yagiyo.domain.common.mail.MailService;
import com.kh.yagiyo.domain.common.util.PasswordGenerator;
import com.kh.yagiyo.domain.entity.Member;
import com.kh.yagiyo.domain.member.svc.MemberSVC;
import com.kh.yagiyo.web.form.login.LoginForm;
import com.kh.yagiyo.web.form.member.FindIdForm;
import com.kh.yagiyo.web.form.member.FindPWForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberSVC memberSVC;

  private final MailService ms;

  //로그인화면
  @GetMapping("/login")
  public String loginForm(Model model){
    model.addAttribute("loginForm", new LoginForm());
    return "login";
  }

  //로그인처리
  @PostMapping("/login")
  public String login(
      @Valid @ModelAttribute LoginForm loginForm,
      BindingResult bindingResult,
      HttpServletRequest httpServletRequest,
      @RequestParam(value="redirectUrl",required = false, defaultValue = "/") String redirectUrl
      ){

    log.info("redirectUrl={}",redirectUrl);
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}",bindingResult);
      return "login";
    }

    //1)아이디 존재유무
    if(!memberSVC.isExist(loginForm.getId())){
      bindingResult.rejectValue("id","login","아이디가 존재하지 않습니다.");
      return "login";
    }

    //2)로그인
    Optional<Member> member = memberSVC.login(loginForm.getId(), loginForm.getPw());
    if(member.isEmpty()){
      bindingResult.rejectValue("pw","login","비밀번호가 일치하지 않습니다.");
      return "login";
    }

    //3)세션 생성
    //세션이 있으면 해당 정보를 가져오고 없으면 세션생성
    HttpSession session = httpServletRequest.getSession(true);
    LoginMember loginMember = new LoginMember(
        member.get().getMemberId(),
        member.get().getId(),
        member.get().getPw(),
        member.get().getNick(),
        member.get().getEmail(),
        member.get().getGender(),
        member.get().getAge(),
        member.get().getGubun()  );
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    return "redirect:"+redirectUrl;
  }

  //로그아웃
  @GetMapping("logout")
  public String logout(HttpServletRequest HttpServletRequest){
    //세션이 있으면 해당 정보를 가져오고 없으면 세션생성 하지 않음
    HttpSession session = HttpServletRequest.getSession(false);
    if(session != null){
      session.invalidate();   //세션 제거
    }
    return "redirect:/";
  }

  //마이페이지
  @GetMapping("/mypage")
  public String mypage() {
    return "/member/mypage";
  }

  //아이디 찾기
  @GetMapping("/findId")
  public String findIdForm(Model model) {
    model.addAttribute("findIdForm", new FindIdForm());
    return "member/findId";
  }

  @PostMapping("/findId")
  public String findId(
      @Valid @ModelAttribute FindIdForm findIdForm,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "member/findId";
    }
    String email = findIdForm.getEmail();
    Optional<String> optionalMember = memberSVC.findEmailById(email);
    if (optionalMember.isPresent()) {
      String id = optionalMember.get();
      FindIdForm result = new FindIdForm();
      result.setId(id);
      model.addAttribute("findIdForm", result);
    } else {
      model.addAttribute("message", "해당 이메일과 연결된 회원 정보를 찾을 수 없습니다.");
    }
    return "member/findId";
  }



  //이메일 인증해서 비밀번호 찾기
  @GetMapping("/findPW")
  public String findPWForm(@ModelAttribute FindPWForm findPWForm){
    return "member/findPW";
  }

  @PostMapping("/findPW")
  public String findPW(
      @Valid @ModelAttribute FindPWForm findPWForm,
      BindingResult bindingResult,
      HttpServletRequest request,
      Model model
  ){
    log.info("findPWForm={}", findPWForm);

    if(bindingResult.hasErrors()){
      return "member/findPW";
    }

    //1) email,nickname 인 회원 찾기
    boolean isExist = memberSVC.isExistByEmailAndId(findPWForm.getEmail(), findPWForm.getId());
    if(!isExist){

      return "member/findPW";
    }
    //2) 임시비밀 번호 생성
    PasswordGenerator.PasswordGeneratorBuilder passwordGeneratorBuilder = new PasswordGenerator.PasswordGeneratorBuilder();
    String tmpPwd = passwordGeneratorBuilder
        .useDigits(true)  //숫자포함여부
        .useLower(true)   //소문자포함
        .useUpper(true)   //대문자포함
        .usePunctuation(false) //특수문자포함
        .build()
        .generate(6); //비밀번호 자리수

    //3) 회원의 비밀번호를 임시비밀번호로 변경
    memberSVC.changePasswd(findPWForm.getEmail(),tmpPwd);

    //4) 메일 발송.
    String subject = "신규 비밀번호 전송";

    //로긴주소
    StringBuilder url = new StringBuilder();
    url.append("http://" + request.getServerName());    //localhost
    url.append(":" + request.getServerPort());          //prot
    url.append(request.getContextPath());               // /
    url.append("/login");

    //메일본문내용
    StringBuilder sb = new StringBuilder();
    sb.append("<!DOCTYPE html>");
    sb.append("<html lang='ko'>");
    sb.append("<head>");
    sb.append("  <meta charset='UTF-8'>");
    sb.append("  <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
    sb.append("  <title>임시 비밀번호 발송</title>");
    sb.append("</head>");
    sb.append("<body>");
    sb.append("  <h1>신규비밀번호</h1>");
    sb.append("  <p>아래 비밀번호로 로그인 하셔서 비밀번호를 변경하세요</p>");
    sb.append("  <p>비밀번호 :" + tmpPwd + "</p>");
    sb.append("  <a href='"+ url +"'>로그인</a>");
    sb.append("</body>");
    sb.append("</html>");

    ms.sendMail(findPWForm.getEmail(), subject , sb.toString());

    model.addAttribute("info", "회원 이메일로 임시번호가 발송되었습니다");

    return "member/findPW";
  }
}
