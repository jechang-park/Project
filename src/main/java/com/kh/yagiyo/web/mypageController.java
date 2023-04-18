package com.kh.yagiyo.web;

import com.kh.yagiyo.domain.entity.Member;
import com.kh.yagiyo.domain.member.svc.MemberSVC;
import com.kh.yagiyo.web.form.member.MemberFixForm;
import com.kh.yagiyo.web.form.member.MemberDeleteForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class mypageController {

    private final MemberSVC memberSVC;


    @GetMapping("/{memberId}/delete")
    public String deleteForm(@PathVariable("memberId") Long memberId, HttpSession session, Model model) {
        LoginMember loginMember = (LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Optional<Object> member = memberSVC.findById(loginMember.getMemberId());

        model.addAttribute("member", member);
        return "/member/memberDelete";
    }
    @PostMapping("/{memberId}/delete")
    public String memberDelete(@PathVariable("memberId") Long memberId, @ModelAttribute MemberDeleteForm memberDeleteForm, HttpSession session) {
        log.info("memberDeleteForm={}",memberDeleteForm);
        LoginMember loginMember = (LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER);
        memberSVC.delete(loginMember.getMemberId());

        // 삭제된 회원이 현재 로그인한 회원이면 로그아웃 처리
        if (loginMember.getMemberId().equals(memberId)) {
            session.invalidate();
        }
        return "redirect:/";
    }

@GetMapping("/{memberId}/fix")
public String memberInformation(Model model,HttpServletRequest request) {
    // HttpSession 객체를 이용하여 세션에서 사용자 정보를 가져옴
    HttpSession session = request.getSession(false); // false 파라미터를 사용하여 새로운 세션을 생성하지 않고, 기존 세션이 있으면 가져옵니다.
    if (session == null) {
        return "/member/memberFix"; // 세션이 없으면 회원 정보 수정 페이지로 이동합니다.
    } else {
        LoginMember loginMember = (LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember != null) {
            String username = loginMember.getNick();
            model.addAttribute("username", username);
            return "/member/memberFix"; // 세션에 저장된 회원 정보를 사용하여 회원 정보 수정 페이지로 이동합니다.
        } else {
            return "redirect:/"; // 세션에 로그인 정보가 없으면 메인 페이지로 이동합니다.
        }
    }
}
    @PostMapping("/{memberId}/fix")
    public String memberFix(@PathVariable Long memberId, @ModelAttribute MemberFixForm memberFixForm, HttpSession session) {

        log.info("memberFixForm={}",memberFixForm);

        // 현재 로그인한 회원의 정보를 세션에서 가져옴
        LoginMember loginMember = (LoginMember) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 입력된 수정 정보를 바탕으로 회원 정보 수정
        Member member = new Member(

            loginMember.getMemberId(), // 로그인멤버에서 멤버아이디값을 불러옴
            memberFixForm.getId(),
            memberFixForm.getPw(),
            memberFixForm.getNick(),
            memberFixForm.getEmail(),
            memberFixForm.getGender(),
            memberFixForm.getAge(),
            memberFixForm.getGubun()
        );
        memberSVC.update(member);

        // 수정된 회원 정보를 다시 세션에 저장
        LoginMember updatedLoginMember = new LoginMember(
            loginMember.getMemberId(),
            loginMember.getId(),
            loginMember.getPw(),
            loginMember.getNick(),
            loginMember.getEmail(),
            loginMember.getGender(),
            loginMember.getAge(),
            loginMember.getGubun()
        );
        session.setAttribute(SessionConst.LOGIN_MEMBER, updatedLoginMember);

        // 수정 완료 후 홈 화면으로 리다이렉트

        return "redirect:/";
    }
}
