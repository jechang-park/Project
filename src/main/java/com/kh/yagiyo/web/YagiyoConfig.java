package com.kh.yagiyo.web;

import com.kh.yagiyo.web.interceptor.LoginCheckInterceptor;
import com.kh.yagiyo.web.interceptor.MeasuringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class YagiyoConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //응답시간계산
    registry.addInterceptor(new MeasuringInterceptor())
        .order(1)
        .addPathPatterns("/**")
        .excludePathPatterns(
            "/css/*",
            "/js/*",
            "/img/*",
            "/error/*");

    //세션체크
    registry.addInterceptor(new LoginCheckInterceptor())
        .order(2)                 // 인터페이스 실행순서 지정
        .addPathPatterns("/**")   // 인터셉터에 포함시키는 url패턴, 루트경로부터 하위경로 모두
        .excludePathPatterns(
            "/",          //초기화면
            "/login",     //로그인
            "/logout",    //로그아웃
            "/members/add", //회원가입
            "/members/joinSuccess", //가입성공페이지
            "/board", //게시판
            "/board/list", //게시판 목록
            "/board/paging", //게시판 페이징
                "/members/nick-check", // 닉네임체크
                "/members/id-check", // 아이디체크
                "/members/email-check", // 이메일체크
                "/comment/save",        // 댓글 저장
            "/members/mailConfirm",    // 이메일 인증
//            "/board/{id}",
            "/css/*",
            "/js/*",
            "/img/*",
            "/error/*",
            "/api/*"
        );  //인터셉터에서 제외되는 url패턴
  }
}
